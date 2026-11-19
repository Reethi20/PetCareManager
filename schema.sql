-- PetCareManager database schema
CREATE DATABASE IF NOT EXISTS petcaremanager;
USE petcaremanager;

CREATE TABLE IF NOT EXISTS owners (
    owner_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS dogs (
    dog_id INT AUTO_INCREMENT PRIMARY KEY,
    owner_id INT,
    name VARCHAR(50) NOT NULL,
    breed VARCHAR(50),
    age INT,
    gender VARCHAR(10),
    weight DECIMAL(5,2),
    color VARCHAR(30),
    FOREIGN KEY (owner_id) REFERENCES owners(owner_id)
);

CREATE TABLE IF NOT EXISTS medical_records (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    dog_id INT,
    vaccination_status VARCHAR(100),
    allergies VARCHAR(255),
    chronic_diseases VARCHAR(255),
    vet_name VARCHAR(100),
    notes TEXT,
    FOREIGN KEY (dog_id) REFERENCES dogs(dog_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    dog_id INT,
    type VARCHAR(50),          -- Vet, Grooming, Training, etc.
    date DATE,
    time TIME,
    status VARCHAR(20),        -- Upcoming, Completed, Cancelled
    notes TEXT,
    FOREIGN KEY (dog_id) REFERENCES dogs(dog_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS expenses (
    expense_id INT AUTO_INCREMENT PRIMARY KEY,
    dog_id INT,
    date DATE,
    category VARCHAR(50),      -- Food, Vet, Grooming, Others
    amount DECIMAL(10,2),
    notes TEXT,
    FOREIGN KEY (dog_id) REFERENCES dogs(dog_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS care_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    dog_id INT NOT NULL,
    date DATE,
    time TIME,
    activity_type VARCHAR(50), -- Walk, Feed, Bath, Medicine, etc.
    description TEXT,
    FOREIGN KEY (dog_id) REFERENCES dogs(dog_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reminders (
    reminder_id INT AUTO_INCREMENT PRIMARY KEY,
    owner_id INT NOT NULL,
    dog_id INT,
    reminder_type VARCHAR(50), -- Vaccination, Vet Visit, Grooming, etc.
    title VARCHAR(100),
    message TEXT,
    reminder_date DATE,
    reminder_time TIME,
    status VARCHAR(20) DEFAULT 'PENDING',  -- PENDING, DONE, MISSED
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES owners(owner_id),
    FOREIGN KEY (dog_id) REFERENCES dogs(dog_id) ON DELETE CASCADE
);

-- Demo owner (the app currently uses owner_id = 1)
INSERT INTO owners (username, password, name, phone, email)
SELECT 'demo_user', 'demo_password', 'Demo Owner', '0000000000', 'demo@example.com'
WHERE NOT EXISTS (SELECT 1 FROM owners WHERE owner_id = 1);
