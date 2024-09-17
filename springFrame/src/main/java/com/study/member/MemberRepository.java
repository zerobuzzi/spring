package com.study.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
	
	@Autowired
	SqlSessionTemplate sql;
	
	public int save(MemberDTO memberDTO) {
		
		return sql.insert("Member.save", memberDTO);
	}

	public MemberDTO login(MemberDTO memberDTO) {
		return sql.selectOne("Member.login", memberDTO);
	}

	public List<MemberDTO> findAll() {
		return sql.selectList("Member.list");
	}

	public MemberDTO findById(long id) {
		// TODO Auto-generated method stub
		return sql.selectOne("Member.detail", id);
	}

	public void delete(long id) {
		sql.delete("Member.delete", id);
	}

	public MemberDTO findByEmail(String loginEmail) {
		return sql.selectOne("Member.ByEmail", loginEmail);
	}

	public int update(MemberDTO memberDTO) {
		return sql.update("Member.update", memberDTO);
	}



}
