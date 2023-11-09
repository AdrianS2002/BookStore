import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;
import repository.book.*;
import service.BookService;
import service.BookServiceImpl;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args){

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");



        BookRepository bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(connectionWrapper.getConnection()),
                new Cache<>()
        );
        BookService bookService = new BookServiceImpl(bookRepository);
        //folosim Decorator Pattern pentru a adauga functionalitate de cache la repository-ul de carti
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

        bookService.save(book);
        bookService.save(book1);
        bookService.save(book2);

        System.out.println(bookService.findAll());
        System.out.println(bookService.findAll());
        System.out.println(bookService.findAll());
        System.out.println(bookService.findAll());


    }
}
