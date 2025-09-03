package org.saran.insurancetracker;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class InsuranceRestApi {

    @Autowired
    private PolicyService policyService;

    @GetMapping("/view")
    public List<Insurance> readAllInsurance(){
        return policyService.findAll();
    }
}
