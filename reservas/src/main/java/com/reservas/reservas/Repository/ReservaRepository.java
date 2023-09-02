package com.reservas.reservas.Repository;

import com.reservas.reservas.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {

}
