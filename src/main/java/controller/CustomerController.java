package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import view.CustomerView;


public class CustomerController {

    private final CustomerView customerView;
    private final User currentUser;
    private final BookService bookService;

    private boolean isTableVisible = false;


    public CustomerController(CustomerView customerView, User currentUser, BookService bookService){
          this.customerView = customerView;
          this.currentUser = currentUser;
          this.bookService = bookService;
          this.customerView.viewAllBooksButtonListener(new ViewAllBooksButtonListener());
    }

    private class BuyButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            int bookId = customerView.getBookId();
            int quantity = customerView.getQuantitySelected();

        }


    }

    private class ViewAllBooksButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            customerView.setBooks(bookService.findAll());
            isTableVisible = !isTableVisible;
            customerView.showTable(isTableVisible);
        }

    }
}
