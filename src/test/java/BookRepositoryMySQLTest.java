import database.DatabaseConnectionFactory;
import model.Book;
import model.builder.BookBuilder;
import org.junit.jupiter.api.Test;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryMySQLTest {
    private Connection connection= DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();
    private BookRepositoryMySQL bookRepository = new BookRepositoryMySQL(connection);
    @Test
    public void testFindAll(){
        assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    public void testFindById(){
        Book book = new BookBuilder()
                .setAuthor("', '', null); SLEEP(20); --")
                .setTitle("Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .setId(1L)
                .build();

        Optional<Book> optionalBook = bookRepository.findById(1L);
        Book book1 = null;
        if(optionalBook.isPresent()){
            book1 = optionalBook.get();
        }
        assertEquals(book, book1);
}


    @Test
        public void testSave(){
        assertEquals(true, bookRepository.save(new BookBuilder()
                .setAuthor("J.k. Rowling")
                .setTitle("Harry Potter")
                .setPublishedDate(LocalDate.of(1997, 6, 26))
                .build()));
    }

    @Test
    public void testRemoveAll(){
        bookRepository.removeAll();
        assertEquals(0, bookRepository.findAll().size());
    }

}
