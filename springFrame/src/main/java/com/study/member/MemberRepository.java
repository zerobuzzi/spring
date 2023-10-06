package com.study.member;

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

}
