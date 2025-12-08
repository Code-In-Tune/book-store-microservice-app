CREATE TABLE book_sale
(
    sale_id SERIAL NOT NULL,
    book_id INT NOT NULL,
    quantity INT NOT NULL,
    date_sold TIMESTAMP DEFAULT NOW(),
    amount NUMERIC(10,2) NOT NULL
);