package com.study.member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	final MemberRepository memberRepository;

	public int save(MemberDTO memberDTO) {
		return memberRepository.save(memberDTO);
	}

}
