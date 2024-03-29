package repository.book;

import model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> update(Book book);
    Optional<Book> updateForRestock(Book book);

    boolean save(Book book);

    void removeAll();
    void removeById(Long id);
}
