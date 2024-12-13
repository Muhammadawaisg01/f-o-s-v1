CREATE TABLE IF NOT EXISTS roles
(
    id   UUID         NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

ALTER TABLE roles
    DROP CONSTRAINT IF EXISTS unq_roles_name;
ALTER TABLE roles
    ADD CONSTRAINT unq_roles_name UNIQUE (name);

CREATE TABLE IF NOT EXISTS users
(
    id                     UUID         NOT NULL,
    version                BIGINT                DEFAULT 1,
    created_at             TIMESTAMP,
    updated_at             TIMESTAMP,
    email                  VARCHAR(255) NOT NULL,
    username               VARCHAR(255) NOT NULL,
    preferred_language     VARCHAR(255) NOT NULL DEFAULT 'ENGLISH',
    first_name             VARCHAR(255),
    last_name              VARCHAR(255),
    profile_avatar_url     VARCHAR(255),
    profile_cover_url      VARCHAR(255),
    profile_description    VARCHAR(500),
    status_text            VARCHAR(200),
    status_file_id         VARCHAR(255),
    status_expiration_date TIMESTAMP,
    verified               BOOLEAN      NOT NULL DEFAULT FALSE,
    top_creator_mark       VARCHAR(255) NOT NULL DEFAULT 'NONE',
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    DROP CONSTRAINT IF EXISTS unq_users_email;
ALTER TABLE users
    ADD CONSTRAINT unq_users_email UNIQUE (email);
ALTER TABLE users
    DROP CONSTRAINT IF EXISTS unq_users_username;
ALTER TABLE users
    ADD CONSTRAINT unq_users_username UNIQUE (username);

CREATE TABLE IF NOT EXISTS users_roles
(
    role_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
);

ALTER TABLE users_roles
    DROP CONSTRAINT IF EXISTS fk_user_role_id;
ALTER TABLE users_roles
    ADD CONSTRAINT fk_user_role_id FOREIGN KEY (role_id) REFERENCES roles (id);
ALTER TABLE users_roles
    DROP CONSTRAINT IF EXISTS fk_role_user_id;
ALTER TABLE users_roles
    ADD CONSTRAINT fk_role_user_id FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE IF NOT EXISTS settings
(
    id           UUID         NOT NULL,
    version      BIGINT DEFAULT 1,
    user_setting VARCHAR(255) NOT NULL,
    value        VARCHAR(255),
    enabled      BOOLEAN,
    user_id      UUID         NOT NULL,
    CONSTRAINT pk_settings PRIMARY KEY (id)
);

ALTER TABLE settings
    DROP CONSTRAINT IF EXISTS fk_settings_user_id;
ALTER TABLE settings
    ADD CONSTRAINT fk_settings_user_id FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE IF NOT EXISTS user_reports
(
    id          UUID         NOT NULL,
    version     BIGINT DEFAULT 1,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    reporter_id UUID         NOT NULL,
    reported_id UUID         NOT NULL,
    post_id     UUID,
    reason      VARCHAR(255) NOT NULL,
    explanation VARCHAR(200) NOT NULL,
    CONSTRAINT pk_user_reports PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS screenshot_upload_ids
(
    user_report_id       UUID NOT NULL,
    screenshot_upload_id VARCHAR(255)
);

ALTER TABLE screenshot_upload_ids
    DROP CONSTRAINT IF EXISTS fk_screenshot_upload_ids_user_report_id;
ALTER TABLE screenshot_upload_ids
    ADD CONSTRAINT fk_screenshot_upload_ids_user_report_id FOREIGN KEY (user_report_id) REFERENCES user_reports (id)



