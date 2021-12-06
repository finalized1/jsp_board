package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/file/*")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		if (uri.contains("filedown")) {
			//파일다운로드
			//디렉토리, 파일이름
			//web.xml에서 가져옴
//			String saveDirectory = "C:/savedir";
			String saveDirectory = getServletContext().getInitParameter("savedir");
			String filename = request.getParameter("filename");

			//마임타입 : 파일의 종류
			String mimeType = getServletContext().getMimeType(filename);
			if (mimeType == null) {
				mimeType = "application/octet-stream;charset=utf-8";
			}
			response.setContentType(mimeType);
			
			//첨부파일로 파일을 보낼 때
			//한글파일이름 깨지지 않게 utf-8로 인코딩
			//attachment : 첨부파일로 전송
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
			
			//읽어올 파일 경로명
			String  fileurl = saveDirectory + "/" + filename;
			System.out.println(fileurl);
			
			//입력스트림
			FileInputStream fis = new FileInputStream(fileurl);
			//출력스트림
			ServletOutputStream outs = response.getOutputStream();
			
			//한번에 읽어들일 바이트 배열
			byte[] b = new byte[4096]; //4kb 크기의 byte배열
			int numRead = 0; //읽어들인 바이트 수 (-1이면 파일의 끝)
			while((numRead = fis.read(b,0,b.length)) != -1) {
				outs.write(b,0,numRead); //읽어들인 바이트 수 만큼 write
			}
			outs.flush(); //내보내기, 버퍼를 비우기
			outs.close();
			fis.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
