package project.home.webapp.controller;

import static java.nio.file.Files.size;
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
import project.home.webapp.data.BmiSessionData;
import project.home.webapp.repository.BmiRepository;

@Controller
public class WebController {

    @Autowired   
    private BmiSessionData bmidata;

    private void fillGraphBmi(Model model) {
   
    List<Bmi> bmis = bmidata.getBmis();
    
    int size = bmis.size();
    
    String[] datum = new String[size];
    Double[] bmi = new Double[size];
    
    
    for (int pos = 0; pos < bmis.size(); pos++){
        Bmi bmiz = bmis.get(pos);
        datum[pos] = bmiz.getTageszeit().toString();
        bmi[pos] = bmiz.getBmi();
    }
            
        
    model.addAttribute("bmiGewicht", bmi);
    model.addAttribute("grapDatum",datum);
   
    }
    
    @GetMapping({"/start", "/"})
    public String start(Model model) {
        model.addAttribute("item", new Bmi()); 
       return "start";
    }

    
    // Contact Page -- obsolete -- 
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
        BmiSessionData.save(info);
        bmis.save(info);
        bmidata.save(info);

        List<Bmi> bmis = bmidata.findAll();
        model.addAttribute("bmis", bmis);
        model.addAttribute("item", info);
        fillGraphBmi(model);               
                
        return "ausgabebmi";
    }

    @GetMapping({"/ausgabebmi"})
    public String linkausgabebmi(Model model) {
        model.addAttribute("item", new Bmi());
        List<Bmi> bmis = bmiRepository.findAll();
        model.addAttribute("bmis", bmis);

        fillGraphBmi(model);
        
        return "ausgabebmi";
    }

    @GetMapping("delete")
    public String delete(Model model,
            @RequestParam(name = "id") int id) {
        Bmi last = bmidata.findAll().get((int) bmiRepository.count() - 1);
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
