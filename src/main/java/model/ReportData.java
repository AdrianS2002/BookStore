package model;

public class ReportData {

    private Long id;
    private Book book;

    private int quantity;


    public ReportData(Long id, Book book, int quantity, Long userid) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
        this.userid = userid;
    }

    private Long userid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "ReportData{" +
                "id=" + id +
                ", book=" + book +
                ", quantity=" + quantity +
                ", userid=" + userid +
                '}';
    }
}
