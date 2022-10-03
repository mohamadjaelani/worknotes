package com.uncal.dt.remote.sh.cfg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Webscp {
	
	/*@GetMapping("/")
	public String bacaPerintah() {
		return "webscp";
	}*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showview(){
		ModelAndView mv = new ModelAndView();
        mv.setViewName("index.jsp");
        return mv;
    }
}
