DROP DATABASE SchoolDB;

CREATE DATABASE SchoolDB;

USE SchoolDB;

-- Create Courses table first
CREATE TABLE Courses
(
    CourseID      INT         NOT NULL AUTO_INCREMENT,
    CourseName    VARCHAR(50) NOT NULL UNIQUE,
    CourseStart VARCHAR(50),
    CourseTeacher VARCHAR(50),
    PRIMARY KEY (CourseID)
);

-- Create Teachers table, referencing Courses
CREATE TABLE Teachers
(
    TeacherID   INT         NOT NULL AUTO_INCREMENT,
    TeacherName VARCHAR(50) NOT NULL,
    Age         INT,
    Contact     VARCHAR(50) UNIQUE,
    HireDate VARCHAR(50),
    CourseID    INT,
    PRIMARY KEY (TeacherID),
    FOREIGN KEY (CourseID) REFERENCES Courses (CourseID)
);

-- Create Grades table, referencing Teachers and Courses
CREATE TABLE Grades
(
    GradeID   INT NOT NULL AUTO_INCREMENT,
    Grade     INT CHECK (Grade BETWEEN 1 AND 5),
    TeacherID INT,
    CourseID  INT,
    PRIMARY KEY (GradeID),
    FOREIGN KEY (TeacherID) REFERENCES Teachers (TeacherID),
    FOREIGN KEY (CourseID) REFERENCES Courses (CourseID)
);

-- Create Students table, referencing Grades and Courses
CREATE TABLE Students
(
    StudentID      INT         NOT NULL AUTO_INCREMENT,
    Name           VARCHAR(50) NOT NULL,
    Age            INT,
    Contact        VARCHAR(100) UNIQUE,
    EnrollmentDate varchar(50),
    GradeID        INT,
    CourseID       INT,
    PRIMARY KEY (StudentID),
    FOREIGN KEY (GradeID) REFERENCES Grades (GradeID),
    FOREIGN KEY (CourseID) REFERENCES Courses (CourseID)
);

-- Create CourseFeedback table, referencing Students and Courses
CREATE TABLE CourseFeedback
(
    FeedbackID    INT NOT NULL AUTO_INCREMENT,
    FeedbackGrade INT CHECK (FeedbackGrade BETWEEN 1 AND 5),
    CourseID      INT,
    StudentID     INT,
    PRIMARY KEY (FeedbackID),
    FOREIGN KEY (CourseID) REFERENCES Courses (CourseID),
    FOREIGN KEY (StudentID) REFERENCES Students (StudentID)
);

ALTER TABLE Grades
    ADD StudentID INT;

ALTER TABLE Grades
    ADD CONSTRAINT fk_student
        FOREIGN KEY (StudentID)
            REFERENCES Students (StudentID);
