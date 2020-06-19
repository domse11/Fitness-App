package project.home.webapp.data;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import project.home.webapp.repository.BmiRepository;

@Component
@SessionScope
public class BmiSessionData {

    @Autowired
    private BmiRepository bmiRepository;

    public BmiSessionData() {
    }

    public void addBmi(Bmi bmi) {
        bmiRepository.save(bmi);
    }

    public void updateBmi(Bmi bmi) {
        bmiRepository.save(bmi);
    }

    public void deleteBmi(int id) {
        bmiRepository.deleteById(id);
    }

    public Bmi getlast() {
        return bmiRepository.findAll().get((int) bmiRepository.count() - 1);
    }

    public List<Bmi> getBmis() {
        return bmiRepository.findAll();
    }

    public Bmi getBmi(int id) {
        Optional<Bmi> itemOpt = bmiRepository.findById(id);
        if (itemOpt.isPresent()) {
            return itemOpt.get();
        }
        return new Bmi();
    }

}
