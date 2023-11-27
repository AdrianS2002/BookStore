package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import service.book.BookService;
import service.user.AuthenticationService;
import view.AdminView;
import view.EmployeeView;

public class AdminController {
    private final AdminView adminView;
    private final AuthenticationService authenticationService;
    private final User currentUser;
    private boolean isTableVisible = false;

    public AdminController(AdminView adminView, User currentUser, AuthenticationService authenticationService) {
        this.adminView = adminView;
        this.currentUser = currentUser;
        this.authenticationService = authenticationService;
        this.adminView.viewAllUsersButtonListenerForAdmin(new ViewAllUsersButtonListenerForAdmin());
    }

    private class ViewAllUsersButtonListenerForAdmin implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
           // adminView.setUsers(authenticationService.find)
            isTableVisible = !isTableVisible;
            adminView.showTableOfBooksForUser(isTableVisible);

        }
    }

}
