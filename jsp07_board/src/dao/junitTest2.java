package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dto.BoardFile;

class junitTest2 {
	BoardFileDAO bfdao = new BoardFileDAOImpl();
	@Test
	void testInsert() {
		BoardFile bf = new BoardFile();
		bf.setBnum(3);
		bf.setFilename("a.png");
		int cnt = bfdao.insert(bf);
		System.out.println(cnt + "건 추가");
	}
	@Test
	void testUpdate() {
		BoardFile bf = new BoardFile();
		bf.setFilename("b.png");
		bf.setFnum(4);
		int cnt = bfdao.update(bf);
		System.out.println(cnt + "건 수정");
	}
	@Test
	void testDelete() {
		int cnt = bfdao.delete(4);
		System.out.println(cnt + "건 삭제");
	}
	@Test
	void testSelectOne() {
		BoardFile bf = bfdao.selectOne(6);
		System.out.println(bf);
	}
	@Test
	void testSelectList() {
		List<BoardFile> bflist = bfdao.selectList(3);
		System.out.println(bflist);
	}
}
