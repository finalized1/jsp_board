package service;

import dto.Board;

/*댓글처리 서비스*/
public interface ReplyService {
	String insert(Board board);

	Board selectOne(int bnum);

	String update(Board board);
	
}
