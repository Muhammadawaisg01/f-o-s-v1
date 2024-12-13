CREATE TABLE IF NOT EXISTS posts_content_categories
(
    post_id               UUID         NOT NULL,
    content_category_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_posts_content_categories PRIMARY KEY (post_id, content_category_name)
);

ALTER TABLE posts_content_categories
    DROP CONSTRAINT IF EXISTS fk_content_category_post_id;
ALTER TABLE posts_content_categories
    ADD CONSTRAINT fk_content_category_post_id FOREIGN KEY (post_id) REFERENCES posts (id);

ALTER TABLE posts_content_categories
    DROP CONSTRAINT IF EXISTS unq_post_content_category;
ALTER TABLE posts_content_categories
    ADD CONSTRAINT unq_post_content_category UNIQUE (post_id, content_category_name);