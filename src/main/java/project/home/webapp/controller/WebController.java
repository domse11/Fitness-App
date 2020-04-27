package project.home.webapp.controller;

import javax.validation.Valid;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.home.webapp.data.Bmi;

@Controller
@ComponentScan
@EnableAutoConfiguration
public class WebController {
    
   @GetMapping({"/index","/"})
   public String index(Model model) {
       model.addAttribute("item", new Bmi());
       return "index";
   }
    
   
   @GetMapping("/start")
   public String start(Model model) {
       model.addAttribute("item", new Bmi());
       return "start";
   }
     
}
