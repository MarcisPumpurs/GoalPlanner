/* Create and select DB */
DROP DATABASE IF EXISTS goal_planner;
CREATE DATABASE goal_planner;
USE goal_planner;

/* Create Tables */
CREATE TABLE users(
	user_id BIGINT AUTO_INCREMENT UNIQUE,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE sessions(
	session_id BIGINT AUTO_INCREMENT UNIQUE,
    user_id BIGINT NOT NULL
);

CREATE TABLE userplans(
	plan_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL
);

CREATE TABLE status(
	status_id BIGINT AUTO_INCREMENT UNIQUE,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE plans(
	plan_id BIGINT AUTO_INCREMENT UNIQUE,
    status_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE tasks(
	task_id BIGINT AUTO_INCREMENT UNIQUE,
    plan_id BIGINT NOT NULL,
    status_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE subtasks(
	subtask_id BIGINT AUTO_INCREMENT UNIQUE,
    task_id BIGINT NOT NULL,
    status_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL
);

/* Create PK Constraints */
ALTER TABLE users
ADD CONSTRAINT pk_users
PRIMARY KEY (user_id);

ALTER TABLE sessions
ADD CONSTRAINT pk_sessions
PRIMARY KEY (session_id);

ALTER TABLE status
ADD CONSTRAINT pk_status
PRIMARY KEY (status_id);

ALTER TABLE userplans
ADD CONSTRAINT pk_users
PRIMARY KEY (user_id, plan_id);

ALTER TABLE plans
ADD CONSTRAINT pk_plans
PRIMARY KEY (plan_id);

ALTER TABLE tasks
ADD CONSTRAINT pk_tasks
PRIMARY KEY (task_id);

ALTER TABLE subtasks
ADD CONSTRAINT pk_subtasks
PRIMARY KEY (subtask_id);

/* Create FK Constraints */
ALTER TABLE plans
ADD CONSTRAINT fk_plans_status
FOREIGN KEY (status_id)
REFERENCES status(status_id);

ALTER TABLE tasks
ADD CONSTRAINT fk_tasks_status
FOREIGN KEY (status_id)
REFERENCES status(status_id);

ALTER TABLE subtasks
ADD CONSTRAINT fk_subtasks_status
FOREIGN KEY (status_id)
REFERENCES status(status_id);

ALTER TABLE tasks
ADD CONSTRAINT fk_tasks_plans
FOREIGN KEY (plan_id)
REFERENCES plans(plan_id);

ALTER TABLE subtasks
ADD CONSTRAINT fk_subtasks_tasks
FOREIGN KEY (task_id)
REFERENCES tasks(task_id);

ALTER TABLE userplans
ADD CONSTRAINT fk_userplans_users
FOREIGN KEY (user_id)
REFERENCES users(user_id);

ALTER TABLE userplans
ADD CONSTRAINT fk_userplans_plans
FOREIGN KEY (plan_id)
REFERENCES plans(plan_id);
