package launcher;

import controller.LoginController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import model.User;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryImpl;
import repository.security.RightsRolesRepositoryImpl;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.CustomerView;
import view.LoginView;

import java.sql.Connection;

public class ComponentFactory {
    private final LoginView loginView;
    private final LoginController loginController;

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    private final RightsRolesRepository rightsRolesRepository;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final BookService bookService;

    private static ComponentFactory instance;

    // trebuie sa fie thread safe

    public static ComponentFactory getInstance(Boolean componentsForTests, Stage stage){
        if (instance == null){
            instance = new ComponentFactory(componentsForTests, stage);
        }
        return instance;
    }



    public ComponentFactory(Boolean componentsForTests, Stage stage) {

        Connection connection = new DatabaseConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryImpl(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceImpl(userRepository, rightsRolesRepository);
        this.loginView = new LoginView(stage);

        this.bookRepository = new BookRepositoryMySQL(connection);  //avem nevoie de book service pt ca nu putem apela bookrepo

        this.bookService = new BookServiceImpl(new BookRepositoryMySQL(connection));

        this.userService = new UserServiceImpl(new UserRepositoryMySQL(connection, rightsRolesRepository));
        this.loginController = new LoginController(loginView, authenticationService, bookService, userService);

    }



    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public static ComponentFactory getInstance() {
        return instance;
    }
}
