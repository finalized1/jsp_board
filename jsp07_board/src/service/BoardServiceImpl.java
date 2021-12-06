package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BoardDAO;
import dao.BoardDAOImpl;
import dao.BoardFileDAO;
import dao.BoardFileDAOImpl;
import dto.Board;
import dto.BoardFile;
import dto.Page;

public class BoardServiceImpl implements BoardService{
	private BoardDAO bdao = new BoardDAOImpl();
	private BoardFileDAO bfdao = new BoardFileDAOImpl();
	@Override
	public List<Board> selectList(Page page) { 
		//페이징 처리
		int curpage = page.getCurpage(); //현재페이지
		int perpage = page.getPerpage(); //한 페이지 당 게시물 수
		int startnum = (curpage-1) * perpage + 1; //시작번호
		int endnum = startnum + perpage -1;
		page.setStartnum(startnum);
		page.setEndnum(endnum);
		
		//전체페이지 수 구하기
		int totcnt = bdao.select_totcnt(page); //전체 건수
		int totpage = totcnt/perpage; //전체 페이지 수
		System.out.println("전체건수" + totcnt);
		System.out.println("전체페이지수1 :" + totpage);
		if (totcnt%perpage>0) totpage ++;	
		System.out.println("전체페이지수2 :" + totpage);
		page.setTotpage(totpage);
		
			
		
		//페이지블럭 구하기
		int perblock = page.getPerblock(); //페이지 블록의 수
		int startpage =  curpage - ((curpage-1)%perblock);
		int endpage = startpage + perblock -1;
		if (totpage<endpage) endpage = totpage;
		
		page.setStartpage(startpage);
		page.setEndpage(endpage);
		
		System.out.println("service:"+page);
		 return bdao.selectList(page);

	}
	@Override
	public String insert(Board board, List<String> filenames) {
		int cnt = bdao.insert(board);
		System.out.println("board" + cnt + "건 추가");
		System.out.println("service :" + board);
		//파일이름 배열 처리
		//board.getBnum() : boardMapper의 insert시  bnum구해줌
		for(String filename : filenames) {
			if (filename==null) continue;
			BoardFile boardFile = new BoardFile();
			boardFile.setBnum(board.getBnum());
			boardFile.setFilename(filename);
			bfdao.insert(boardFile);
			System.out.println("file :" + boardFile);
		}
		
		if (cnt>0)
			return "추가성공";
		else
			return "추가실패";
	}
	@Override
	public Map<String, Object> selectOne(int bnum) {
		//게시물 조회
		Board board = bdao.selectOne(bnum);
		//게시물 파일 조회
		List<BoardFile> bflist = bfdao.selectList(bnum);
		//댓글조회
		List<Board> rlist =  bdao.select_reply(bnum); //원본의 bnum과 ref는 같다
		
		System.out.println(board);
		System.out.println(bflist);
		System.out.println(rlist);
		
		Map<String, Object> map = new HashMap<>();
		map.put("board", board);
		map.put("bflist", bflist);
		map.put("rlist", rlist);
		return map;
	}
	@Override
	public String delete(int bnum) {
		//주의 : fk때문에 자식데이터부터 삭제 후 부모데이터 삭제
		//게시물삭제
		bfdao.delete_bnum(bnum);
		//게시물의 파일들 삭제
		int cnt = bdao.delete(bnum);
		if (cnt>0) 
			return "삭제 성공";
		else
			return "삭제 실패";
	}
	@Override
	public String update(Board board, String[] filedels, List<String> filenames) {
		//게시물 수정
		int cnt = bdao.update(board);
		
		//파일들 삭제
		if (filedels != null) {
			for(String fnum : filedels) {
				bfdao.delete(Integer.parseInt(fnum));
			}
		}
		//파일들 추가
		BoardFile boardFile = new BoardFile();
		for(String filename : filenames) {
			boardFile.setBnum(board.getBnum());
			boardFile.setFilename(filename);
			bfdao.insert(boardFile);
		}
		if (cnt >0)
			return "수정 완료";
		else
			return "수정 실패";
		
	}
	@Override
	public void cntplus(int bnum) {
		// 조회수 +1
		bdao.cntplus(bnum);
		
	}

}
