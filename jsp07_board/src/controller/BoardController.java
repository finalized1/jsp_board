package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


import dto.Board;
import dto.Page;
import service.BoardService;
import service.BoardServiceImpl;


@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService bservice = new BoardServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		//context path
		String path = request.getContextPath();
		if (uri.contains("list")) {
			String findkey = request.getParameter("findkey");
			String findvalue = request.getParameter("findvalue");
			String curpage_s = request.getParameter("curpage");
			
			int curpage = 1; //디폴트 페이지
			if (curpage_s != null)
				curpage = Integer.parseInt(curpage_s);

			
			Page page = new Page();
			page.setFindkey(findkey);
			page.setFindvalue(findvalue);
			page.setCurpage(curpage);
			System.out.println("controller :" + page);
			List<Board> blist =  bservice.selectList(page);
			System.out.println(blist);
			
			request.setAttribute("blist", blist);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/views/board/list.jsp").forward(request, response);
			
		}else if (uri.contains("add")) {
			//등록
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*10;
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			
			String email = multi.getParameter("email");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			
			//객체생성
			Board board = new Board();
			board.setEmail(email);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(request.getRemoteAddr()); //요청 ip
			System.out.println(board);
			
			
			//신규 파일이름 처리
			List<String> filenames = new ArrayList<String>();
			Enumeration<String> files = multi.getFileNames();
			while(files.hasMoreElements()) { //다음 자료가 잇으면 true
				String name = files.nextElement();
				System.out.println(name);
				//실제로 저장된 파일이름
				String filename = multi.getFilesystemName(name);
				if (filename != null) filenames.add(filename);
			}
			System.out.println(filenames);
//			String filename1 = multi.getFilesystemName("file1");
//			String filename2 = multi.getFilesystemName("file2");
//			String filename3 = multi.getFilesystemName("file3");
//			System.out.println("파일이름1 :" + filename1);
//			System.out.println("파일이름2 :" + filename2);
//			System.out.println("파일이름3 :" + filename3);
//			String[] filenames = new String[3];
//			filenames[0] = filename1;
//			filenames[1] = filename2;
//			filenames[2] = filename3;
//			System.out.println(Arrays.toString(filenames));
			String msg = bservice.insert(board,filenames);
			
			//redirect방식 /board/list 호출
			response.sendRedirect(path + "/board/list?msg=" + URLEncoder.encode(msg, "utf-8"));
		
		}else if (uri.contains("detail")) {
			//상세정보
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			System.out.println(bnum);
			//조회수 +1
			String cntplus = request.getParameter("cntplus");
			System.out.println("cntplus :" + cntplus);
			if (cntplus != null) {
				bservice.cntplus(bnum);
			}
			//게시물 조회 + 파일조회 + 댓글
			Map<String, Object> map = bservice.selectOne(bnum);
			
			//forward방식 detail.jsp이동
			request.setAttribute("map", map);
			request.getRequestDispatcher("/views/board/detail.jsp").forward(request, response);
		
		}else if (uri.contains("remove")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			String msg = bservice.delete(bnum);
			System.out.println(msg);
			request.setAttribute("msg", msg);
			response.sendRedirect(path + "/board/list?msg=" + URLEncoder.encode(msg, "utf-8"));
		
		}else if (uri.contains("modiform")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			//게시물 조회 파일조회
			Map<String, Object> map = bservice.selectOne(bnum);
			//forward방식 modify.jsp이동
			request.setAttribute("map", map);
			request.getRequestDispatcher("/views/board/modify.jsp").forward(request, response);
		
		}else if (uri.contains("modify")) {
			//수정
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*10;
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			int bnum = Integer.parseInt(multi.getParameter("bnum"));
			String email = multi.getParameter("email");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			Board board = new Board();
			board.setBnum(bnum);
			board.setEmail(email);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(request.getRemoteAddr());
			System.out.println("수정할 board" + board);
			
			String[] filedels = multi.getParameterValues("filedel");
			System.out.println("기존 파일 삭제리스트" + Arrays.toString(filedels));
			
			//신규 파일이름 처리
			List<String> filenames = new ArrayList<String>();
			Enumeration<String> files = multi.getFileNames();
			while(files.hasMoreElements()) { //다음 자료가 잇으면 true
				String name = files.nextElement();
				System.out.println(name);
				//실제로 저장된 파일이름
				String filename = multi.getFilesystemName(name);
				if (filename != null) filenames.add(filename);
			}
			System.out.println("신규파일 추가 리스트" + filenames);
			
			//서비스 update요청
			String msg = bservice.update(board,filedels,filenames);
			
			//redirect방식 /board/detail
			response.sendRedirect(path+"/board/detail?bnum=" + bnum + "&msg=" + URLEncoder.encode(msg, "utf-8"));
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
