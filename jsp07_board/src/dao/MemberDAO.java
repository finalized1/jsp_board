package dao;

import java.util.List;
import java.util.Map;

import dto.Member;

public interface MemberDAO {
	int insert(Member member);
	int update(Member member);
	int delete(String email);
	Member selectOne(String email);
	List<Member> selectList(Map<String, String> map);
}
