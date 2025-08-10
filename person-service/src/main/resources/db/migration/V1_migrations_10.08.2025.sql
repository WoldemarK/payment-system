-- Включаем расширение для генерации UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Создаём схему person, если её нет
CREATE SCHEMA IF NOT EXISTS person;

-- Таблица стран
CREATE TABLE countries
(
    id      UUID PRIMARY KEY,
    created TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name    VARCHAR(64)    NOT NULL,
    alpha2  CHAR(2) UNIQUE NOT NULL,
    alpha3  CHAR(3) UNIQUE NOT NULL,
    status  VARCHAR(16)    NOT NULL DEFAULT 'ACTIVE'
        CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

-- Таблица адресов
CREATE TABLE addresses
(
    id         UUID PRIMARY KEY,
    created    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    country_id UUID         NOT NULL REFERENCES countries (id),
    address    VARCHAR(256) NOT NULL,
    zip_code   VARCHAR(20),
    city       VARCHAR(64)  NOT NULL,
    state      VARCHAR(64),
    archived   TIMESTAMP,
    CONSTRAINT fk_address_country FOREIGN KEY (country_id) REFERENCES countries (id)
);

-- Таблица пользователей
CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    secret_key VARCHAR(256),
    email      VARCHAR(254) NOT NULL UNIQUE,
    created    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    filled     BOOLEAN      NOT NULL DEFAULT FALSE,
    address_id UUID REFERENCES addresses (id),
    CONSTRAINT fk_user_address FOREIGN KEY (address_id) REFERENCES addresses (id)
);

-- Таблица физ. лиц (индивидуумов)
CREATE TABLE individuals
(
    id              UUID PRIMARY KEY,
    user_id         UUID        NOT NULL UNIQUE REFERENCES users (id),
    passport_number VARCHAR(32),
    phone_number    VARCHAR(20),
    verified_at     TIMESTAMP   NOT NULL,
    archived_at     TIMESTAMP, -- NULL = не архивирован
    status          VARCHAR(16) NOT NULL DEFAULT 'ACTIVE'
        CHECK (status IN ('ACTIVE', 'VERIFIED', 'ARCHIVED', 'SUSPENDED')),
    created         TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated         TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);