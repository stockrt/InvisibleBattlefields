# --- !Ups

CREATE TABLE if NOT EXISTS node (
  id bigint NOT NULL auto_increment,
  uuid VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  info VARCHAR(255) NOT NULL,
  CONSTRAINT pk_node PRIMARY KEY (id)
) engine=innodb;

# --- !Downs

DROP TABLE if EXISTS node;
