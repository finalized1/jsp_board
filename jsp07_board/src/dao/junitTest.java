package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Board;
import dto.Page;

class junitTest {
	BoardDAO bdao = new BoardDAOImpl();
	@Test
	void testMBConn() {
		MBConn.getSession();
	}
	@Test
	void testInsert() {
		Board board = new Board();
		board.setEmail("tnswl@naver.com");
		board.setSubject("안녕");
		board.setContent("안녕하세요");
		board.setIp("192.168.0.1");
		int cnt = bdao.insert(board);
		System.out.println(cnt + "건 추가");
	}
	@Test
	void testUpdate() {
		Board board = new Board();
		board.setEmail("psj@naver.com");
		board.setSubject("제목");
		board.setContent("내용");
		board.setIp("192.168.0.1");
		board.setBnum(2);
		int cnt = bdao.update(board);
		System.out.println(cnt + "건 수정");
	}
	@Test
	void testDelete() {
		int cnt = bdao.delete(2);
		System.out.println(cnt + "건 삭제");
	}
	@Test
	void testSelectOne() {
		Board board = bdao.selectOne(3);
		System.out.println(board);
		//null과 같지 않으면
		assertNotEquals(null, board);
	}
	@Test
	void testSelectList() {
		Page page = new Page();
		page.setFindkey("email");
		page.setFindvalue("tnswl");
		List<Board> blist = bdao.selectList(page);
		System.out.println(blist);
	}
}
