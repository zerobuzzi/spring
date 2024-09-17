package com.study.board;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {

	private long id;
	private String boardWriter;
	private String boardPass;
	private String boardTitle;
	private String boardContents;
	private int boardHits;
	private Timestamp boardCreatedTime;
	
}
