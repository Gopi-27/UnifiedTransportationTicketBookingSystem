---- TRAIN  
-- Stores train master data. 
CREATE TABLE train ( 
    train_id NUMBER PRIMARY KEY, 
    train_number VARCHAR2(10) UNIQUE NOT NULL, 
    train_name VARCHAR2(100), 
    source_station VARCHAR2(50) NOT NULL, 
    destination_station VARCHAR2(50) NOT NULL 
); 
---- TRAIN_SCHEDULE 
-- Connects TRAIN   <->  journey date & availability. 
CREATE TABLE train_schedule ( 
    schedule_id NUMBER PRIMARY KEY, 
    train_id NUMBER NOT NULL, 
    journey_date DATE NOT NULL, 
    departure_time VARCHAR2(10), 
    arrival_time VARCHAR2(10), 
    available_seats NUMBER, 
    CONSTRAINT fk_schedule_train 
        FOREIGN KEY (train_id) REFERENCES train(train_id) 
); 
---- COACH  
-- Connects TRAIN_SCHEDULE  <-> seat classes. 
CREATE TABLE coach ( 
    coach_id NUMBER PRIMARY KEY, 
    schedule_id NUMBER NOT NULL, 
    coach_type VARCHAR2(20), -- e.g., Sleeper, AC, etc. 
    total_seats NUMBER, 
    CONSTRAINT fk_coach_schedule 
        FOREIGN KEY (schedule_id) REFERENCES train_schedule(schedule_id) 
); 
---- SEAT 
-- Connects COACH  <-> individual seat availability. 
CREATE TABLE seat ( 
    seat_id NUMBER PRIMARY KEY, 
    coach_id NUMBER NOT NULL, 
    seat_number VARCHAR2(10) NOT NULL,  -- e.g., A1, B3 
    seat_status VARCHAR2(20) DEFAULT 'AVAILABLE', -- AVAILABLE / BOOKED 
    CONSTRAINT fk_seat_coach 
        FOREIGN KEY (coach_id) REFERENCES coach(coach_id) 
); 
---- BOOKING 
-- Connects USERS  <-> TRAIN_SCHEDULE. 
CREATE TABLE booking ( 
    booking_id NUMBER PRIMARY KEY, 
    user_id NUMBER NOT NULL, 
    schedule_id NUMBER NOT NULL, 
    booking_date DATE DEFAULT SYSDATE, 
    booking_status VARCHAR2(20), -- e.g., CONFIRMED, CANCELLED 
    CONSTRAINT fk_booking_user 
        FOREIGN KEY (user_id) REFERENCES users(user_id), 
    CONSTRAINT fk_booking_schedule 
        FOREIGN KEY (schedule_id) REFERENCES train_schedule(schedule_id) 
); 
---- PASSENGER 
-- Connects BOOKING  <-> passenger details + seat selection. 
CREATE TABLE passenger ( 
    passenger_id NUMBER PRIMARY KEY, 
    booking_id NUMBER NOT NULL, 
    seat_id NUMBER NOT NULL, 
    passenger_name VARCHAR2(100), 
    age NUMBER, 
    gender VARCHAR2(10), 
    CONSTRAINT fk_passenger_booking 
        FOREIGN KEY (booking_id) REFERENCES booking(booking_id), 
    CONSTRAINT fk_passenger_seat 
        FOREIGN KEY (seat_id) REFERENCES seat(seat_id) 
);
