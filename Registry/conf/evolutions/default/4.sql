# --- !Ups

ALTER TABLE node ADD UNIQUE (uuid);
ALTER TABLE node ADD UNIQUE (name);

# --- !Downs

ALTER TABLE node DROP INDEX uuid;
ALTER TABLE node DROP INDEX name;
