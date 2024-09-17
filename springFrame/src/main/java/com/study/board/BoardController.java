package com.study.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/index")
	public String index() {
		
		return "/board/index";
	}
	
	@GetMapping("/save")
	public String saveForm() {
		
		return "/board/save";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute BoardDTO boardDTO) {
		
		int saveResult = boardService.save(boardDTO); 
		if(saveResult > 0) {
			return "redirect:/board/paging";
		}else {
			return "save";
		}
	}
	
	
	/*
	@GetMapping("/list")
	public String findAll(@RequestParam(value= "page", required=false, defaultValue="1") int page ,  Model model, SearchDTO search) {
		
		List<BoardDTO> boardList = boardService.findAll();
		model.addAttribute("boardList", boardList);
		System.out.println("리스트 초기");
		
		
		return "/board/list";
	}
	*/
	
	// 상세보기
	@GetMapping
	public String findById(@RequestParam("id") long id, Model model, @ModelAttribute("cri") Criteria cri,
			 @RequestParam(value= "page", required=false, defaultValue="1") int page) {
		
		// 조회수 증가
		boardService.updateHits(id);
		// 전체리스트
		BoardDTO boardDTO = boardService.findById(id);
		
		//댓글 리스트
		List<CommentDTO> commnetList = commentService.findAll(id);
		
		
		model.addAttribute("board", boardDTO);
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("searchName", cri.getSearchName());
		model.addAttribute("page", page);
		model.addAttribute("commentList", commnetList);
		return "/board/detail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		boardService.delete(id);
		return "redirect:/board/list";
	}
	
	@GetMapping("/update")
	public String updateForm(@RequestParam("id") long id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "/board/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		
		boardService.update(boardDTO);
		BoardDTO dto = boardService.findById(boardDTO.getId());
		model.addAttribute("board", dto);
		return "/board/detail";
		//return "redirect:/board?id="+boardDTO.getId();
	}
	
			// /board/paging?page=2 
			// /board/paging?page=1 처음 페이지 요청은 무조건 1페이지를 보여줌
			@GetMapping("/paging")
			public String paging(Model model, @RequestParam(value= "page", required=false, defaultValue="1") int page, SearchDTO search) {
				System.out.println("page = " + page);
				System.out.println("searchPageDTO =");
					// 해당 페이지에서 보여줄 글 목록
					List<BoardDTO> pagingList = boardService.pagingList(page, search);
					
					PageDTO pageDTO = boardService.paginParam(page, search);
					model.addAttribute("boardList", pagingList);
					model.addAttribute("paging", pageDTO);
				
				System.out.println(pageDTO);
				
				return "/board/paging";
			}
	
	@RequestMapping("/list")
	public String findAll(@RequestParam(value= "page", required=false, defaultValue="1") int page,
			@ModelAttribute("cri") Criteria cri, Model model) {
		
			//리스트 목록
			List<HashMap<String, Object>> list = boardService.list(cri);
			System.out.println(" 검색조건 = " + cri.getSearchType());
			System.out.println(" 검색어 = " + cri.getSearchName());
			
			model.addAttribute("list", list);
			
			PageMaker pageMaker = new PageMaker();
			
			//검색조회 수량
			int count = boardService.listCount(cri);
			System.out.println(" 검색된수 = " + count);
			
			pageMaker.setCriteria(cri);
			pageMaker.setTotalCount(count);
			
			
			
			model.addAttribute("boardList", list);
			model.addAttribute("pageMaker", pageMaker);
			
			
			//보면 리스트에서는 서치타입이랑 서치네임을 모델에 담고있음 ㅇㅋ? ㅇㅇ
			model.addAttribute("searchType", cri.getSearchType());
			model.addAttribute("searchName", cri.getSearchName());
			model.addAttribute("page", cri.getPage());
			
		
		return "/board/list";
	}
}
