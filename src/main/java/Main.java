import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryMock;
import repository.book.BookRepositoryMySQL;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");



        BookRepository bookRepository = new BookRepositoryMySQL(connectionWrapper.getConnection());

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

        bookRepository.save(book);
        bookRepository.save(book1);
        bookRepository.save(book2);

        System.out.println(bookRepository.findAll());


    }
}
