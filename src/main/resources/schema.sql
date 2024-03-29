DROP TABLE IF EXISTS rent;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS company_branch;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS car_rental;

CREATE TABLE car_rental
(
    id              BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    internet_domain VARCHAR(255) NOT NULL,
    address         VARCHAR(255) NOT NULL,
    owner           VARCHAR(255) NOT NULL
);

CREATE TABLE car
(
    id                 BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    brand              VARCHAR(50) NOT NULL,
    model              VARCHAR(50) NOT NULL,
    type               VARCHAR(50) NOT NULL,
    year_of_production INT         NOT NULL,
    color              VARCHAR(50) NOT NULL,
    car_mileage        INT         NOT NULL,
    status             TINYINT     NOT NULL,
    price              DECIMAL(7, 2)
);

CREATE TABLE company_branch
(
    id             BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    branch_address VARCHAR(255) NOT NULL,
    car_rental_id  BIGINT,
    FOREIGN KEY (car_rental_id) REFERENCES car_rental (id)
);

CREATE TABLE employee
(
    id                BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name              VARCHAR(50) NOT NULL,
    surname           VARCHAR(50) NOT NULL,
    job_position      TINYINT     NOT NULL,
    company_branch_id BIGINT,
    FOREIGN KEY (company_branch_id) REFERENCES company_branch (id)
);

CREATE TABLE reservation
(
    id              BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    customer        VARCHAR(255)  NOT NULL,
    start_date      DATE          NOT NULL,
    end_date        DATE          NOT NULL,
    loan_amount          DECIMAL(7, 2) NOT NULL,
    start_branch_id BIGINT,
    end_branch_id   BIGINT,
    car_id          BIGINT,
    FOREIGN KEY (start_branch_id) REFERENCES company_branch (id),
    FOREIGN KEY (end_branch_id) REFERENCES company_branch (id),
    FOREIGN KEY (car_id) REFERENCES car (id)
);

CREATE TABLE rent
(
    id             BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    employee_id    BIGINT,
    comments       VARCHAR(255) NOT NULL,
    rent_date      DATE         NOT NULL,
    reservation_id BIGINT       NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);




