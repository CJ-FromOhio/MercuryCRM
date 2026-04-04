--liquibase formatted sql

--changeset tropia:1
ALTER TABLE users
    ADD COLUMN firstname VARCHAR(64) NOT NULL default 'firstname',
    ADD COLUMN lastname VARCHAR(64) NOT NULL default 'lastname'
