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
		return "redirect:/wordle";
	}

	@PostMapping("/checkReset")
	public String checkResetPost() {
		wordleService.resetWordle();
		return "redirect:/wordle";
	}

	@GetMapping("/wordle")
	public ModelAndView wordle(ModelAndView mv) {
		mv.setViewName("wordle");
		mv.addObject("wordle", wordleService.getWordle());
		if (!wordleService.getWordle().isVictory())
			if (wordleService.getWordle().getWords().size() + 1 > wordleService.getWordle().getMaxTries())
				mv.addObject("gameOver", true);
			else
				mv.addObject("gameOver", false);

		mv.addObject("victory", wordleService.getWordle().isVictory());
		return mv;
	}

	@PostMapping("/wordle")
	public ModelAndView wordlePost(@ModelAttribute("word") String word, ModelAndView mv) {
		mv.setViewName("wordle");
		wordleService.wordle(word, wordleService.getWordle());
		mv.addObject("wordle", wordleService.getWordle());
		if (!wordleService.getWordle().isVictory())
			if (wordleService.getWordle().getWords().size() + 1 > wordleService.getWordle().getMaxTries())
				mv.addObject("gameOver", true);
			else
				mv.addObject("gameOver", false);

		mv.addObject("victory", wordleService.getWordle().isVictory());

		return mv;
	}

	@GetMapping("/searchTry")
	public ModelAndView searchTry(ModelAndView mv) {
		mv.setViewName("searchTry");
		mv.addObject("tries", wordleService.getWordle().getWords().size());
		if (wordleService.getWordle().getWords().size() > 1) {
			mv.addObject("try", 1);
			mv.addObject("searchTry", wordleService.getWordle().getWords().get(0).getLetters());
		}
		return mv;
	}

	@PostMapping("/searchTry")
	public ModelAndView searchTryPost(@ModelAttribute("try") int trySearch, ModelAndView mv) {
		mv.setViewName("searchTry");
		if (!wordleService.getWordle().getWords().isEmpty())
			mv.addObject("searchTry", wordleService.getWordle().getWords().get(trySearch - 1).getLetters());
		
		mv.addObject("tries", wordleService.getWordle().getWords().size());
		mv.addObject("try", trySearch);
		return mv;
	}

}