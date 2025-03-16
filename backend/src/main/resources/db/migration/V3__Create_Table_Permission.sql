CREATE TABLE IF NOT EXISTS permission (
  id bigserial NOT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);