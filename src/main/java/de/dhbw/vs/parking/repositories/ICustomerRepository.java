package de.dhbw.vs.parking.repositories;

import de.dhbw.vs.parking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, BigInteger> {

}
