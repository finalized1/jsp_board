package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Member;

public class MemberDAOImpl implements MemberDAO{

	@Override
	public int insert(Member member) {
		try(SqlSession session = MBConn.getSession()){
			return session.insert("MemberMapper.insert", member);
		}

	}

	@Override
	public int update(Member member) {
		try(SqlSession session = MBConn.getSession()){
			return session.update("MemberMapper.update", member);
		}

	}

	@Override
	public int delete(String email) {
		try(SqlSession session = MBConn.getSession()){
			return session.delete("MemberMapper.delete", email);
		}

	}

	@Override
	public Member selectOne(String email) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("MemberMapper.selectOne", email);
		}

	}

	@Override
	public List<Member> selectList(Map<String, String> map) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("MemberMapper.selectList", map);
		}

	}

}
