package repository.book;

import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMock implements BookRepository{

    private List<Book> books;

    public BookRepositoryMock(){
        books = new ArrayList<>();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean save(Book book) {
        return books.add(book);
    }

    @Override
    public void removeAll() {
        books.clear();
    }

    @Override
    public Optional<Book> update(Book book) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> updateForRestock(Book book) {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) {
        books.removeIf(it -> it.getId().equals(id));
    }
}
