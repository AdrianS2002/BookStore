package repository.book.DAO;

import model.EBook;
import model.builder.EBookBuilder;

import java.sql.Connection;

public class EBookRepositoryMySQL extends AbstractBookRepository<EBook> {
    public EBookRepositoryMySQL(Connection connection) {
        super(connection);
    }
}
