DROP TABLE IF EXISTS donations;
DROP TABLE IF EXISTS donors;
DROP TABLE IF EXISTS charities;


CREATE TABLE charities
(
  id          BIGINT       NOT NULL AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL UNIQUE,
  ein         VARCHAR(255) NOT NULL UNIQUE,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE donors
(
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL UNIQUE,
  last_name  VARCHAR(255) NOT NULL UNIQUE,
  address    VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE donations
(
  id         BIGINT NOT NULL AUTO_INCREMENT,
  donor_id   BIGINT NOT NULL,
  charity_id BIGINT NOT NULL,
  amount     DOUBLE NOT NULL,
  date_created DATETIME NOT NULL,
  last_updated DATETIME NOT NULL,
  PRIMARY KEY (id)
);
