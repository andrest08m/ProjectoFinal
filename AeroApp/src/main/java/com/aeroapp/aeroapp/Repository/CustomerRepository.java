package com.aeroapp.aeroapp.Repository;

import com.aeroapp.aeroapp.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
