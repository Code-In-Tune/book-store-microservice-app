ALTER TABLE book_sale
ADD CONSTRAINT book_sale_pk PRIMARY KEY (sale_id);

ALTER TABLE book_sale
ADD CONSTRAINT book_sale_fk FOREIGN KEY (book_id) REFERENCES book_record(book_id);