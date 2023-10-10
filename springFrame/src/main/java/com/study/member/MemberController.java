package com.study.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		boolean loginResult = memberService.login(memberDTO);
		
		if(loginResult) {
			session.setAttribute("loginEmail", memberDTO.getMemberEmail());
			return "/member/main";
		}else {
			return "/member/login";
		}
	}
	
	@GetMapping("/")
	public String finAll(Model model) {
		List<MemberDTO> memberList = memberService.findAll();
		model.addAttribute("memberList", memberList);
		return "/member/list";
	}
	
	@GetMapping
	public String findById(@RequestParam("id") long id, Model model) {
		MemberDTO member = memberService.findById(id);
		model.addAttribute("member", member);
		return "/member/detail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") long id) {
		memberService.delete(id);
		return "redirect:/";
	}
	
	@GetMapping("/update")
	public String updateForm(HttpSession session, Model model) {
		String loginEmail = (String)session.getAttribute("loginEmail");
		MemberDTO memberDTO = memberService.findByEmail(loginEmail);
		model.addAttribute("member", memberDTO);
		
		return "/member/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute MemberDTO memberDTO) {
		boolean result = memberService.update(memberDTO);
		if(result) {
			return "redirect:/member?id=" + memberDTO.getId();
		}else {
			return "/member/index";
		}
	}
	
	@PostMapping("/email-check")
	public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
		System.out.println("memberEmail = " + memberEmail);
		String checkResult = memberService.emailCheck(memberEmail);
		
		System.out.println(checkResult);
		return checkResult;
	}
	
	
}
