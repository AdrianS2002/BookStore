package service.book;

import model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Book findById(Long id);

    Optional<Book> update(Book book);
    Optional<Book> updateForRestock(Book book);


    boolean save(Book book);

    void removeById(Long id);

    int getAgeOfBook(Long id);
}

