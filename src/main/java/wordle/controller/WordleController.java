package wordle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WordleController {
	

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
}