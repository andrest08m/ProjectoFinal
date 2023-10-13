package com.aeroapp.aeroapp.Repository;

import com.aeroapp.aeroapp.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends JpaRepository<Flight, Long> {


}
