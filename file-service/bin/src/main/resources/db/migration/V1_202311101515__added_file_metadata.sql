CREATE TABLE IF NOT EXISTS file_metadata
(
    id            UUID NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    version       BIGINT,
    upload_id     VARCHAR(500),
    user_id       UUID,
    filename      VARCHAR(255),
    filetype      VARCHAR(255),
    relative_path VARCHAR(500),
    bucket        VARCHAR(255),
    upload_status VARCHAR(255),
    CONSTRAINT pk_file_metadata PRIMARY KEY (id)
);