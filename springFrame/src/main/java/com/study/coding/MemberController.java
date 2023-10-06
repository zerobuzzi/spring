package com.study.coding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	
	//@GetMapping("/member/save")
	@GetMapping("/save")
	public String saveForm() {
		System.out.println("회뤈가입 폼");
		return "/member/save";
	}
	
	
	@PostMapping("/save")
	public String save() {
		return "redirect:/";
		
	}
	
}
