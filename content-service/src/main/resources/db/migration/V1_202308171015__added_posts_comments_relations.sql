CREATE TABLE IF NOT EXISTS posts
(
    id              UUID         NOT NULL,
    user_id         UUID         NOT NULL,
    username        VARCHAR(30)  NOT NULL,
    media_type      VARCHAR(255) NOT NULL,
    thumbnail_url   VARCHAR(255),
    media_url       VARCHAR(255),
    description     VARCHAR(500),
    relation_type   VARCHAR(255) NOT NULL,
    age_limited     BOOLEAN      NOT NULL DEFAULT FALSE,
    content_warning VARCHAR(255),
    allow_comments  BOOLEAN      NOT NULL DEFAULT TRUE,
    view_count      BIGINT,
    deleted         BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP,
    version         BIGINT,
    CONSTRAINT pk_posts PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS post_reactions
(
    id         UUID        NOT NULL,
    post_id    UUID        NOT NULL,
    user_id    UUID        NOT NULL,
    username   VARCHAR(30) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version    BIGINT DEFAULT 1,
    CONSTRAINT pk_post_reactions PRIMARY KEY (id)
);

ALTER TABLE post_reactions
    DROP CONSTRAINT IF EXISTS FK_POST_REACTIONS_POST_ID;
ALTER TABLE post_reactions
    ADD CONSTRAINT FK_POST_REACTIONS_POST_ID FOREIGN KEY (post_id) REFERENCES posts (id);

ALTER TABLE post_reactions
    DROP CONSTRAINT IF EXISTS unq_post_reactions_post_id_user_id;
ALTER TABLE post_reactions
    ADD CONSTRAINT unq_post_reactions_post_id_user_id UNIQUE (post_id, user_id);

CREATE TABLE IF NOT EXISTS comments
(
    id         UUID         NOT NULL,
    post_id    UUID         NOT NULL,
    user_id    UUID         NOT NULL,
    text       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version    BIGINT DEFAULT 1,
    CONSTRAINT pk_comments PRIMARY KEY (id)
);

ALTER TABLE comments
    DROP CONSTRAINT IF EXISTS FK_COMMENTS_POST_ID;
ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_POST_ID FOREIGN KEY (post_id) REFERENCES posts (id);

CREATE TABLE IF NOT EXISTS comment_reactions
(
    id         UUID        NOT NULL,
    comment_id UUID        NOT NULL,
    user_id    UUID        NOT NULL,
    username   VARCHAR(30) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version    BIGINT DEFAULT 1,
    CONSTRAINT pk_comment_reactions PRIMARY KEY (id)
);

ALTER TABLE comment_reactions
    DROP CONSTRAINT IF EXISTS FK_COMMENT_REACTIONS_COMMENT_ID;
ALTER TABLE comment_reactions
    ADD CONSTRAINT FK_COMMENT_REACTIONS_COMMENT_ID FOREIGN KEY (comment_id) REFERENCES comments (id);

CREATE TABLE IF NOT EXISTS moments
(
    id              UUID NOT NULL,
    user_id         UUID NOT NULL,
    media_type      VARCHAR(255),
    media_url       VARCHAR(255),
    view_count      BIGINT,
    expiration_date TIMESTAMP,
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP,
    version         BIGINT,
    CONSTRAINT pk_moments PRIMARY KEY (id)
);