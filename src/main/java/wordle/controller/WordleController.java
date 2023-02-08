package wordle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import wordle.service.IWordleService;

@Controller
public class WordleController {

	@Autowired
	IWordleService wordleService;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/wordle")
	public ModelAndView wordle(ModelAndView mv) {
		return mv;
	}

	@PostMapping("/wordle")
	public ModelAndView wordle(@ModelAttribute String word) {
		ModelAndView mv = new ModelAndView("wordle");
		wordleService.wordle(word);
		return mv;
	}
}