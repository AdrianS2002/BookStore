package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import service.book.BookService;
import view.EmployeeView;

public class EmployeeController {
    private final EmployeeView employeeView;

    private final BookService bookService;

    private final User currentUser;
    private boolean isTableVisible = false;

    public EmployeeController(EmployeeView employeeView, User currentUser, BookService bookService) {
        this.employeeView = employeeView;
        this.currentUser = currentUser;
        this.bookService = bookService;
        this.employeeView.viewAllBooksButtonListenerForEmployee(new ViewAllBooksButtonListenerForEmployee());
    }
    private class ViewAllBooksButtonListenerForEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            employeeView.setBooks(bookService.findAll());
            isTableVisible = !isTableVisible;
            employeeView.showTableOfBooksForEmployee(isTableVisible);
        }

    }

}
