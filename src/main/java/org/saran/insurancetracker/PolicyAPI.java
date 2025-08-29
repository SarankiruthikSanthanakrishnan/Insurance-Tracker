package org.saran.insurancetracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class PolicyAPI {
    @Autowired
    private PolicyService policyService;
    //Add data
    @GetMapping("/new")
    public String newPolicy(Model model) {
        Insurance insurance = new Insurance();
        model.addAttribute("insurance", insurance);
        return "InsuranceForm";
    }



    @PostMapping("/open")
    public String newInsurance(Insurance insurance,Model model)
    {
        Insurance received = policyService.implementSave(insurance);

        if(received!=null)
            model.addAttribute("insurance","success");
        else
            model.addAttribute("insurance","failure");
        return "redirect:/new";
    }
    //Read Data
    @GetMapping("/view")
    public String ViewPolicy(Model model){
        List<Insurance> insurances = policyService.findAll();
        model.addAttribute("insurances",insurances);
        return "viewPolicy";
    }

}
