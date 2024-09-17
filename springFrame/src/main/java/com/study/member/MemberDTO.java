package com.study.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class MemberDTO {
	
	private long id;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private int memberAge;
	private String memberMobile;
	
	
}
