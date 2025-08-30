package org.saran.insurancetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class PolicyAPI {
    @Autowired
    private PolicyService policyService;

    // Add data
    @GetMapping("/new")
    public String newPolicy(Model model) {
        Insurance insurance = new Insurance();
        model.addAttribute("insurance", insurance);
        return "InsuranceForm";
    }

    @PostMapping("/open")
    public String openInsurance(Model model, Insurance insurance) {
        System.out.println(insurance);
        Insurance recieved = policyService.implementSave(insurance);
        return "redirect:/api/view";
    }

    // Read Data
    @GetMapping("/view")
    public String ViewPolicy(Model model) {
        List<Insurance> insurances = policyService.findAll();
        model.addAttribute("insurances", insurances);
        return "viewPolicy";
    }

    // Edit Data using Primary key as Insurance ID
    @GetMapping("/edit/{key}")
    public String editPolicy(Model model, @PathVariable("key") int key) {
        Optional<Insurance> insurance = policyService.findById(key);
        if (insurance.isPresent()) {
            model.addAttribute("Oldvalue", insurance.get());
            return "editPolicy";
        }
        return "redirect:/api/view";
    }

    @GetMapping("/delete/{key}")
    public String deletePolicy(Model model, @PathVariable("key") int key) {
        Optional<Insurance> insurance = policyService.findById(key);
        if (insurance.isPresent()) {
            model.addAttribute("Oldvalue", insurance.get());
            return "deletePolicy"; // shows confirmation
        }
        return "redirect:/api/view";
    }

    @PostMapping("/delete/{key}")
    public String confirmDelete(@PathVariable("key") int key) {
        policyService.deleteById(key);
        return "redirect:/api/view";
    }
}
