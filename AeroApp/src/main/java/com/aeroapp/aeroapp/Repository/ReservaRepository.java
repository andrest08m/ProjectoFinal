package com.aeroapp.aeroapp.Repository;

import com.aeroapp.aeroapp.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reservation,Long> {

}
