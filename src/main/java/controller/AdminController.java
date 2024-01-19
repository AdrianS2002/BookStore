package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Role;
import model.User;
import service.book.BookService;
import service.employeeBook.EmployeeBookService;
import service.user.AuthenticationService;
import service.user.UserService;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

import java.util.List;
import java.util.stream.Stream;

public class AdminController {
    private final AdminView adminView;
    private final AuthenticationService authenticationService;
    private final EmployeeBookService employeeBookService;
    private final UserService userService;
    private final BookService bookService;
    private final User currentUser;
    private boolean isTableVisible = false;

    public AdminController(AdminView adminView, User currentUser, AuthenticationService authenticationService, UserService userService,EmployeeBookService employeeBookService,BookService bookService) {
        this.adminView = adminView;
        this.currentUser = currentUser;
        this.userService = userService;
        this.bookService = bookService;
        this.authenticationService = authenticationService;
        this.employeeBookService = employeeBookService;
        this.adminView.viewAllUsersButtonListenerForAdmin(new ViewAllUsersButtonListenerForAdmin());
        this.adminView.addUserButtonListener(new AddUserButtonListener());
        this.adminView.deleteUserButton(new DeleteUserButtonListener());
        this.adminView.updateUserButton(new UpdateUserButtonListener());
        this.adminView.findUserButtonListener(new FindUserButtonListener());
        this.adminView.setBackButtonListener(new BackButtonListener());
        this.adminView.generateReportButtonListener(new GenerateReportButtonListener());
    }

    private class ViewAllUsersButtonListenerForAdmin implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            adminView.setUsers(userService.findAll());
            isTableVisible = !isTableVisible;
            adminView.showTableOfUser(isTableVisible);

        }
    }

    private class BackButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            Stage currentStage = adminView.getAdminStage();
            currentStage.close();
            LoginView loginView = new LoginView(new Stage());
            LoginController loginController = new LoginController(loginView, authenticationService, bookService,userService,employeeBookService);

        }
    }

    private class AddUserButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            String username = adminView.getUsername();
            String roles = adminView.getRoles();
            String password = adminView.getPassword();
            authenticationService.register(username,password);
            adminView.showMessageAddUser("User added successfully!");
        }
    }

    public class DeleteUserButtonListener implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Long id = adminView.getId();
            userService.removeById(id);
            adminView.showMessageDeleteUser("User deleted successfully!");
        }
    }

    public class UpdateUserButtonListener implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Long id = adminView.getId();
            String roles = adminView.getRoles();
            Long roleList = Stream.of(roles.split(" ")).map(Long::parseLong).toList().get(0);
            userService.update(id, roleList);
            adminView.showMessageUpdateUser("User updated successfully!");
        }
    }

    public class FindUserButtonListener implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            Long id = adminView.getIdUser();
            User user = userService.findById(id);
            adminView.setFoundUser(user);
        }
    }

    public class GenerateReportButtonListener implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            adminView.setUsers(userService.findAll());
            adminView.generateReport(employeeBookService.getReportAdmin());
        }
    }

}
