ALTER TABLE images
    ADD resource_type VARCHAR(255);

ALTER TABLE images
    ALTER COLUMN resource_id DROP NOT NULL;