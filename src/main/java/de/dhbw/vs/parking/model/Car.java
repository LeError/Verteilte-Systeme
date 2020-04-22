package de.dhbw.vs.parking.model;

import de.dhbw.vs.parking.exceptions.IllegalDataStateException;
import de.dhbw.vs.parking.utli.PredicateUtils;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;



@Entity
@NoArgsConstructor
public class Car {

    @Id
    @Column
    private String licensePlate;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Visit> visits = new HashSet<>();

    public Set<Visit> getBilledVisits() {
        return visits.stream().filter(Visit::isPayed).collect(Collectors.toSet());
    }

    public Set<Visit> getNotBilledVisits() {
        return visits.stream().filter(PredicateUtils.not(Visit::isPayed)).filter(Visit::isVisitFinished).collect(Collectors.toSet());
    }

    public Set<Visit> getVisits() {
        return visits.stream().filter(Visit::isVisitFinished).collect(Collectors.toSet());
    }

    public Visit getOngoingVisit() throws IllegalDataStateException {
        Set<Visit> visits = this.visits.stream().filter(PredicateUtils.not(Visit::isVisitFinished)).collect(Collectors.toSet());
        if(visits.size() > 2) {
            throw new IllegalDataStateException("Two unfinished visits with one car are impossible. Illegal State!");
        }
        if(visits.isEmpty()) {
            return null;
        }
        return visits.stream().findFirst().get();
    }

}
