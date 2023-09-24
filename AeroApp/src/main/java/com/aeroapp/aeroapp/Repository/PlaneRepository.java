package com.aeroapp.aeroapp.Repository;

import com.aeroapp.aeroapp.Entity.Plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long> {
}
