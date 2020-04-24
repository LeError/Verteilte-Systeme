package de.dhbw.vs.parking.services.impl;

import de.dhbw.vs.parking.services.IBillService;
import de.dhbw.vs.parking.services.IScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service public class ScheduleService
implements IScheduleService {

    private final IBillService billService;

    public ScheduleService (IBillService billService) {
        this.billService = billService;
    }

    @Override @Scheduled (cron = "0 0 */5 8 0") public void triggerAccounting () {
        //TODO
    }

}
