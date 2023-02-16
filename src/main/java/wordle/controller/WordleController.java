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

	@GetMapping("/checkReset")
	public String checkResetGet() {
		return "checkReset";
	}

	@PostMapping("/checkReset")
	public String checkResetPost() {
		wordleService.resetWordle();
		return "index";
	}
	
	@GetMapping("/wordle")
	public ModelAndView wordle(ModelAndView mv) {
		mv.setViewName("wordle");
		mv.addObject("wordle", wordleService.getWordle());
		return mv;
	}

	@PostMapping("/wordle")
	public ModelAndView wordlePost(@ModelAttribute("word") String word, ModelAndView mv) {
		mv.setViewName("wordle");
		wordleService.wordle(word);
		mv.addObject("wordle", wordleService.getWordle());
		return mv;
	}

}