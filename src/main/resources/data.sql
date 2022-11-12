DROP TABLE IF EXISTS books;

CREATE TABLE books (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  author VARCHAR(250) NOT NULL
);

INSERT INTO books (name, author) VALUES
  ('The Lord of the Rings', 'J. R. R. Tolkien'),
  ('Journey to the Center of the Earth', 'Jules Verne'),
  ('The Book Thief', 'Markus Zusak');