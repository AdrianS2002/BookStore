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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmployeeView {

    private TextField findedBook;
    private TextField bookId;
    TableView<Book> tableBook;
    TextField idBook, titleBook, authorBook, publishDateBook, quantityBook;
    private Button findBookButton;
    private Button deleteBookButton;
    private Button viewAllBooksButton;
    private Button generateReportButton;
    private Button addBookButton;
    private Button updateBookButton;
    private Button backButton;
    HBox hBoxTable;
    private Stage employeeStage;
    List<Book> books =  new ArrayList<>();
    public EmployeeView(Stage employeeStage) {
        this.employeeStage = employeeStage;
        employeeStage.setTitle("Employee Page");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gridPane.setBackground(background);


        Scene scene = new Scene(gridPane, 1200, 650);
        employeeStage.setScene(scene);

        initializeSceneTitle(gridPane);
        employeeStage.getIcons().add(new Image("file:icon.png"));
        initializeFields(gridPane);

        employeeStage.show();
    }
    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }
    private void initializeSceneTitle(GridPane gridPane){
        Text sceneTitle = new Text("Welcome to our employee page");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
        GridPane.setHalignment(sceneTitle, HPos.CENTER);
    }
    private void initializeFields(GridPane gridPane){
        Label buyBookLabel = new Label("Find a book:");
        buyBookLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(buyBookLabel, 0, 1);

        Label bookIdLabel = new Label("Book id:");
        bookIdLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(bookIdLabel, 0, 2);

        bookId = new TextField();
        bookId.setPromptText("Give the id of the book you want to find");
        bookId.setPrefColumnCount(10);
        bookId.getText();
        gridPane.add(bookId, 1, 2);

        findBookButton = new Button("Find book by Id");
        HBox signInButtonHBox = new HBox(10);
        signInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        signInButtonHBox.getChildren().add(findBookButton);
        findBookButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        findBookButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(signInButtonHBox, 2, 2);

        Label findedBookLabel = new Label("Find book:");
        findedBookLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(findedBookLabel, 0, 4);

        findedBook = new TextField();
        findedBook.setPrefColumnCount(20);
        findedBook.getText();
        gridPane.add(findedBook, 1, 4);

        viewAllBooksButton = new Button("View all books");
        HBox logInButtonHBox = new HBox(10);
        logInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        logInButtonHBox.getChildren().add(viewAllBooksButton);
        viewAllBooksButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        viewAllBooksButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(logInButtonHBox, 0, 5);

        generateReportButton = new Button("Generate Report");
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

        TableColumn<Book, Long> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setMinWidth(200);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, LocalDate> publishDateColumn = new TableColumn<>("Publish Date");
        publishDateColumn.setMinWidth(200);
        publishDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        TableColumn<Book, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(50);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        idBook = new TextField();
        idBook.setPromptText("Id");
        idBook.setMinWidth(50);

        titleBook = new TextField();
        titleBook.setPromptText("Title");
        titleBook.setMinWidth(100);

        authorBook = new TextField();
        authorBook.setPromptText("Author");
        authorBook.setMinWidth(100);

        publishDateBook = new TextField();
        publishDateBook.setPromptText("Publish Date");
        publishDateBook.setMinWidth(100);

        quantityBook = new TextField();
        quantityBook.setPromptText("Quantity");
        quantityBook.setMinWidth(50);

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

        updateBookButton = new Button("Update");
        updateBookButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        updateBookButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        updateBookButton.setPrefWidth(100);

        hBoxTable = new HBox();

        hBoxTable.setPadding(new Insets(10, 10, 10, 10));
        hBoxTable.setSpacing(10);
        hBoxTable.getChildren().addAll(idBook, titleBook, authorBook, publishDateBook, quantityBook, addBookButton, deleteBookButton,updateBookButton);
        hBoxTable.setVisible(false);

        tableBook = new TableView<>();
        tableBook.getColumns().addAll(idColumn, titleColumn, authorColumn, publishDateColumn, quantityColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableBook,hBoxTable);
        tableBook.setVisible(false);
        gridPane.add(vBox, 0, 6, 2, 1);


    }



    public void setBooks(List<Book> all) {
        tableBook.getItems().clear();
        tableBook.getItems().addAll(all);


    }

    public void showTableOfBooksForEmployee(boolean show) {
        tableBook.setVisible(show);
        hBoxTable.setVisible(show);
    }

    public void viewAllBooksButtonListenerForEmployee(EventHandler<ActionEvent> viewAllBooksButtonListener) {
        viewAllBooksButton.setOnAction(viewAllBooksButtonListener);
    }
}


