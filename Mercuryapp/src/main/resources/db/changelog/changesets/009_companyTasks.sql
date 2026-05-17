--liquibase formatted sql

--changeset tropia:13
ALTER TABLE tasks
    ADD COLUMN company_id BIGINT REFERENCES company(id);