package service;

import java.util.List;
import java.util.Map;

import dto.Board;
import dto.Page;



public interface BoardService {

	List<Board> selectList(Page page);

	String insert(Board board, List<String> filenames);

	Map<String, Object> selectOne(int bnum);

	String delete(int bnum);

	String update(Board board, String[] filedels, List<String> filenames);

	void cntplus(int bnum);

}
