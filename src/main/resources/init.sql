DROP TABLE IF EXISTS CLIENT;
CREATE SCHEMA IF NOT EXISTS PUBLIC;
CREATE TABLE IF NOT EXISTS PUBLIC.CLIENT (
    PHONE_NUMBER CHARACTER VARYING NOT NULL PRIMARY KEY,
	LAST_NAME CHARACTER VARYING NOT NULL,
	FIRST_NAME CHARACTER VARYING NOT NULL,
    MIDDLE_NAME CHARACTER VARYING NOT NULL,
	INN CHARACTER VARYING NOT NULL,
	ADDRESS CHARACTER VARYING NOT NULL
);