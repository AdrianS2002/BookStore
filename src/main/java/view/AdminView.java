package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import model.Book;
import model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AdminView {

    private TextField findedUser;
    private TextField userId;
    TableView<User> tableUser;
    TextField idUser, username, rolesUser;
    private Button findBookButton;
    private Button deleteBookButton;
    private Button viewAllUsersButton;
    private Button generateReportButton;
    private Button addBookButton;
    private Button backButton;
    HBox hBoxTable;
    private Stage adminStage;
    List<Book> books =  new ArrayList<>();
    public AdminView(Stage adminStage) {
        this.adminStage = adminStage;
        adminStage.setTitle("Admin Page");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gridPane.setBackground(background);


        Scene scene = new Scene(gridPane, 980, 650);
        adminStage.setScene(scene);

        initializeSceneTitle(gridPane);
        adminStage.getIcons().add(new Image("file:icon.png"));
        initializeFields(gridPane);

        adminStage.show();
    }
    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }
    private void initializeSceneTitle(GridPane gridPane){
        Text sceneTitle = new Text("Welcome to our admin page");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
        GridPane.setHalignment(sceneTitle, HPos.CENTER);
    }
    private void initializeFields(GridPane gridPane){
        Label buyBookLabel = new Label("Find a user:");
        buyBookLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(buyBookLabel, 0, 1);

        Label bookIdLabel = new Label("User id:");
        bookIdLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(bookIdLabel, 0, 2);

        userId = new TextField();
        userId.setPromptText("Give the id of the user you want to find");
        userId.setPrefColumnCount(10);
        userId.getText();
        gridPane.add(userId, 1, 2);

        findBookButton = new Button("Find user by Id");
        HBox signInButtonHBox = new HBox(10);
        signInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        signInButtonHBox.getChildren().add(findBookButton);
        findBookButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        findBookButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(signInButtonHBox, 2, 2);

        Label findedBookLabel = new Label("Find book:");
        findedBookLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(findedBookLabel, 0, 4);

        findedUser = new TextField();
        findedUser.setPrefColumnCount(20);
        findedUser.getText();
        gridPane.add(findedUser, 1, 4);

        viewAllUsersButton = new Button("View all users");
        HBox logInButtonHBox = new HBox(10);
        logInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        logInButtonHBox.getChildren().add(viewAllUsersButton);
        viewAllUsersButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        viewAllUsersButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(logInButtonHBox, 0, 5);

        generateReportButton = new Button("Generate employee report");
        HBox generateReportButtonHBox = new HBox(10);
        generateReportButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        generateReportButtonHBox.getChildren().add(generateReportButton);
        generateReportButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        generateReportButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(generateReportButtonHBox, 1, 5);


        backButton = new Button("Back");
        HBox backButtonHBox = new HBox(10);
        backButtonHBox.setAlignment(Pos.BOTTOM_CENTER);
        backButtonHBox.getChildren().add(backButton);
        backButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        backButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(backButtonHBox, 0, 7);

        TableColumn<User, Long> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(200);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, List<String>> rolesUserColumn = new TableColumn<>("Roles");
        rolesUserColumn.setMinWidth(200);
        rolesUserColumn.setCellValueFactory(new PropertyValueFactory<>("rolesUser"));

        idUser = new TextField();
        idUser.setPromptText("Id");
        idUser.setMinWidth(50);

        username = new TextField();
        username.setPromptText("username");
        username.setMinWidth(100);

        rolesUser = new TextField();
        rolesUser.setPromptText("roles");
        rolesUser.setMinWidth(100);


        addBookButton = new Button("Add");
        addBookButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        addBookButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        addBookButton.setPrefWidth(100);

        //addBookButton.setOnAction(e -> addButtonClicked());

        deleteBookButton = new Button("Delete");
        deleteBookButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        deleteBookButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        deleteBookButton.setPrefWidth(100);

        //deleteBookButton.setOnAction(e -> deleteButtonClicked());

        hBoxTable = new HBox();

        hBoxTable.setPadding(new Insets(10, 10, 10, 10));
        hBoxTable.setSpacing(10);
        hBoxTable.getChildren().addAll(idUser, username, rolesUser, addBookButton, deleteBookButton);
        hBoxTable.setVisible(false);
        tableUser = new TableView<>();
        tableUser.getColumns().addAll(idColumn, usernameColumn, rolesUserColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableUser,hBoxTable);
        tableUser.setVisible(false);
        gridPane.add(vBox, 0, 6, 2, 1);


    }

    public void setUsers(List<User> all) {
        tableUser.getItems().clear();
        tableUser.getItems().addAll(all);
    }

    public void showTableOfBooksForUser(boolean show) {
        tableUser.setVisible(show);
        hBoxTable.setVisible(show);
    }


    public void viewAllUsersButtonListenerForAdmin(EventHandler<ActionEvent> viewAllUsersButtonListener) {

        viewAllUsersButton.setOnAction(viewAllUsersButtonListener);
    }
}


