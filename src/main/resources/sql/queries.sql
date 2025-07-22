CREATE TABLE employee (
    id INT PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    role VARCHAR,
    reports_to INT,
    leave_balance INT,
    sick_leaves INT,
    paid_leaves INT,
    contact VARCHAR,
    email_id VARCHAR UNIQUE,
    phone VARCHAR UNIQUE,
    address TEXT,
    city VARCHAR,
    locality VARCHAR,
    state VARCHAR,
    pin_code VARCHAR
);

CREATE TABLE leave_application (
    leave_id SERIAL PRIMARY KEY,
    employee_id INT,
    leave_type VARCHAR CHECK (leave_type IN ('SICK', 'PAID')),
    start_date DATE,
    end_date DATE,
    status VARCHAR CHECK (status IN ('APPLIED', 'APPROVED', 'REJECTED')),
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);