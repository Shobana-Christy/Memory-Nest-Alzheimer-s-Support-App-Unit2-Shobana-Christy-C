
CREATE TABLE IF NOT EXISTS user_role (
    user_role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(100),
    CONSTRAINT uk_user_role_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS reminder (
    reminder_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_role_id BIGINT NOT NULL,
    name VARCHAR(255),
    date DATE,
    time TIME,
    notes LONGTEXT,
    CONSTRAINT fk_reminder_user_role FOREIGN KEY (user_role_id)
        REFERENCES user_role(user_role_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS contact_us (
    contact_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255),
    message LONGTEXT
);

CREATE TABLE IF NOT EXISTS engagement_center (
    engagement_center_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    src VARCHAR(500)
);

