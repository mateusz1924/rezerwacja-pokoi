package Mateusz.demo.mainController;

import javax.ws.rs.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);
	
	@GET //informacja ze jest to odebranie danych
	@RequestMapping(value = {"/","/index"}) // jesli po localhost:8080 pojawi sie takie wywolanie to ma sie wykonac to co jest nizej
	public String showMainPage() {
		LOG.info("**** WYWOŁANO > showMainPage()");
		return "index";
	}

}
