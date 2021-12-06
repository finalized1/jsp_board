package dao;


import java.util.List;

import dto.BoardFile;

public interface BoardFileDAO {
	int insert(BoardFile boardFile);
	int update(BoardFile boardFile);
	int delete(int fnum);
	int delete_bnum(int bnum); //bnum을 기준으로 삭제
	BoardFile selectOne(int fnum);
	List<BoardFile> selectList(int bnum);
}
