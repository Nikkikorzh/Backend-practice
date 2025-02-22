
CREATE TABLE IF NOT EXISTS authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS genres (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    publication_year INT NOT NULL
);


CREATE TABLE IF NOT EXISTS books_authors (
    book_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);


CREATE TABLE IF NOT EXISTS books_genres (
    book_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, genre_id),
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_favorite_books (
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

INSERT INTO authors (name) VALUES ('Джоан Роулинг');
INSERT INTO authors (name) VALUES ('Джордж Р.Р. Мартин');

INSERT INTO genres (name) VALUES ('Фэнтези');
INSERT INTO genres (name) VALUES ('Приключения');

INSERT INTO books (title, description, publication_year)
VALUES ('Гарри Поттер и философский камень', 'Первая книга о Гарри Поттере.', 1997);

INSERT INTO books_authors (book_id, author_id)
VALUES (1, 1);


INSERT INTO books_genres (book_id, genre_id)
VALUES (1, 1);

INSERT INTO users (email, password, role) VALUES
('user1@example.com', '$2a$10$6oJi8/R.CbFOt22giAxpve8N2FmQkx7Ud.FfbLDgCdhYIR85ds2Pq', 'USER'),
('user2@example.com', '$2a$10$6oJi8/R.CbFOt22giAxpve8N2FmQkx7Ud.FfbLDgCdhYIR85ds2Pq', 'USER'),
('admin@example.com', '$2b$12$mqGQliaSpZ7vOR60EUjX1u/jd37Sz0JXYQ9ridzWMS1T8tIhwmBTa', 'ADMIN');
