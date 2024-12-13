CREATE TABLE IF NOT EXISTS contents
(
    id                 UUID         NOT NULL,
    post_id            UUID         NOT NULL,
    thumbnail_key      VARCHAR(255) NOT NULL,
    thumbnail_blur_key VARCHAR(255) NOT NULL,
    media_key          VARCHAR(255) NOT NULL,
    version            BIGINT,
    CONSTRAINT pk_contents PRIMARY KEY (id)
);

ALTER TABLE contents
    DROP CONSTRAINT IF EXISTS FK_CONTENTS_POST_ID;
ALTER TABLE contents
    ADD CONSTRAINT FK_CONTENTS_POST_ID FOREIGN KEY (post_id) REFERENCES posts (id);