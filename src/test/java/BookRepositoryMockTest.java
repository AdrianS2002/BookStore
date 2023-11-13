//import model.Book;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import repository.book.BookRepositoryMock;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class BookRepositoryMockTest {
//
//    private BookRepositoryMock bookRepository;
//
//    @BeforeEach
//    void setUp() {
//        bookRepository = new BookRepositoryMock();
//    }
//
//    @Test
//    public void testFindAll() {
//        List<Book> books = bookRepository.findAll();
//        assertEquals(0, books.size());
//    }
//
//    @Test
//    public void testFindById() {
//
//        Book book = new Book();
//        book.setId(1L);
//        book.setAuthor("Liviu Rebreanu");
//        book.setTitle("Ion");
//        book.setPublishedDate(LocalDate.now());
//        bookRepository.save(book);
//
//        Optional<Book> foundBook = bookRepository.findById(1L);
//        assertTrue(foundBook.isPresent());
//        assertEquals("Title", foundBook.get().getTitle());
//    }
//
//    @Test
//    public void testSave() {
//        Book book = new Book();
//        book.setId(1L);
//        book.setAuthor("Author");
//        book.setTitle("Title");
//        book.setPublishedDate(LocalDate.now());
//        assertTrue(bookRepository.save(book));
//
//
//        List<Book> books = bookRepository.findAll();
//        assertEquals(1, books.size());
//    }
//
//    @Test
//    public void testRemoveAll() {
//
//        Book book1 = new Book();
//        book1.setId(1L);
//        book1.setAuthor("Author 1");
//        book1.setTitle("Title 1");
//        book1.setPublishedDate(LocalDate.now());
//
//        Book book2 = new Book();
//        book2.setId(2L);
//        book2.setAuthor("Author 2");
//        book2.setTitle("Title 2");
//        book2.setPublishedDate(LocalDate.now());
//
//        bookRepository.save(book1);
//        bookRepository.save(book2);
//
//
//        List<Book> booksBeforeRemoval = bookRepository.findAll();
//        assertFalse(booksBeforeRemoval.isEmpty());
//
//
//        bookRepository.removeAll();
//
//        // Verificați că nu mai există cărți după eliminare
//        List<Book> booksAfterRemoval = bookRepository.findAll();
//        assertTrue(booksAfterRemoval.isEmpty());
//    }
//}
