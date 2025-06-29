ALTER TABLE screenings
    ADD price DECIMAL;

ALTER TABLE screenings
    ALTER COLUMN price SET NOT NULL;