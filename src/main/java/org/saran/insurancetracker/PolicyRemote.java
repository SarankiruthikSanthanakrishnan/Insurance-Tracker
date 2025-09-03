package org.saran.insurancetracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PolicyRemote extends JpaRepository<Insurance, Integer> {

    List<Insurance> findAllByPolicyType(String policyType);

    List<Insurance> findAllByPremiumAmountBetween(Double premiumAmount1, Double premiumAmount2);

    @Query(value = "SELECT * FROM insurance WHERE policy_name LIKE %:name% OR policy_type LIKE %:name%",nativeQuery = true)
    List<Insurance> findByNameOrType(String name);
    @Query(value = "SELECT * FROM insurance WHERE status = :code", nativeQuery = true)
    List<Insurance> findByStatusname(String code);
    @Query(value = "SELECT * FROM insurance ORDER BY policy_name ASC", nativeQuery = true)
    List<Insurance> orderByPolicyNameAsc();
    @Query(value = "SELECT * FROM insurance ORDER BY policy_name DESC", nativeQuery = true)
    List<Insurance> orderByPolicyNameDesc();

}
