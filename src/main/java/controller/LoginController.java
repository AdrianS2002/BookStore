package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Role;
import model.User;
import model.validator.Notification;
import model.validator.UserValidator;
import repository.book.BookRepository;
import service.book.BookService;
import service.employeeBook.EmployeeBookService;
import service.user.AuthenticationService;
import service.user.UserService;
import view.AdminView;
import view.CustomerView;
import view.EmployeeView;
import view.LoginView;

import java.util.EventListener;
import java.util.List;

public class LoginController {

    private final LoginView loginView;

    private final BookService bookService;

    private final UserService userService;


    private final AuthenticationService authenticationService;

    private final EmployeeBookService employeeBookService;


    public LoginController(LoginView loginView, AuthenticationService authenticationService, BookService bookService, UserService userService, EmployeeBookService employeeBookService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.bookService = bookService;
        this.userService = userService;
        this.employeeBookService = employeeBookService;
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
                if(roles.stream().anyMatch(role -> role.getRole().equals("administrator"))){
                   // loginView.getPrimaryStage().close();
                    new AdminController(new AdminView(loginView.getPrimaryStage()),loginNotification.getResult(), authenticationService, userService,employeeBookService,bookService);
                }
                else if (roles.stream().anyMatch(role -> role.getRole().equals("employee"))){
                    //loginView.getPrimaryStage().close();
                    new EmployeeController(new EmployeeView(loginView.getPrimaryStage()),loginNotification.getResult(), bookService, employeeBookService,authenticationService,userService);

                }
                else if (roles.stream().anyMatch(role -> role.getRole().equals("customer"))){
                   // new EmployeeController(new EmployeeView(loginView.getPrimaryStage()),loginNotification.getResult(), bookService);

                    new CustomerController( new CustomerView(loginView.getPrimaryStage()), loginNotification.getResult(), bookService,userService, authenticationService,employeeBookService);
                      //new AdminController(new AdminView(loginView.getPrimaryStage()),loginNotification.getResult(),authenticationService);
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