package service;

import java.util.Map;

import dto.Member;

public interface MemberService {
	String insert(Member member);

	Map<String, String> login(String email, String passwd);

	Member selectOne(String email);

	String update(Member member, String changepw);
	
}
