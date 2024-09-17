package com.study.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/save")
	public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO){
		System.out.println("commentDTO = " + commentDTO);
		commentService.save(commentDTO); // 댓글 저장
		
		// 게시글 댓글 리스트 가져옴
		List<CommentDTO> commentList = commentService.findAll(commentDTO.getBoardId());
		System.out.println("댓글 리스트  = " + commentList);
		return commentList;
	}
	
}
