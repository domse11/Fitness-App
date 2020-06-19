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
import project.home.webapp.data.BmiSessionData;

@Controller
public class WebController {

    @Autowired
    private BmiSessionData bmidata;

    private void fillGraphBmi(Model model) {

        List<Bmi> bmis = bmidata.getBmis();

        int size = bmis.size();

        String[] datum = new String[size];
        Double[] bmi = new Double[size];

        for (int pos = 0; pos < bmis.size(); pos++) {
            Bmi bmiz = bmis.get(pos);
            datum[pos] = bmiz.getTageszeit().toString();
            bmi[pos] = bmiz.getBmi();
        }

        model.addAttribute("graphBmi", bmi);
        model.addAttribute("graphDatum", datum);

    }

    @GetMapping({"/start", "/"})
    public String start(Model model) {
        model.addAttribute("item", new Bmi());
        return "start";
    }

    // Contact Page -- obsolete --
    /*@GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("item", new Bmi());
        return "contact";
    }*/
    // Contact Page -- obsolte --
    // old Startpage -- obsolte --
    /*@GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("item", new Bmi());
        return "index";
    }
     // old Startpage -- obsolte --

    // Test if data was stored in DB
    /*
    @GetMapping("/bmi")
    public String showBmi(Model model) {

        List<Bmi> bmis = bmiRepository.findAll();
        model.addAttribute("bmis", bmis);

        return "show_bmi";
    }
     */
    // Test if data was stored in DB END
    private List<String> bmis = new ArrayList<>();

    @PostMapping("/ausgabebmi")
    public String ausgabebmi(Model model,
            @Valid @ModelAttribute("item") Bmi info,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {//
            System.out.println("Fehler bei Validierung");
            model.addAttribute("item", info);
            return "start";
        }
        info.setTageszeit();
        info.getBmi();
        /*Bmi ausgabe auf der Console
        System.out.println(info.toString());*/
        bmidata.updateBmi(info);

        List<Bmi> bmis = bmidata.getBmis();
        showBMI(model, info);
        model.addAttribute("bmis", bmis);
        model.addAttribute("item", info);
        fillGraphBmi(model);

        return "ausgabebmi";
    }

    @GetMapping({"/ausgabebmi"})
    public String linkausgabebmi(Model model) {
        model.addAttribute("item", new Bmi());
        // List<Bmi> bmis = bmiRepository.findAll();
        List<Bmi> bmis = bmidata.getBmis();

        // 0 - 1 ist ein doofer index
        Bmi info = bmis.get(bmis.size() - 1);
        showBMI(model, info);
        model.addAttribute("item", info);
        model.addAttribute("bmis", bmis);
        fillGraphBmi(model);

        return "ausgabebmi";
    }

    @GetMapping("delete")
    public String delete(Model model,
            @RequestParam(name = "id") int id) {
        Bmi last = bmidata.getlast();
        bmidata.deleteBmi(id);
        if (id == last.getId()) {
            return "redirect:/start";
        }
        return "redirect:/ausgabebmi";
    }

    /* //testdaten
	@GetMapping("/ausgabebmi/bmiliste")
	public String createBmi() {
		int pos = 0;
		Bmi bmi = new Bmi(++pos, 180, 90);

		bmiRepository.save(bmi);

		return "bmi_created";
	}
     */
    private void showBMI(Model model, Bmi item) {
        String classBMI = "";
        String bemerkung = "";
        String bemerkunglang = "";
        double bmi = item.getBmi();
        if (bmi < 16.0) {
            classBMI = "Starkes Untergewicht!";
            bemerkung = "Ihr Gewicht ist gesundheitsgefährdend gering.";
            bemerkunglang = "Bitte begeben Sie sich in ärztliche Hände!";
        } else if (bmi >= 16.0 && bmi < 18.5) {
            classBMI = "Untergewicht";
            bemerkung = "Ihr Gewicht bewegt sich im unteren Segment.";
            bemerkunglang = "Nehmen sie mehr Kalorien zu sich!";
        } else if (bmi >= 18.5 && bmi < 25.0) {
            classBMI = "Normalgewicht";
            bemerkung = "Ihr Gewicht befindet sich im Normalbereich.";
            bemerkunglang = "Geben Sie weiterhin gut auf sich acht.";
        } else if (bmi >= 25.0 && bmi < 30.0) {
            classBMI = "Übergewicht - Präadipositas";
            bemerkung = "Sie sind leicht übergewichtig.";
            bemerkunglang = "Achten Sie auf eine ausgewogene Ernährungsweise und versuchen Sie mehr Sport zu treiben!";
        } else if (bmi >= 30.0 && bmi < 35.0) {
            classBMI = "Adipositas Grad  I";
            bemerkung = "Ihr Risiko für Folgeerkrankungen ist erhöht.";
            bemerkunglang = "Eine Gewichtsreduktion wird stark empfohlen.";
        } else if (bmi >= 35.0 && bmi < 40.0) {
            classBMI = "Adipositas Grad  II";
            bemerkung = "Sie haben ein erhöhtes Risiko eine Herz-Kreislauf Erkrankung zu entwickeln.";
            bemerkunglang = "Reduzieren sie ihr Gewicht und achten sie auf einen gesünderen Lebensstil!";
        } else if (bmi >= 40.0) {
            classBMI = "Adipositas Grad III";
            bemerkung = "Ihr Gewicht ist stark erhöht.";
            bemerkunglang = "Konsultieren Sie Ihren Arzt und nehmen Sie ab um Folgeerkrankungen zu vermeiden!";

        }
        model.addAttribute("bemerkung", bemerkung);
        model.addAttribute("class", classBMI);
        model.addAttribute("bemerkunglang", bemerkunglang);
    }
}
