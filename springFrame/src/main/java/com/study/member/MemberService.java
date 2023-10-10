package com.study.member;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	final MemberRepository memberRepository;

	public int save(MemberDTO memberDTO) {
		return memberRepository.save(memberDTO);
	}

	public boolean login(MemberDTO memberDTO) {
		MemberDTO loginMember = memberRepository.login(memberDTO);
		
		if(loginMember != null) {
			return true;
		}else {
			return false;
		}
	}

	public List<MemberDTO> findAll() {
		
		return memberRepository.findAll();
	}

	public MemberDTO findById(long id) {
		return memberRepository.findById(id);
	}

	public void delete(long id) {
		memberRepository.delete(id);
	}

	public MemberDTO findByEmail(String loginEmail) {
		return memberRepository.findByEmail(loginEmail);
	}

	public boolean update(MemberDTO memberDTO) {
		int result = memberRepository.update(memberDTO);
		if (result > 0) {
			return true;
		}else {
			return false;
		}
	}

	public String emailCheck(String memberEmail) {
			MemberDTO memberDTO = memberRepository.findByEmail(memberEmail);
			System.out.println("뭐임 = " + memberDTO);
			if(memberDTO == null) {
				return "ok";
			}else {
				return "no";
			}
	}



}
