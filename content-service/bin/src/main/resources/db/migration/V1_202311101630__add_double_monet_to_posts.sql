DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'posts') THEN

        IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'posts' AND column_name = 'double_monetization') THEN
            ALTER TABLE posts ADD COLUMN double_monetization BOOLEAN DEFAULT false;
        END IF;

        IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'posts' AND column_name = 'price') THEN
            ALTER TABLE posts ADD COLUMN price NUMERIC(19, 2);
        END IF;
    END IF;
END $$;