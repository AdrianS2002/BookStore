package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Role;
import model.User;

import java.util.List;
import java.util.stream.Collectors;


public class AdminView {

    private TextField foundUser;



    private TextField userId;
    TableView<User> tableUser;
    TextField idUser, username, rolesUser, passwordUser;
    private Button findUserButton;
    private Button deleteUserButton;
    private Button updateUserButton;
    private Button viewAllUsersButton;
    private Button generateReportButton;
    private Button addUserButton;
    private Button backButton;
    HBox hBoxTable;
    private Stage adminStage;

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

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Welcome to our admin page");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
        GridPane.setHalignment(sceneTitle, HPos.CENTER);
    }

    private void initializeFields(GridPane gridPane) {
        Label findUserById = new Label("Find a user:");
        findUserById.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(findUserById, 0, 1);

        Label userIdLabel = new Label("User id:");
        userIdLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(userIdLabel, 0, 2);

        userId = new TextField();
        userId.setPromptText("Give the id of the user you want to find");
        userId.setPrefColumnCount(10);
        userId.getText();
        gridPane.add(userId, 1, 2);

        findUserButton = new Button("Find user by Id");
        HBox signInButtonHBox = new HBox(10);
        signInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        signInButtonHBox.getChildren().add(findUserButton);
        findUserButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        findUserButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(signInButtonHBox, 2, 2);

        foundUser = new TextField();
        foundUser.setPrefColumnCount(20);
        foundUser.getText();
        gridPane.add(foundUser, 1, 4);

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

        TableColumn<User, List<Role>> rolesUserColumn = new TableColumn<>("Roles");
        rolesUserColumn.setMinWidth(200);
        rolesUserColumn.setCellValueFactory(new PropertyValueFactory<>("roles"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setMinWidth(200);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        idUser = new TextField();
        idUser.setPromptText("Id");
        idUser.setMinWidth(50);

        username = new TextField();
        username.setPromptText("username");
        username.setMinWidth(100);

        rolesUser = new TextField();
        rolesUser.setPromptText("roles");
        rolesUser.setMinWidth(100);

        passwordUser = new TextField();
        passwordUser.setPromptText("password");
        passwordUser.setMinWidth(100);


        addUserButton = new Button("Add");
        addUserButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        addUserButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        addUserButton.setPrefWidth(100);


        deleteUserButton = new Button("Delete");
        deleteUserButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        deleteUserButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        deleteUserButton.setPrefWidth(100);


        updateUserButton = new Button("Update");
        updateUserButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        updateUserButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        updateUserButton.setPrefWidth(100);


        hBoxTable = new HBox();

        hBoxTable.setPadding(new Insets(10, 10, 10, 10));
        hBoxTable.setSpacing(10);
        hBoxTable.getChildren().addAll(idUser, username, rolesUser, passwordUser, addUserButton, deleteUserButton, updateUserButton);
        hBoxTable.setVisible(false);
        tableUser = new TableView<>();
        tableUser.getColumns().addAll(idColumn, usernameColumn, rolesUserColumn, passwordColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableUser, hBoxTable);
        tableUser.setVisible(false);
        gridPane.add(vBox, 0, 6, 2, 1);


    }

    public void setUsers(List<User> all) {
        tableUser.getItems().clear();
        List<UserPrintUI> list = all.stream().map(user -> new UserPrintUI(user.getId(), user.getUsername(),
                user.getRoles().stream().map(role -> role.getRole()).collect(Collectors.joining(",")))).toList();//flat la rol
        tableUser.getItems().addAll(all);

    }
    public void setFoundUser(User foundUser) {
        this.foundUser.setText(foundUser.toString());
    }

    public class UserPrintUI {
        private Long id;
        private String email;

        private String roles;

        public UserPrintUI(Long id, String email, String roles) {
            this.id = id;
            this.email = email;
            this.roles = roles;

        }

        public Long getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getRoles() {
            return roles;
        }
    }

    public String getUsername() {
        return username.getText();
    }

    public String getRoles() {
        return rolesUser.getText();
    }

    public String getPassword() {
        return passwordUser.getText();
    }


    public void addUserButtonListener(EventHandler<ActionEvent> addUserButtonListener) {
        addUserButton.setOnAction(addUserButtonListener);

    }

    public Long getId() {
        return Long.parseLong(idUser.getText());
    }

    public Long getIdUser() {
        return Long.parseLong(userId.getText());
    }

    public void showTableOfUser(boolean show) {
        tableUser.setVisible(show);
        hBoxTable.setVisible(show);
    }


    public void viewAllUsersButtonListenerForAdmin(EventHandler<ActionEvent> viewAllUsersButtonListener) {

        viewAllUsersButton.setOnAction(viewAllUsersButtonListener);
    }

    public void updateUserButton(EventHandler<ActionEvent> updateUserButtonListener) {
        updateUserButton.setOnAction(updateUserButtonListener);
    }

    public void deleteUserButton(EventHandler<ActionEvent> deleteUserButtonListener) {
        deleteUserButton.setOnAction(deleteUserButtonListener);
    }

    public void findUserButtonListener(EventHandler<ActionEvent> findUserButtonListener) {
        findUserButton.setOnAction(findUserButtonListener);
    }
}


