package repository.employeeBook;

import model.ReportData;
import model.User;

import java.util.List;

public interface EmployeeBook {


    List<ReportData> getReport(Long userId);
    List<ReportData> getReportAdmin();
}
