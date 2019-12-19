
package com.mitrais.smartclinic.repository;

import com.mitrais.smartclinic.model.ClinicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ClinicUser,Long> {
}