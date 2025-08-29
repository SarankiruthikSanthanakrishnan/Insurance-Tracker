package org.saran.insurancetracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRemote extends JpaRepository<Insurance, Integer> {
}
