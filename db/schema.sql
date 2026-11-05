CREATE DATABASE IF NOT EXISTS personal_finance CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE personal_finance;

CREATE TABLE IF NOT EXISTS users (
 id BIGINT NOT NULL AUTO_INCREMENT,
 first_name VARCHAR(80) NOT NULL,
 last_name VARCHAR(80) NOT NULL,
 email VARCHAR(254) NOT NULL,
 phone_number VARCHAR(20),
 monthly_budget DECIMAL(15,2) NOT NULL,
 created_at DATETIME(6) NOT NULL,
 updated_at DATETIME(6) NOT NULL,
 PRIMARY KEY (id),
 CONSTRAINT uk_users_email UNIQUE (email),
 CONSTRAINT chk_users_monthly_budget CHECK (monthly_budget > 0)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS expenses (
 id BIGINT NOT NULL AUTO_INCREMENT,
 user_id BIGINT NOT NULL,
 title VARCHAR(120) NOT NULL,
 description VARCHAR(1000),
 category VARCHAR(30) NOT NULL,
 amount DECIMAL(15,2) NOT NULL,
 expense_date DATE NOT NULL,
 created_at DATETIME(6) NOT NULL,
 updated_at DATETIME(6) NOT NULL,
 PRIMARY KEY (id),
 KEY idx_expenses_user_date (user_id, expense_date),
 CONSTRAINT fk_expenses_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
 CONSTRAINT chk_expenses_amount CHECK (amount > 0)
) ENGINE=InnoDB;
