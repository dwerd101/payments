CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE user
(
    id         BIGINT    DEFAULT global_seq.nextval PRIMARY KEY,
    phone_number    VARCHAR(255)
);

CREATE TABLE payment
(
    id         BIGINT    DEFAULT global_seq.nextval PRIMARY KEY,
    amount_of_payment      DECIMAL             NOT NULL,
    payment_date TIMESTAMP                NOT NULL,
    comment   VARCHAR(255)            NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);


