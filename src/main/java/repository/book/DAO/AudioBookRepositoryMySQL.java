package repository.book.DAO;

import model.AudioBook;

import java.sql.Connection;

public class AudioBookRepositoryMySQL extends AbstractBookRepository<AudioBook> {

    public AudioBookRepositoryMySQL(Connection connection) {
        super(connection);
    }
}
