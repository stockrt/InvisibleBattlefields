# --- !Ups

ALTER TABLE node CHANGE name label VARCHAR(255) NOT NULL;

# --- !Downs

ALTER TABLE node CHANGE label name VARCHAR(255) NOT NULL;
