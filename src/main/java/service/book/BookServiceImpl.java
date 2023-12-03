package service.book;

import model.Book;
import repository.book.BookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    @Override
    public Optional<Book> update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public Optional<Book> updateForRestock(Book book) {
        return bookRepository.updateForRestock(book);
    }
    @Override
    public boolean save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void removeById(Long id) {
        bookRepository.removeById(id);
    }


    @Override
    public int getAgeOfBook(Long id) {
        Book book = this.findById(id);

        LocalDate now = LocalDate.now();

        return (int)ChronoUnit.YEARS.between(book.getPublishedDate(), now);
    }
}
