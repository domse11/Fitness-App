package project.home.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.home.webapp.data.Bmi;
import project.home.webapp.repository.BmiRepository;

@Controller
public class WebController {

    @Autowired
    private BmiRepository bmiRepository;

    private void fülleBmi(Model model) {
    //bmi.addAttribute("bmiGewicht", bmi.getGewicht());
    //bmi.addAttribute("bmiGroesse", bmi.getGroesse());
    
    
    
    
    
    
    
    
    
    
    }
    
    @GetMapping({"/start", "/"})
    public String start(Model model) {
        model.addAttribute("item", new Bmi());       
        return "start";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("item", new Bmi());
        return "contact";
    }

    // alte Startseite (Test)
    /*@GetMapping("/index")
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
    //Testen ob Daten in die DB geschrieben worden sind
    /*
    @GetMapping("/bmi")
    public String showBmi(Model model) {

        List<Bmi> bmis = bmiRepository.findAll();
        model.addAttribute("bmis", bmis);

        return "show_bmi";
    }
     */
    private List<String> bmis = new ArrayList<>();

    @PostMapping("/ausgabebmi")
    public String ausgabebmi(Model model,
            @Valid Bmi info,
            BindingResult bindingResult) {
        /* Thomas 
        model.addAttribute("message", "Hallo Welt");*/
        if (bindingResult.hasErrors()) {
            bindingResult.addError(new ObjectError("bmi", "Fehler!!!"));
            System.out.println("Fehler bei Validierung");
            model.addAttribute("errorMsg", "Fehler beim Speichern (Validierungsfehler)!");
            bindingResult.rejectValue("bmi", "not.valid");
            model.addAttribute("item", info);
            return "redirect:/start";
        }
        info.setTageszeit();
        info.getBmi();
        /*Bmi ausgabe auf der Console
        System.out.println(info.toString());*/
        bmiRepository.save(info);

        List<Bmi> bmis = bmiRepository.findAll();
        model.addAttribute("bmis", bmis);
        model.addAttribute("item", info);
        
        return "ausgabebmi";
    }

    @GetMapping({"/ausgabebmi"})
    public String linkausgabebmi(Model model) {
        model.addAttribute("item", new Bmi());
        List<Bmi> bmis = bmiRepository.findAll();
        model.addAttribute("bmis", bmis);

        return "ausgabebmi";
    }

    @GetMapping("delete")
    public String delete(Model model,
            @RequestParam(name = "id") int id) {
        Bmi last = bmiRepository.findAll().get((int) bmiRepository.count() - 1);
        bmiRepository.deleteById(id);
        if (id == last.getId()) {
            return "redirect:/start";
        }
        return "redirect:/ausgabebmi";
    }

    //testdaten
    @GetMapping("/ausgabebmi/bmiliste")
    public String createBmi() {
        int pos = 0;
        Bmi bmi = new Bmi(++pos, 180, 90);

        bmiRepository.save(bmi);

        return "bmi_created";
    }
}
