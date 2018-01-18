package io.peter.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.peter.domain.BoardVO;
import io.peter.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		
		logger.info("register get .............");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		
		logger.info("regist post ..............");
		logger.info(board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("msg", "success"); 
		// Model 객체에 addAttribute를 이용하여 전달한 뒤 redirect를 하면 url에 쿼리스트링이 남아서 보기에 안좋음.
		// 따라서 위와 같이 RedirectAttributes의 addFlashAttribute 메소드를 이용하여 쿼리스트링이 노출되지 않고 뷰로 전달하는 것이 좋음.
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		
		logger.info("show all list .............");
		model.addAttribute("list", service.listAll());
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		logger.info("/read is called. bno : " + bno);
		
		model.addAttribute(service.read(bno)); // view에서 boardVO로 받음.
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception{
		logger.info("/remove is called. bno : " + bno);
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception{
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		service.modify(board);
		
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listAll";
	}
	
	
}
