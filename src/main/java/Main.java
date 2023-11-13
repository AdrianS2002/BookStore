import database.JDBConnectionWrapper;


import model.AudioBook;
import model.Book;
import model.EBook;
import model.builder.BookBuilder;
import model.builder.EBookBuilder;
import repository.book.DAO.AbstractBookRepository;
import repository.book.DAO.AudioBookRepositoryMySQL;
import repository.book.DAO.BookRepositoryMySQL;
import repository.book.DAO.EBookRepositoryMySQL;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args){

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");

        AbstractBookRepository<EBook> bookRepository = new EBookRepositoryMySQL(connectionWrapper.getConnection());
        AbstractBookRepository<AudioBook> bookRepository1 = new AudioBookRepositoryMySQL(connectionWrapper.getConnection());
        AbstractBookRepository<Book> bookRepositor2 = new BookRepositoryMySQL(connectionWrapper.getConnection());
       // folosim Decorator Pattern pentru a adauga functionalitate de cache la repository-ul de carti
        Book book = new BookBuilder()
                .setAuthor("', '', null); SLEEP(20); --")
                .setTitle("Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();

        Book book1 = new BookBuilder()
                .setAuthor("Agatha Christie")
                .setTitle("Crima din Orient Express")
                .setPublishedDate(LocalDate.of(1934, 1, 1))
                .build();

        Book book2 = new BookBuilder()
                .setAuthor("J.k. Rowling")
                .setTitle("Harry Potter")
                .setPublishedDate(LocalDate.of(1997, 6, 26))
                .build();

        EBook eBook = new EBookBuilder()
                .setAuthor("J.k. Rowling")
                .setTitle("Harry Potter")
                .setFormat("pdf")
                .setPublishedDate(LocalDate.of(1997, 6, 26))
                .build();


        bookRepositor2.save(book);
        bookRepositor2.save(book1);
        bookRepositor2.save(book2);
        bookRepository.save(eBook);


        System.out.println(bookRepositor2.findAll());
        System.out.println(bookRepositor2.findAll());
        System.out.println(bookRepositor2.findAll());
        System.out.println(bookRepository.findAll());


    }
}
