--liquibase formatted sql

--changeset tropia:12
ALTER TABLE payment_transaction
    ADD COLUMN company_id BIGINT REFERENCES company(id)