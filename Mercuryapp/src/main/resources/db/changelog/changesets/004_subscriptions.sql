--liquibase formatted sql

--changeset tropia:7
CREATE TABLE subscription_plans(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL ,
    currency varchar(16) NOT NULL,
    max_workers INTEGER NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

--changeset tropia:8
CREATE TABLE subscriptions(
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT REFERENCES company(id) NOT NULL ,
    plan_id BIGINT REFERENCES subscription_plans(id) NOT NULL ,
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP,
    active BOOLEAN NOT NULL DEFAULT TRUE
);