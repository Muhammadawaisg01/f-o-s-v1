CREATE TABLE IF NOT EXISTS purchased_post
(
    id         UUID PRIMARY KEY NOT NULL,
    user_id    UUID             NOT NULL,
    post_id    UUID             NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version    BIGINT,
    CONSTRAINT unq_purchased_post_user_id_post_id UNIQUE (user_id, post_id)
);

ALTER TABLE purchased_post
    DROP CONSTRAINT IF EXISTS fk_purchased_post_post_id;
ALTER TABLE purchased_post
    ADD CONSTRAINT fk_purchased_post_post_id FOREIGN KEY (post_id) REFERENCES posts (id);