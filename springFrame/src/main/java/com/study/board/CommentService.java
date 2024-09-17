package com.study.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	public void save(CommentDTO commentDTO) {
		commentRepository.save(commentDTO);
	}

	public List<CommentDTO> findAll(long boardId) {
		System.out.println(boardId);
		return commentRepository.findAll(boardId);
	}
	
	
	
}
