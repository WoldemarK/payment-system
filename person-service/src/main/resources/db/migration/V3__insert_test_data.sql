-- V3__insert_sample_data.sql
INSERT INTO countries (name, alpha2, alpha3, status)
VALUES ('Russia',
        'RU',
        'RUS',
        'ACTIVE'),
       ('United States',
        'US',
        'USA',
        'ACTIVE');

INSERT INTO addresses (id, created, updated, archived, country_id, address, zip_code, city, state)
VALUES (
           'e8b7d6c5-4f3a-2e1d-0c9b-8a7f6e5d4c3b',
           NOW(),
           NOW(),
           NOW(),
           1,
           'Lenina 10',
           '123456',
           'Moscow',
           'Moscow'
       );

INSERT INTO users (id, secret_key, email, created, updated, first_name, last_name, filled, address_id)
VALUES
    (
        '3f1e2d3c-4b5a-6987-1029-384756654321',
        'secret123',
        'user@example.com',
        NOW(),
        NOW(),
        'Ivan',
        'Ivanov',
        TRUE,
        (SELECT id FROM addresses LIMIT 1));
INSERT INTO individuals (id,user_id, passport_number, phone_number, verified_at, archived_at, status)
VALUES ((SELECT id FROM users LIMIT 1),
        '3f1e2d3c-4b5a-6987-1029-384756654321',
        '1234567890',
        '+79001234567',
        NOW(),
        NOW(),
        'VERIFIED'
       );