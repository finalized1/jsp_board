package dao;

import java.util.List;
import java.util.Map;

import dto.Board;
import dto.Page;

public interface BoardDAO {
	int insert(Board board);
	int update(Board board);
	int delete(int bnum);
	Board selectOne(int bnum);
	List<Board> selectList(Page page);
	void cntplus(int bnum);
	//전체게시물 수
	int select_totcnt(Page page);
	//댓글순서 변경
	void restepplus(Board board);
	//댓글조회
	List<Board> select_reply(int ref);
}
