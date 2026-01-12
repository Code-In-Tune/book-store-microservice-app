package com.codeintune.bookstore.testutils.seeder.sale.data;

import com.codeintune.bookstore.model.sale.BookSale;

import java.math.BigDecimal;
import java.time.Instant;

public class BookSaleTestSeeder {

    public static BookSale generateEntity(){
        BookSale bookSale = new BookSale();
        bookSale.setBookId(1L);
        bookSale.setSaleId(1L);
        bookSale.setAmount(BigDecimal.ONE);
        bookSale.setQuantity(1);
        bookSale.setDateSold(Instant.ofEpochMilli(0));
        return bookSale;
    }
}
