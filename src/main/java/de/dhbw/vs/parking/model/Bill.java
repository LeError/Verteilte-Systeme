package de.dhbw.vs.parking.model;

import de.dhbw.vs.parking.constants.IPriceConstants;
import de.dhbw.vs.parking.exceptions.CarNotCheckedOutException;
import de.dhbw.vs.parking.exceptions.IllegalDataStateException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Bill {

    public Bill(Date startAccountingPeriod, Date endAccountingPeriod, List<Car> cars) {
        this.startAccountingPeriod = startAccountingPeriod;
        this.endAccountingPeriod = endAccountingPeriod;
        for(Car car : cars) {
            visits.addAll(car.getNotBilledVisits());
        }
        for(Visit visit : visits) {
            visit.setPayed();
        }
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column
    private Date createdAt = new Date();

    @Getter
    @Column
    private Date startAccountingPeriod;

    @Getter
    @Column
    private Date endAccountingPeriod;

    @OneToMany
    private List<Visit> visits = new ArrayList<>();

    public Long getTotalAmount() throws IllegalDataStateException {
        long totalMinutes = 0;
        for(Visit visit : visits) {
            try {
                totalMinutes += visit.getStayedMinutes();
            } catch(CarNotCheckedOutException e) {
                throw new IllegalDataStateException("Not checked out visit in Bill. Illegal State!");
            }
        }
        return  totalMinutes;
    }

    public Float getTotal() throws IllegalDataStateException {
        long totalMinutes = 0;
        for(Visit visit : visits) {
            try {
                totalMinutes += visit.getStayedMinutes();
            } catch(CarNotCheckedOutException e) {
                throw new IllegalDataStateException("Not checked out visit in Bill. Illegal State!");
            }
        }
        long totalPriceUnits = totalMinutes / IPriceConstants.minutesPerPriceUnit;
        if(totalMinutes % IPriceConstants.minutesPerPriceUnit > 0) {
            totalPriceUnits++;
        }
        return totalPriceUnits * IPriceConstants.price;
    }

}
