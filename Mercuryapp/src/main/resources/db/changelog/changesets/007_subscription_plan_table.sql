--liquibase formatted sql

--changeset tropia:11
ALTER TABLE subscription_plans
    ADD COLUMN duration_value INT NOT NULL,
    ADD COLUMN duration_unit VARCHAR(16) NOT NULL