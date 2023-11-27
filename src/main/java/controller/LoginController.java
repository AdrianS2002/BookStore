package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Role;
import model.User;
import model.validator.Notification;
import model.validator.UserValidator;
import repository.book.BookRepository;
import service.book.BookService;
import service.user.AuthenticationService;
import view.AdminView;
import view.CustomerView;
import view.EmployeeView;
import view.LoginView;

import java.util.EventListener;
import java.util.List;

public class LoginController {

    private final LoginView loginView;

    private final BookService bookService;


    private final AuthenticationService authenticationService;


    public LoginController(LoginView loginView, AuthenticationService authenticationService, BookService bookService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.bookService = bookService;
        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());

    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()){
                loginView.setActionTargetText(loginNotification.getFormattedErrors());
            }else{
                loginView.setActionTargetText("LogIn Successfull!");
                List<Role> roles = loginNotification.getResult().getRoles();
                if(roles.stream().anyMatch(role -> role.getRole().equals("admin"))){
                   // loginView.getPrimaryStage().close();
                  //  new AdminController(new AdminView(loginView.getPrimaryStage()),loginNotification.getResult(), bookService);
                }
                else if (roles.stream().anyMatch(role -> role.getRole().equals("employee"))){
                    //loginView.getPrimaryStage().close();
                    new EmployeeController(new EmployeeView(loginView.getPrimaryStage()),loginNotification.getResult(), bookService);

                }
                else if (roles.stream().anyMatch(role -> role.getRole().equals("customer"))){
                    new EmployeeController(new EmployeeView(loginView.getPrimaryStage()),loginNotification.getResult(), bookService);

                    //new CustomerController( new CustomerView(loginView.getPrimaryStage()), loginNotification.getResult(), bookService);
                }

            }

        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register successful!");
            }
        }
    }
}