CREATE TABLE book_record
(
    book_id SERIAL NOT NULL,
    title   VARCHAR(255) NOT NULL,
    author  VARCHAR(255) NOT NULL,
    price   NUMERIC(10,2) NOT NULL,
    isbn    VARCHAR(50) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    availability BOOLEAN NOT NULL,
    quantity INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);