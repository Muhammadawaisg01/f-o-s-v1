CREATE TABLE IF NOT EXISTS connections
(
    id           UUID    NOT NULL,
    user_id      UUID    NOT NULL,
    connected_id UUID    NOT NULL,
    pending      BOOLEAN NOT NULL DEFAULT false,
    version      BIGINT,
    CONSTRAINT pk_connections PRIMARY KEY (id)
);

ALTER TABLE connections
    DROP CONSTRAINT IF EXISTS fk_connections_user_id;
ALTER TABLE connections
    ADD CONSTRAINT fk_connections_user_id FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE connections
    DROP CONSTRAINT IF EXISTS fk_connections_connected_id;
ALTER TABLE connections
    ADD CONSTRAINT fk_connections_connected_id FOREIGN KEY (connected_id) REFERENCES users (id);