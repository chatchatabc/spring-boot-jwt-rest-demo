CREATE TABLE operator
(
    id              bigint    NOT NULL AUTO_INCREMENT,
    name            varchar(63)   DEFAULT NULL,
    password        varchar(63)   DEFAULT NULL,
    role            varchar(63)    DEFAULT NULL,
    status          int            DEFAULT NULL,
    created_at      timestamp NULL DEFAULT NULL,
    updated_at timestamp NULL DEFAULT NULL,
    PRIMARY KEY (id)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_520_ci;

CREATE TABLE book
(
    id              bigint    NOT NULL AUTO_INCREMENT,
    title           varchar(127)   DEFAULT NULL,
    isbn            varchar(32)    DEFAULT NULL,
    authors         varchar(255)   DEFAULT NULL,
    status          int            DEFAULT NULL,
    created_at      timestamp NULL DEFAULT NULL,
    updated_at timestamp NULL DEFAULT NULL,
    PRIMARY KEY (id)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_520_ci;
