INSERT INTO courses (name, author) VALUES ('Learn AWS', 'Jack');
INSERT INTO courses (name, author) VALUES ('Learn Java', 'Bruce');
INSERT INTO courses (name, author) VALUES ('Learn Oracle', 'Caterine');

INSERT INTO books (name, author)
SELECT 'Learn NodeJS', 'Jack'
WHERE NOT EXISTS (
    SELECT 1 FROM books WHERE name = 'Learn NodeJS'
);
