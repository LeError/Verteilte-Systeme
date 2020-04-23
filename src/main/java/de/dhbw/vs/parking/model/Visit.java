package de.dhbw.vs.parking.model;

import de.dhbw.vs.parking.constants.IPriceConstants;
import de.dhbw.vs.parking.exceptions.CarNotCheckedOutException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@ToString
@EqualsAndHashCode
public class Visit {

    /**
     * Generated Id for Visits
     */
    @Id
    @Getter
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    /**
     * Time at which the user checked his car in. Is set at object creation
     */
    @Getter
    @Column(nullable = false)
    private Date checkIn = new Date();

    /**
     * Time at which the user checks out
     */
    @Setter
    @Getter
    @Column
    private Date checkOut = null;

    /**
     * Indicator if visit was billed already
     */
    @Getter
    @Column
    private boolean payed = false;

    /**
     * Calculates the minutes a visit lasted
     * @return time stayed in minutes
     * @throws CarNotCheckedOutException thrown when the visit is not finished
     */
    public Long getStayedMinutes() throws CarNotCheckedOutException {
        if (checkOut == null) {
            throw new CarNotCheckedOutException("Could not calculate minutes of visit stay because it is not finished. Car is not checked out");
        }
        return TimeUnit.MINUTES.convert(Math.abs(checkOut.getTime() - checkIn.getTime()), TimeUnit.MILLISECONDS);
    }

    /**
     * Calculates Price for the visit
     * @return price of the stay in currency if payed individual
     * @throws CarNotCheckedOutException thrown when the visit is not finished
     */
    public Float getIndividualPrice() throws CarNotCheckedOutException {
        if (checkOut == null) {
            throw new CarNotCheckedOutException("Could not calculate price of visit stay because it is not finished. Car is not checked out");
        }
        long priceUnits = getStayedMinutes() / IPriceConstants.minutesPerPriceUnit;
        if(getStayedMinutes() % IPriceConstants.minutesPerPriceUnit > 0) {
            priceUnits++;
        }
        return IPriceConstants.price * priceUnits;
    }

    /**
     * Signalises if the Visit is finished
     * @return weather the visit is finished or not
     */
    public Boolean isVisitFinished() {
        return checkOut != null;
    }

    /**
     * Sets the visit state to billed
     */
    public void setPayed() {
        payed = true;
    }

}
