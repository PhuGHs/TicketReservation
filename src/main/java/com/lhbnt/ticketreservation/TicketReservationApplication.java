package com.lhbnt.ticketreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class TicketReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketReservationApplication.class, args);
    }

}
