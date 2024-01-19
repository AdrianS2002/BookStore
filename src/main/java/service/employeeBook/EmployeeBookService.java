package service.employeeBook;

import model.ReportData;

import java.util.List;

public interface EmployeeBookService {

    List<ReportData> getReport(Long userId);
    List<ReportData> getReportAdmin();

}
