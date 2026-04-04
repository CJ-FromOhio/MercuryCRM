--liquibase formatted sql

--changeset tropia:6
CREATE TABLE clients(
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(64) not null,
    lastname VARCHAR(64) not null,
    phone_number VARCHAR(32) not null,
    email VARCHAR(64) not null,
    company_id BIGINT REFERENCES company(id)
);

--changeset tropia:7
ALTER TABLE tasks
    ADD COLUMN client_id BIGINT REFERENCES clients(id);