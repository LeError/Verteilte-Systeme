package de.dhbw.vs.parking.repositories;

import de.dhbw.vs.parking.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface IVisitRepository extends JpaRepository<Visit, BigInteger> {

}
