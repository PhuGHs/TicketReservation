ALTER TABLE bookings
DROP
CONSTRAINT fk_bookings_on_seat;

ALTER TABLE seats
DROP
CONSTRAINT fk_seats_on_screening;

CREATE TABLE booking_seats
(
    booking_id UUID NOT NULL,
    seat_id    UUID NOT NULL
);

ALTER TABLE seats
    ADD auditorium_id UUID;

ALTER TABLE seats
    ADD number INTEGER;

ALTER TABLE seats
    ADD row VARCHAR(255);

ALTER TABLE seats
    ALTER COLUMN number SET NOT NULL;

ALTER TABLE seats
    ALTER COLUMN row SET NOT NULL;

ALTER TABLE bookings
    ADD screening_id UUID;

ALTER TABLE bookings
    ALTER COLUMN screening_id SET NOT NULL;

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_SCREENING FOREIGN KEY (screening_id) REFERENCES screenings (id);

ALTER TABLE seats
    ADD CONSTRAINT FK_SEATS_ON_AUDITORIUM FOREIGN KEY (auditorium_id) REFERENCES auditoriums (id);

ALTER TABLE booking_seats
    ADD CONSTRAINT fk_boosea_on_booking FOREIGN KEY (booking_id) REFERENCES bookings (id);

ALTER TABLE booking_seats
    ADD CONSTRAINT fk_boosea_on_seat FOREIGN KEY (seat_id) REFERENCES seats (id);

ALTER TABLE seats
DROP
COLUMN is_available;

ALTER TABLE seats
DROP
COLUMN screening_id;

ALTER TABLE seats
DROP
COLUMN seat_number;

ALTER TABLE seats
DROP
COLUMN version;

ALTER TABLE bookings
DROP
COLUMN seat_id;

ALTER TABLE images
    ALTER COLUMN resource_id DROP NOT NULL;