--liquibase formatted sql

--changeset tropia:9
CREATE TABLE payment_transaction(
    id BIGSERIAL PRIMARY KEY ,
    transaction_id VARCHAR(64) NOT NULL UNIQUE,
    payment_status VARCHAR(16) NOT NULL,
    subscription_id BIGINT REFERENCES subscriptions(id),
    price DECIMAL(12, 2) NOT NULL,
    currency VARCHAR(8) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
