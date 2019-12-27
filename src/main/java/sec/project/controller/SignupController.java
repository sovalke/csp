package sec.project.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadForm1() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm2() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, Model model) {
        Signup sih = new Signup(name, address);
        signupRepository.save(sih);
        model.addAttribute("details", signupRepository.findOne(sih.getId()));
        return "done";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allSignups(Model model, HttpSession session) {
        model.addAttribute("all", signupRepository.findAll());
        return "all";
    }

    @RequestMapping(value = "/all/names", method = RequestMethod.GET)
    public String allSignups2(Model model) {
        model.addAttribute("all", signupRepository.findAll());
        return "names";
    }

}
