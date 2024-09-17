package com.study.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

	@Autowired
	SqlSessionTemplate sql;
	
	public void save(CommentDTO commentDTO) {
		sql.insert("Comment.save", commentDTO);
	}

	public List<CommentDTO> findAll(long boardId) {
		System.out.println(boardId);
		return sql.selectList("Comment.findAll", boardId);
	}

}
