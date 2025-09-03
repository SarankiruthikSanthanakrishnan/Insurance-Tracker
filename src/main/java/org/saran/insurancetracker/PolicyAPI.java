package org.saran.insurancetracker;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class PolicyAPI {
    @Autowired
    private PolicyService policyService;

    //Home Page
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Add data
    @GetMapping("/create")
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
            return "deletePolicy";
        }
        return "redirect:/api/view";
    }

    @PostMapping("/delete/{key}")
    public String confirmDelete(@PathVariable("key") int key) {
        policyService.deleteById(key);
        return "redirect:/api/view";
    }


//    //based on Scheme Type
//    @GetMapping("/view/types")
//    public String readByTypes(Model model, @RequestParam String policytype) {
//        List<Insurance> insurances = policyService.implementFindAllBySchemeType(policytype);
//        model.addAttribute("insurances",insurances);
//        return "viewPolicy";
//    }
//
//    //Based on Amount
//    @GetMapping("/view/amount")
//    public String readByAmount(Model model, @RequestParam double amount1 ,@RequestParam double amount2) {
//        List<Insurance> insurances = policyService.implementBasedPolicyAmount(amount1,amount2);
//        model.addAttribute("insurances",insurances);
//        return "viewPolicy";
//    }
    //Search based on value
    @GetMapping("/view/search")
    public String readBySearch(Model model, @RequestParam String name) {
        List<Insurance> insurances = policyService.findValue(name);
        model.addAttribute("insurances",insurances);
        return "viewPolicy";

    }
    @GetMapping("/view/status")
    public String readByStatus(Model model,
                               @RequestParam(defaultValue = "All") String status) {
        List<Insurance> insurances;

        if ("All".equalsIgnoreCase(status)) {
            insurances = policyService.findAll();
        } else {
            insurances = policyService.findStatus(status);
        }

        model.addAttribute("insurances", insurances);
        model.addAttribute("status", status);
        return "viewPolicy";
    }
    //sorting
    @GetMapping("/view/sort")
    public String sortPolicies(Model model, @RequestParam(defaultValue = "asc") String order) {
        List<Insurance> insurances;
        if (order.equals("desc")) {
            insurances = policyService.getAllPoliciesSortedDesc();
        } else {
            insurances = policyService.getAllPoliciesSortedAsc();
        }
        model.addAttribute("insurances", insurances);
        model.addAttribute("order", order);
        return "viewPolicy";

    }
    }
