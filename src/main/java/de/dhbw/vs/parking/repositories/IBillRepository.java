package de.dhbw.vs.parking.repositories;

import de.dhbw.vs.parking.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface IBillRepository extends JpaRepository<Bill, BigInteger> {

}
