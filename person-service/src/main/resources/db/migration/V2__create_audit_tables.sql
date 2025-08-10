-- V2__create_audit_tables.sql

-- Таблица ревизий (revinfo)
CREATE TABLE revinfo (
                         rev      BIGSERIAL PRIMARY KEY,
                         revtstmp BIGINT
);

-- Таблица аудита для address
CREATE TABLE address_AUD (
                             id BIGINT NOT NULL,
                             rev INTEGER NOT NULL,
                             revtype SMALLINT,
                             city VARCHAR(255),
                             PRIMARY KEY (id, rev),
                             FOREIGN KEY (rev) REFERENCES revinfo(rev)
);

-- Таблица аудита для countries
CREATE TABLE countries_AUD (
                               id BIGINT NOT NULL,
                               rev INTEGER NOT NULL,
                               revtype SMALLINT,
                               name VARCHAR(255),
                               alpha2 VARCHAR(2),
                               alpha3 VARCHAR(3),
                               status VARCHAR(255),
                               PRIMARY KEY (id, rev),
                               FOREIGN KEY (rev) REFERENCES revinfo(rev)
);

-- Таблица аудита для individuals
CREATE TABLE individuals_AUD (
                                 id BIGINT NOT NULL,
                                 rev INTEGER NOT NULL,
                                 revtype SMALLINT,
                                 status VARCHAR(255),
                                 phone_number VARCHAR(20),
                                 passport_number VARCHAR(20),
                                 user_id BIGINT,
                                 PRIMARY KEY (id, rev),
                                 FOREIGN KEY (rev) REFERENCES revinfo(rev)
);

-- Таблица аудита для users
CREATE TABLE users_AUD (
                           id BIGINT NOT NULL,
                           rev INTEGER NOT NULL,
                           revtype SMALLINT,
                           filled BOOLEAN,
                           last_name VARCHAR(255),
                           first_name VARCHAR(255),
                           secret_key VARCHAR(255),
                           address_id BIGINT,
                           PRIMARY KEY (id, rev),
                           FOREIGN KEY (rev) REFERENCES revinfo(rev)
);