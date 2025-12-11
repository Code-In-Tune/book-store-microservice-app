CREATE TABLE book_sale
(
    sale_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    date_sold TIMESTAMP DEFAULT NOW(),
    amount NUMERIC(10,2) NOT NULL
);