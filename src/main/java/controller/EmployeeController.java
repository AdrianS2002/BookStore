package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import model.User;
import model.builder.BookBuilder;
import service.book.BookService;
import view.EmployeeView;

import java.time.LocalDate;

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
        this.employeeView.AddBookButtonListener(new AddBookButtonListener());
        this.employeeView.deleteBookButtonListener(new DeleteBookButtonListener());
        this.employeeView.updateBookButtonListener(new UpdateBookButtonListener());
        this.employeeView.findBookButtonListener(new FindBookButtonListener());
        this.employeeView.generateReportButtonListener(new GenerateReportButtonListener());
    }
    private class ViewAllBooksButtonListenerForEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            employeeView.setBooks(bookService.findAll());
            isTableVisible = !isTableVisible;
            employeeView.showTableOfBooksForEmployee(isTableVisible);
        }

    }

    private class AddBookButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String title = employeeView.getTitleBook();
            String author = employeeView.getAuthorBook();
            LocalDate publishDate = employeeView.getPublishDateBook();
            Integer quantity = employeeView.getQuantityBook();
            Book book = new BookBuilder()
                    .setTitle(title)
                    .setAuthor(author)
                    .setPublishedDate(publishDate)
                    .setQuantity(quantity)
                    .build();
            bookService.save(book);
            employeeView.showMessageAddBook("Book added successfully!");
        }

    }

    public class DeleteBookButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Long id = employeeView.getBookId();
            bookService.removeById(id);
            employeeView.showMessageDeleteBook("Book deleted successfully!");
        }
    }

    public class UpdateBookButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Long id = employeeView.getBookId();
            Integer quantity = employeeView.getQuantityBook();
            if(quantity >= 0){
                Book byId = bookService.findById(id);
                byId.setQuantity(quantity);
                bookService.updateForRestock(byId);
                employeeView.showMessageUpdateBook("Book updated successfully!");
            }
            else
                employeeView.showMessageUpdateBook("Quantity must be greater than 0!");

        }
    }

    public class FindBookButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Long id = employeeView.getIdBook();
            Book book = bookService.findById(id);
            employeeView.setFoundBook(book);
        }
    }

    public class GenerateReportButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            employeeView.generateReport();
        }
    }

}
