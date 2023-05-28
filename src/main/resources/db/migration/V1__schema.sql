DROP TABLE IF EXISTS charities;

CREATE TABLE charities
(
  id          BIGINT       NOT NULL AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL UNIQUE,
  ein         VARCHAR(255) NOT NULL UNIQUE,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS donors;

CREATE TABLE donors
(
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL UNIQUE,
  last_name  VARCHAR(255) NOT NULL UNIQUE,
  address    VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS donations;

CREATE TABLE donations
(
  id         BIGINT NOT NULL AUTO_INCREMENT,
  donor_id   BIGINT NOT NULL,
  charity_id BIGINT NOT NULL,
  amount     DOUBLE NOT NULL,
  date_created DATE NULL,
  last_updated DATE NULL,
  PRIMARY KEY (id)
);
