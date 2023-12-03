package repository.employeeBook;

import model.Book;
import model.ReportData;
import model.builder.BookBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBookMySQL implements EmployeeBook {

    private final Connection connection;

    public EmployeeBookMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ReportData> getReport(Long userId) {
        List<ReportData> reportDataList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM employee_book WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long userId1 = resultSet.getLong("user_id");
                Long bookId = resultSet.getLong("book_id");
                Integer quantity = resultSet.getInt("quantity");
                String sql2= "SELECT * FROM book WHERE id = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
                preparedStatement1.setLong(1, bookId);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                resultSet1.next();
                Book book = getBookFromResultSet(resultSet1);
                reportDataList.add(new ReportData(id,book, quantity,userId1));

            }
            return reportDataList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<ReportData> getReportAdmin() {
        List<ReportData> reportDataList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM employee_book";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long userId1 = resultSet.getLong("user_id");
                Long bookId = resultSet.getLong("book_id");
                Integer quantity = resultSet.getInt("quantity");
                String sql2= "SELECT * FROM book WHERE id = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
                preparedStatement1.setLong(1, bookId);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                resultSet1.next();
                Book book = getBookFromResultSet(resultSet1);
                reportDataList.add(new ReportData(id,book, quantity,userId1));

            }
            return reportDataList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException{
        return new BookBuilder()
                .setId(resultSet.getLong("id"))
                .setTitle(resultSet.getString("title"))
                .setAuthor(resultSet.getString("author"))
                .setPublishedDate(new java.sql.Date(resultSet.getDate("publishedDate").getTime()).toLocalDate())
                .setQuantity(resultSet.getInt("quantity"))
                .build();
    }
}
