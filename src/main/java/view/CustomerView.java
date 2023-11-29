package view;


import controller.CustomerController;
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
import javafx.stage.Stage;
import javafx.scene.image.Image;
import model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerView {

    private TextField quantitySelected;
    private TextField bookId;

    TableView<Book> table;

    TextField idBook, titleBook, authorBook, publishDateBook, quantityBook;

    private Button buyButton;

    private Button viewAllBooksButton;

    private Button backButton;
    private Stage customerStage;


    List<Book> books =  new ArrayList<>();

    public CustomerView(Stage customerStage) {
        this.customerStage = customerStage;
        customerStage.setTitle("Customer Page");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gridPane.setBackground(background);


        Scene scene = new Scene(gridPane, 850, 540);
        customerStage.setScene(scene);

        initializeSceneTitle(gridPane);
        customerStage.getIcons().add(new Image("file:icon.png"));
        initializeFields(gridPane);

        customerStage.show();
    }
    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }
    private void initializeSceneTitle(GridPane gridPane){
        Text sceneTitle = new Text("Welcome to our customer page");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
        GridPane.setHalignment(sceneTitle, HPos.CENTER);
    }
    private void initializeFields(GridPane gridPane){
        Label buyBookLabel = new Label("Buy a book:");
        buyBookLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(buyBookLabel, 0, 1);

        Label bookIdLabel = new Label("Book id:");
        bookIdLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(bookIdLabel, 0, 2);

        bookId = new TextField();
        bookId.setPromptText("Give the id of the book you want to buy");
        bookId.setPrefColumnCount(20);
        bookId.getText();
        gridPane.add(bookId, 1, 2);

        Label quantitySelectedLabel = new Label("Quantity:");
        quantitySelectedLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        gridPane.add(quantitySelectedLabel, 0, 3);

        quantitySelected = new TextField();
        quantitySelected.setPromptText("Quantity Selected");
        quantitySelected.setPrefColumnCount(10);
        quantitySelected.getText();
        gridPane.add(quantitySelected, 1, 3);

        buyButton = new Button("Buy book");
        HBox signInButtonHBox = new HBox(10);
        signInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        signInButtonHBox.getChildren().add(buyButton);
        buyButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        buyButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(signInButtonHBox, 0, 4);

        viewAllBooksButton = new Button("View all books");
        HBox logInButtonHBox = new HBox(10);
        logInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        logInButtonHBox.getChildren().add(viewAllBooksButton);
        viewAllBooksButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        viewAllBooksButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(logInButtonHBox, 1, 4);

        backButton = new Button("Back");
        HBox backButtonHBox = new HBox(10);
        backButtonHBox.setAlignment(Pos.BOTTOM_CENTER);
        backButtonHBox.getChildren().add(backButton);
        backButton.setStyle("-fx-background-color: #7071E8; -fx-text-fill: #FFC7C7;");
        backButton.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
        gridPane.add(backButtonHBox, 0, 5);

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

        table = new TableView<>();
        //table.setItems();
        table.getColumns().addAll(idColumn, titleColumn, authorColumn, publishDateColumn, quantityColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        table.setVisible(false);
        gridPane.add(vBox, 0, 6, 2, 1);


    }

    public int getQuantitySelected() {
        return Integer.parseInt(quantitySelected.getText());
    }

    public int getBookId() {
        return Integer.parseInt(bookId.getText()) ;
    }

    public Stage getCustomerStage() {
        return customerStage;
    }

    public void setBooks(List<Book> all) {
        table.getItems().clear();
        table.getItems().addAll(all);


    }

    public void setBuyButton(EventHandler<ActionEvent> buyButtonListener) {
        buyButton.setOnAction(buyButtonListener);
    }

    public void showTable(boolean show) {
        table.setVisible(show);
    }

    public void viewAllBooksButtonListener(EventHandler<ActionEvent> viewAllBooksButtonListener) {
        viewAllBooksButton.setOnAction(viewAllBooksButtonListener);
    }

    // Change the method name to setBackButtonListener
    public void setBackButtonListener(EventHandler<ActionEvent> backButtonListener) {
        backButton.setOnAction(backButtonListener);
    }

}
