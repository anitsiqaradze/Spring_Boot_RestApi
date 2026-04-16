-- Drop tables if they already exist (to avoid conflicts)
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

-- Create departments table
CREATE TABLE departments (
    department_id BIGINT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL
);

-- Create employees table
CREATE TABLE employees (
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150),
    phone_number VARCHAR(50),
    salary DOUBLE,
    hire_date DATE,
    department_id BIGINT,
    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
);

-- Insert departments first (parent table)
INSERT INTO departments (department_id, department_name)
VALUES
(1, 'Human Resources'),
(2, 'Engineering'),
(3, 'Finance'),
(4, 'Marketing'),
(5, 'Sales');

-- Insert employees (child table)
INSERT INTO employees 
(first_name, last_name, email, phone_number, salary, hire_date, department_id)
VALUES
('John', 'Doe', 'john.doe@example.com', '555-1234', 5000.00, '2023-01-15', 1),
('Jane', 'Smith', 'jane.smith@example.com', '555-5678', 6200.50, '2022-11-03', 2),
('Michael', 'Brown', 'michael.brown@example.com', '555-9012', 4800.75, '2024-02-20', 1),
('Emily', 'Davis', 'emily.davis@example.com', '555-3456', 7100.00, '2021-07-10', 3),
('David', 'Wilson', 'david.wilson@example.com', '555-7890', 5300.25, '2023-05-05', 2);