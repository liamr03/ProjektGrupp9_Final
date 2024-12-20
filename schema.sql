CREATE DATABASE SkolaDB;

SHOW DATABASES;

USE SkolaDB;

-- Create Kurser table first
CREATE TABLE Kurser
(
    KursID     INT         NOT NULL AUTO_INCREMENT,
    Kursnamn   VARCHAR(50) NOT NULL UNIQUE,
    Kursstart  DATE,
    KursLärare VARCHAR(50),
    PRIMARY KEY (KursID)
);

-- Create Lärare table next, since it references Kurser
CREATE TABLE Lärare
(
    LärarID           INT         NOT NULL AUTO_INCREMENT,
    LärarNamn         VARCHAR(50) NOT NULL,
    Ålder             INT,
    Kontakt           VARCHAR(50) UNIQUE,
    AnställningsDatum DATE,
    KursID            INT,
    PRIMARY KEY (LärarID),
    FOREIGN KEY (KursID) REFERENCES Kurser (KursID)
);

-- Then create Betygs table, since it references Lärare and Kurser
CREATE TABLE Betygs
(
    BetygsID INT NOT NULL AUTO_INCREMENT,
    Betyg    INT CHECK (Betyg BETWEEN 1 AND 5),
    LärarID  INT,
    KursID   INT,
    PRIMARY KEY (BetygsID),
    FOREIGN KEY (LärarID) REFERENCES Lärare (LärarID),
    FOREIGN KEY (KursID) REFERENCES Kurser (KursID)
);

-- Create Studenter table, which references Betygs and Kurser
CREATE TABLE Studenter
(
    StudentID INT         NOT NULL AUTO_INCREMENT,
    Namn      VARCHAR(50) NOT NULL,
    Ålder     INT,
    Kontakt   VARCHAR(100) UNIQUE,
    Skolstart DATE,
    BetygID   INT,
    KursID    INT,
    PRIMARY KEY (StudentID),
    FOREIGN KEY (BetygID) REFERENCES Betygs (BetygsID),
    FOREIGN KEY (KursID) REFERENCES Kurser (KursID)
);

-- Finally, create KursPuls table, which references Studenter and Kurser
CREATE TABLE KursPuls
(
    PulsID    INT NOT NULL AUTO_INCREMENT,
    PulsBetyg INT CHECK (PulsBetyg BETWEEN 1 AND 5),
    KursID    INT,
    StudentID INT,
    PRIMARY KEY (PulsID),
    FOREIGN KEY (KursID) REFERENCES Kurser (KursID),
    FOREIGN KEY (StudentID) REFERENCES Studenter (StudentID)
);
