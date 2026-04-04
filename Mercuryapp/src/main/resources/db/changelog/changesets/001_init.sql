--liquibase formatted sql

--changeset tropia:1
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(64) NOT NULL UNIQUE,
    email      VARCHAR(64) NOT NULL UNIQUE,
    password   TEXT        NOT NULL,
    role       VARCHAR(16) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
--changeset tropia:2
CREATE TABLE company
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(64)  NOT NULL UNIQUE,
    address     VARCHAR(128) NOT NULL,
    director_id BIGINT REFERENCES users (id),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
--changeset tropia:3
CREATE TABLE tasks
(
    id          BIGSERIAL PRIMARY KEY,
    assigned_by BIGINT REFERENCES users (id),
    worker_id   BIGINT REFERENCES users (id),
    status      VARCHAR(16)  NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    due_date    TIMESTAMP,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
--changeset tropia:4
CREATE TABLE company_workers
(
    company_id BIGINT REFERENCES company (id),
    user_id    BIGINT REFERENCES users (id),
    PRIMARY KEY (company_id, user_id)
);
