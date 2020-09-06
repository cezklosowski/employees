CREATE TABLE employee(
id BIGINT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(15),
last_name VARCHAR(15),
position VARCHAR(15),
salary DOUBLE,
birth_year INT,
PRIMARY KEY(id)
);

CREATE TABLE task(
task_id BIGINT NOT NULL AUTO_INCREMENT,
task_name VARCHAR(50),
task_date DATE,
if_done boolean,
employee_id BIGINT,
PRIMARY KEY(task_id)
);