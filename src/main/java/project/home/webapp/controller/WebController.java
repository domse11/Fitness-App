package project.home.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.home.webapp.data.Bmi;
import project.home.webapp.repository.BmiRepository;

@Controller
public class WebController {

    @Autowired
    BmiRepository bmiRepository;

    @GetMapping({"/start", "/"})
    public String start(Model model) {
        model.addAttribute("item", new Bmi());
        return "start";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("item", new Bmi());
        return "index";
    }

    /*@GetMapping("/")
    public String checkBmiInfo(@Valid Bmi bmi, BindingResult bindingresult){
        if (bindingResult.hasErrors()){
            return "start";
        }
        return "redirect:/ausgabebmi";
    }*/
    
    @GetMapping("/bmi")
    public String showBmi(Model model) {

        List<Bmi> bmis = bmiRepository.findAll();
        model.addAttribute("bmis", bmis);

        return "show_bmi";
    }

    private List<String> bmis = new ArrayList<>();

    @PostMapping("/ausgabebmi")
    public String ausgabebmi(Model model,
            @Valid @ModelAttribute("item") Bmi bmi,
            BindingResult bindingResult) {

        List<Bmi> bmis = bmiRepository.findAll();
        model.addAttribute("bmis", bmis);
       /*model.addAttribute("item", bmi);*/

                     
        return "ausgabebmi";
    }

    @GetMapping("/ausgabebmi/bmiliste")
    public String createBmi() {
// testdaten
        Bmi bmi = new Bmi(190, 90);

        bmiRepository.save(bmi);

        return "bmi_created";
    }

}
