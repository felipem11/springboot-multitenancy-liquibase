--liquibase formatted sql

--changeset ernsta:1 failOnError:true

CREATE TABLE Persons (
                         PersonID int,
                         LastName varchar(255),
                         FirstName varchar(255),
                         Address varchar(255),
                         City varchar(255)
);

--rollback DROP TABLE
--rollback foo;