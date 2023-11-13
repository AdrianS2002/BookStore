//package repository.book;
//
//import model.Book;
//
//import java.util.List;
//import java.util.Optional;
//
//public class BookRepositoryCacheDecorator extends BookRepositoryDecorator{
//    private Cache<Book> cache;
//    public BookRepositoryCacheDecorator(BookRepository decoratedRepository, Cache<Book> cache){
//        super(decoratedRepository);
//        this.cache = cache;
//    }
//
//
//    @Override
//    public List<Book> findAll() {
//        if(cache.hasResult())
//        {
//            return cache.load();
//        }
//        List<Book> books = decoratedRepository.findAll();
//        cache.save(books);
//        return books;
//    }
//
//    @Override
//    public Optional<Book> findById(Long id) {
//        return decoratedRepository.findById(id);
//    }
//
//    @Override
//    public boolean save(Object book) {
//        cache.invalidateCache();
//        return decoratedRepository.save(book);
//    }
//
//
//
//    @Override
//    public void removeAll() {
//        cache.invalidateCache();
//        decoratedRepository.removeAll();
//    }
//}
