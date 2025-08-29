package org.saran.insurancetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {
    @Autowired
    private PolicyRemote policyRemote;
    public Insurance implementSave(Insurance insurance) {
        return policyRemote.save(insurance);
    }

    public List<Insurance> findAll(){
        return policyRemote.findAll();
    }

}
