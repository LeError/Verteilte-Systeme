package de.dhbw.vs.parking.repositories;

import de.dhbw.vs.parking.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends JpaRepository<Car, String> {
}
