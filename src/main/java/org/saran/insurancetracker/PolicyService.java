package org.saran.insurancetracker;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public Optional<Insurance> findById(int id){
        return policyRemote.findById(id);
    }

    public void deleteById(int key) {
        policyRemote.deleteById(key);
    }

    public List<Insurance> implementFindAllBySchemeType(String policyType){
        return policyRemote.findAllByPolicyType(policyType);
    }

    public List<Insurance> implementBasedPolicyAmount(double amount1 ,double amount2){
        return policyRemote.findAllByPremiumAmountBetween(amount1, amount2);
    }

    public List<Insurance> findValue(String value){
        return policyRemote.findByNameOrType(value);

    }
    public List<Insurance> findStatus(String status){
        return policyRemote.findByStatusname(status);
    }


    public List<Insurance> getAllPoliciesSortedAsc() {
        return policyRemote.orderByPolicyNameAsc();
    }

    public List<Insurance> getAllPoliciesSortedDesc() {
        return policyRemote.orderByPolicyNameDesc();
    }


    public List<String> getDistinctPolicyNames() {
        return policyRemote.findDistinctPolicyNames();
    }







}

