package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Book;
import model.User;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.user.AuthenticationService;
import view.CustomerView;
import view.LoginView;


public class CustomerController {

    private final CustomerView customerView;
    private final User currentUser;
    private final BookService bookService;
    //private final
    AuthenticationService authenticationService;

    private boolean isTableVisible = false;


    public CustomerController(CustomerView customerView, User currentUser, BookService bookService){
          this.customerView = customerView;
          this.currentUser = currentUser;
          this.bookService = bookService;
          this.customerView.viewAllBooksButtonListener(new ViewAllBooksButtonListener());
         // this.customerView.setBackButtonListener(new BackButtonListener());
          this.customerView.setBuyButton(new BuyButtonListener());
    }

    private class BuyButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            Long bookId = (long) customerView.getBookId();
            int quantity = customerView.getQuantitySelected();
            if(bookService.findById(bookId).getQuantity() >= quantity){
                Book byId = bookService.findById(bookId);
                byId.setQuantity(quantity);
                bookService.update(byId);
                //customerView.showMessage("Book bought successfully!");
                System.out.println("Book bought successfully!");
            }
            else
               /// customerView.showMessage("Not enough books in stock!");
                System.out.println("Book  unsuccessfully!");

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

//    private class BackButtonListener implements EventHandler<ActionEvent> {
//
//        @Override
//        public void handle(ActionEvent event) {
//            // Close the current customer stage
//            Stage currentStage = customerView.getCustomerStage();
//            currentStage.close();
//
//            LoginView loginView = new LoginView(new Stage());
//            LoginController loginController= new LoginController(loginView, authenticationService, bookService);
//
//        }
//
//    }


}
