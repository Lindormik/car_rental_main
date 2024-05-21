DROP TABLE IF EXISTS car_return;
DROP TABLE IF EXISTS rent;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS company_branch;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS car_rental;



CREATE TABLE car_rental
(
    id              BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    internet_domain VARCHAR(255) NOT NULL,
    address         VARCHAR(255) NOT NULL,
    owner           VARCHAR(255) NOT NULL
);

-- Adding data to car_rental table
INSERT INTO car_rental (name, internet_domain, address, owner) VALUES
('Best Cars', 'bestcars.com', '1234 Main St', 'John Doe'),
('Quick Rentals', 'quickrentals.com', '5678 Second Ave', 'Jane Smith');

CREATE TABLE customer
(
    id              BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    surname         VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    address         VARCHAR(255) NOT NULL
);

-- Adding data to customer table
INSERT INTO customer (name, surname, email, address) VALUES
('Alice', 'Johnson', 'alice.johnson@example.com', '1234 Maple St'),
('Bob', 'Smith', 'bob.smith@example.com', '5678 Oak St');

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

-- Adding data to car table
INSERT INTO car (brand, model, type, year_of_production, color, car_mileage, status, price) VALUES
('Toyota', 'Corolla', 'Sedan', 2020, 'Red', 15000, 1, 20000.00),
('Ford', 'Mustang', 'Coupe', 2019, 'Blue', 12000, 1, 30000.00);

CREATE TABLE company_branch
(
    id             BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    branch_address VARCHAR(255) NOT NULL,
    car_rental_id  BIGINT,
    FOREIGN KEY (car_rental_id) REFERENCES car_rental (id)
);

-- Adding data to company_branch table
INSERT INTO company_branch (branch_address, car_rental_id) VALUES
('7890 Third Blvd', 1),
('4321 Fourth Ln', 2);

CREATE TABLE employee
(
    id                BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name              VARCHAR(50) NOT NULL,
    surname           VARCHAR(50) NOT NULL,
    job_position      TINYINT     NOT NULL,
    company_branch_id BIGINT,
    FOREIGN KEY (company_branch_id) REFERENCES company_branch (id)
);

-- Adding data to employee table
INSERT INTO employee (name, surname, job_position, company_branch_id) VALUES
('Emily', 'Clark', 1, 1),
('Michael', 'Brown', 2, 2);

CREATE TABLE reservation
(
    id              BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    customer_id     BIGINT,
    start_date      DATE          NOT NULL,
    end_date        DATE          NOT NULL,
    loan_amount     DECIMAL(7, 2) NOT NULL,
    start_branch_id BIGINT,
    end_branch_id   BIGINT,
    car_id          BIGINT,
    FOREIGN KEY (start_branch_id) REFERENCES company_branch (id),
    FOREIGN KEY (end_branch_id) REFERENCES company_branch (id),
    FOREIGN KEY (car_id) REFERENCES car (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

-- Adding data to reservation table
INSERT INTO reservation (customer_id, start_date, end_date, loan_amount, start_branch_id, end_branch_id, car_id) VALUES
(1, '2024-06-01', '2024-06-10', 500.00, 1, 2, 1),
(2, '2024-07-01', '2024-07-15', 750.00, 2, 1, 2);

CREATE TABLE rent
(
    id             BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    employee_id    BIGINT       NOT NULL,
    comments       VARCHAR(255) NOT NULL,
    rent_date      DATE         NOT NULL,
    reservation_id BIGINT       NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);

-- Adding data to rent table
INSERT INTO rent (employee_id, comments, rent_date, reservation_id) VALUES
(1, 'No issues', '2024-06-01', 1),
(2, 'Minor scratches', '2024-07-01', 2);

CREATE TABLE car_return
    (
        id             BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
        employee_id    BIGINT       NOT NULL,
        reservation_id BIGINT       NOT NULL,
        return_date    DATE         NOT NULL,
        additional_fee INT,
        comments       VARCHAR(255) NOT NULL,
        FOREIGN KEY (employee_id) REFERENCES employee (id),
        FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);

-- Adding data to car_return table
INSERT INTO car_return (employee_id, reservation_id, return_date, additional_fee, comments) VALUES
(1, 1, '2024-06-10', 0, 'Returned in good condition'),
(2, 2, '2024-07-15', 50, 'Late return, additional fee applied');




