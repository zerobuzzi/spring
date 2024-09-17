package com.study.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentDTO {

		
	private long id;
	private String commentWriter;
	private String commentContents;
	private long boardId;
	private Timestamp commentCreatedTime;
	
}
