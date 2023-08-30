package com.vuelos.vuelos.Repository;

import com.vuelos.vuelos.Entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, String> {
}
