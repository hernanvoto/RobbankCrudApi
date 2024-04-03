package com.robbank.crudApis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robbank.crudApis.model.PersonalCustomer;

@Repository
public interface PersonalCustomerRepository extends JpaRepository<PersonalCustomer, Long> {

    Optional<PersonalCustomer> findByName(final String firstName, final String lastName);
}