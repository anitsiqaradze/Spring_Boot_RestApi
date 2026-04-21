INSERT INTO regions (region_name) VALUES
('Europe'),
('Americas'),
('Asia Pacific'),
('Middle East and Africa');
 
-- Countries (region_id references regions above)
INSERT INTO countries (country_name, region_id) VALUES
('United Kingdom', 1),
('Germany', 1),
('United States', 2),
('Canada', 2),
('Australia', 3),
('Japan', 3),
('South Africa', 4),
('UAE', 4);
 
-- Locations (country_id references countries above)
INSERT INTO locations (city, street_address, country_id) VALUES
('London', '10 Downing Street', 1),
('Berlin', '25 Unter den Linden', 2),
('New York', '350 Fifth Avenue', 3),
('Toronto', '100 King Street West', 4),
('Sydney', '1 Macquarie Place', 5),
('Tokyo', '2-1 Marunouchi', 6),
('Cape Town', '8 Adderley Street', 7),
('Dubai', '1 Sheikh Zayed Road', 8);
 
-- Departments (manager_id is NULL initially, updated after employees are inserted)
INSERT INTO departments (department_id, department_name, location_id, manager_id) VALUES
(1, 'Human Resources', 1, NULL),
(2, 'Engineering', 3, NULL),
(3, 'Finance', 2, NULL),
(4, 'Marketing', 4, NULL),
(5, 'Sales', 5, NULL),
(6, 'Operations', 6, NULL),
(7, 'Legal', 7, NULL),
(8, 'Executive', 8, NULL);
 
-- Employees (top-level managers first, manager_id = NULL for them)
INSERT INTO employees (first_name, last_name, email, phone_number, salary, hire_date, department_id, manager_id) VALUES
('Alice',   'Johnson',  'alice.johnson@example.com',  '555-0101', 12000.00, '2018-03-01', 8, NULL),   -- id=1, CEO
('Bob',     'Smith',    'bob.smith@example.com',      '555-0102', 10000.00, '2019-06-15', 2, 1),      -- id=2, Eng Manager
('Carol',   'White',    'carol.white@example.com',    '555-0103', 9500.00,  '2019-09-20', 1, NULL),      -- id=3, HR Manager
('Daniel',  'Brown',    'daniel.brown@example.com',   '555-0104', 9800.00,  '2020-01-10', 3, NULL),      -- id=4, Finance Manager
('Eva',     'Martinez', 'eva.martinez@example.com',   '555-0105', 9200.00,  '2020-04-05', 4, NULL),      -- id=5, Marketing Manager
('Frank',   'Lee',      'frank.lee@example.com',      '555-0106', 9000.00,  '2020-07-22', 5, NULL),      -- id=6, Sales Manager
('Grace',   'Kim',      'grace.kim@example.com',      '555-0107', 8800.00,  '2021-02-14', 6, NULL),      -- id=7, Ops Manager
('Henry',   'Taylor',   'henry.taylor@example.com',   '555-0108', 8500.00,  '2021-05-30', 7, NULL),      -- id=8, Legal Manager
('Isla',    'Anderson', 'isla.anderson@example.com',  '555-0109', 7000.00,  '2022-01-17', 2, 2),      -- engineer
('James',   'Wilson',   'james.wilson@example.com',   '555-0110', 6800.00,  '2022-03-09', 2, 2),      -- engineer
('Karen',   'Thomas',   'karen.thomas@example.com',   '555-0111', 6500.00,  '2022-06-21', 1, 3),      -- HR staff
('Liam',    'Jackson',  'liam.jackson@example.com',   '555-0112', 6200.00,  '2022-08-11', 3, 4),      -- finance staff
('Mia',     'Harris',   'mia.harris@example.com',     '555-0113', 6000.00,  '2023-01-05', 4, 5),      -- marketing staff
('Noah',    'Clark',    'noah.clark@example.com',     '555-0114', 5800.00,  '2023-03-15', 5, 6),      -- sales staff
('Olivia',  'Lewis',    'olivia.lewis@example.com',   '555-0115', 5600.00,  '2023-07-01', 6, 7);      -- ops staff
 
-- Update departments with their managers
UPDATE departments SET manager_id = 3 WHERE department_id = 1; -- Carol manages HR
UPDATE departments SET manager_id = 2 WHERE department_id = 2; -- Bob manages Engineering
UPDATE departments SET manager_id = 4 WHERE department_id = 3; -- Daniel manages Finance
UPDATE departments SET manager_id = 5 WHERE department_id = 4; -- Eva manages Marketing
UPDATE departments SET manager_id = 6 WHERE department_id = 5; -- Frank manages Sales
UPDATE departments SET manager_id = 7 WHERE department_id = 6; -- Grace manages Operations
UPDATE departments SET manager_id = 8 WHERE department_id = 7; -- Henry manages Legal
UPDATE departments SET manager_id = 1 WHERE department_id = 8; -- Alice manages Executive