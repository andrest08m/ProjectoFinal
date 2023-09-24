package com.aeroapp.aeroapp.Repository;

import com.aeroapp.aeroapp.Entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {


}
