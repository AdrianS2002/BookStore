package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import javax.swing.text.html.ImageView;


public class LoginView {

    private TextField userTextField;
    private PasswordField passwordField;
    private Button signInButton;
    private Button logInButton;
    private Text actiontarget;

    private Stage primaryStage;

    public LoginView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Book Store");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gridPane.setBackground(background);


        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);

        initializeSceneTitle(gridPane);
        primaryStage.getIcons().add(new Image("file:icon.png"));
        initializeFields(gridPane);


        primaryStage.show();
    }

    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane){
        Text sceneTitle = new Text("Welcome to our Book Store");
        sceneTitle.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeFields(GridPane gridPane){
        Label userName = new Label("User Name:");
        userName.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(userName, 0, 1);

        userTextField = new TextField();
        userTextField.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(userTextField, 1, 1);

        Label password = new Label("Password");
        password.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(password, 0, 2);

        passwordField = new PasswordField();
        passwordField.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(passwordField, 1, 2);

        signInButton = new Button("Sign In");
        HBox signInButtonHBox = new HBox(10);
        signInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        signInButtonHBox.getChildren().add(signInButton);
        signInButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        signInButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(signInButtonHBox, 1, 4);

        logInButton = new Button("Log In");
        HBox logInButtonHBox = new HBox(10);
        logInButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        logInButtonHBox.getChildren().add(logInButton);
        logInButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        logInButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(logInButtonHBox, 0, 4);

        actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);
        actiontarget.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(actiontarget, 1, 6);
    }

    public String getUsername() {
        return userTextField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void setActionTargetText(String text){ this.actiontarget.setText(text);}

    public void addLoginButtonListener(EventHandler<ActionEvent> loginButtonListener) {
        logInButton.setOnAction(loginButtonListener);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void addRegisterButtonListener(EventHandler<ActionEvent> signInButtonListener) {
        signInButton.setOnAction(signInButtonListener);
    }
}
