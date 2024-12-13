CREATE TABLE IF NOT EXISTS posts_hashtags
(
    post_id UUID        NOT NULL,
    hashtag VARCHAR(30) NOT NULL,
    CONSTRAINT pk_posts_hashtags PRIMARY KEY (post_id, hashtag)
);

ALTER TABLE posts_hashtags
    DROP CONSTRAINT IF EXISTS fk_posts_hashtags_post_id;
ALTER TABLE posts_hashtags
    ADD CONSTRAINT fk_posts_hashtags_post_id FOREIGN KEY (post_id) REFERENCES posts (id);

ALTER TABLE posts_hashtags
    DROP CONSTRAINT IF EXISTS unq_post_hashtag;
ALTER TABLE posts_hashtags
    ADD CONSTRAINT unq_post_hashtag UNIQUE (post_id, hashtag);