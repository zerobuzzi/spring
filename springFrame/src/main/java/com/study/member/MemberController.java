package com.study.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	//@GetMapping("/member/save")
	@GetMapping("/save")
	public String saveForm() {
		System.out.println("회뤈가입 폼");
		return "/member/save";
	}
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute MemberDTO memberDTO) {
		System.out.println(memberDTO);
		int saveResult = memberService.save(memberDTO);
		
		if (saveResult > 0 ) {
			return "/member/login";
		}else {
			return "save";
		}
		//return "redirect:/";
		
	}
	
}
