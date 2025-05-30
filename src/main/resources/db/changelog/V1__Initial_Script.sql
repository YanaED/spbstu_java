--liquibase formatted sql

--changeset init:1

CREATE TABLE IF NOT EXISTS users
(
    id       VARCHAR PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tasks
(
    id           UUID PRIMARY KEY,
    user_id      VARCHAR NOT NULL,
    title        VARCHAR,
    created_at   TIMESTAMP,
    target_date  TIMESTAMP,
    is_completed BOOLEAN,
    is_deleted   BOOLEAN
);

CREATE TABLE IF NOT EXISTS notifications
(
    id      UUID PRIMARY KEY,
    user_id VARCHAR NOT NULL,
    message TEXT,
    is_processed BOOLEAN
);
