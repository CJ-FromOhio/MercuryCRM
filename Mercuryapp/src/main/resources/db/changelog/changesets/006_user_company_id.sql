--liquibase formatted sql

--changeset tropia:10
ALTER TABLE users
    ADD COLUMN company_id BIGINT REFERENCES company(id);