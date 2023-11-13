package repository.book.DAO;

import model.Book;
import model.builder.BookBuilder;

import java.sql.Connection;

public class BookRepositoryMySQL extends AbstractBookRepository<Book> {
    public BookRepositoryMySQL(Connection connection) {
        super(connection);
    }
}
