package service.employeeBook;

import model.ReportData;
import repository.employeeBook.EmployeeBook;

import java.util.List;

public class EmployeeBookServiceImpl implements EmployeeBookService{

    final EmployeeBook employeeBookMySQL;

    public EmployeeBookServiceImpl(EmployeeBook employeeBookMySQL) {
        this.employeeBookMySQL = employeeBookMySQL;
    }

    @Override
    public List<ReportData> getReport(Long userId) {
       return employeeBookMySQL.getReport(userId);
    }

    @Override
    public List<ReportData> getReportAdmin() {
         return employeeBookMySQL.getReportAdmin();
    }
}
