/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import model.Feedback;

/**
 *
 * @author Administrator
 */
public class DAOEMPLOYEE extends DBContext {

    public List<Employee> getall() {
        List<Employee> list = new ArrayList<>();
        String sql = "select * from Employee";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException E) {

        }
        return list;
    }

    public Employee employ(int id) {
        String sql = "select * from Employee where EmployeeId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

            }
        } catch (SQLException e) {

        }
        return null;
    }

    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE [dbo].[Employee]\n"
                + "SET \n"
                + "    [EmployeeName] = ?, \n"
                + "    [EmployeeTel] = ?, \n"
                + "    [EmployeeEmail] = ?\n"
                + "WHERE \n"
                + "    [EmployeeId] = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getEmployeeName());
            stmt.setString(2, employee.getTel());
            stmt.setString(3, employee.getEmail());
            stmt.setInt(4, employee.getEmployeeid());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Update error: " + ex.getMessage());
            return false;
        }
    }

    public boolean updateFeedback(String phanhoi, int traloi, int employid, List<Feedback> feedbackList) {
        String sql = "UPDATE [dbo].[BinhLuan]\n"
                + "SET [PhanHoiNhanVien] = ?,              \n"
                + "    [NgayPhanHoiNhanVien] = GETDATE(), \n"
                + "    [DaTraLoi] = ?,                       \n"
                + "    [EmployID] = ?                        \n"
                + "WHERE [BinhLuanID] = ?;";  // Sửa WHERE clause để sử dụng tham số
        boolean isUpdated = false;

        try {

            for (Feedback feedback : feedbackList) {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, phanhoi);
                stmt.setInt(2, traloi);
                stmt.setInt(3, employid);
                stmt.setInt(4, feedback.getFeedbackId());
                isUpdated = stmt.executeUpdate() > 0 || isUpdated;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated; // Trả về true nếu ít nhất một bản ghi được cập nhật
    }

    public List<Feedback> getidnv(int Passengerid) {
        List<Feedback> list = new ArrayList<>();
        String sql = "select BinhLuanID from BinhLuan where PassengerID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Passengerid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int feedbackid = rs.getInt(1);
                list.add(new Feedback(feedbackid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        DAOEMPLOYEE dao = new DAOEMPLOYEE();

        int passengerId = 1003;
        int employId = 2;
        String feedbackResponse = "Cảm Ơn Quý Khách";

        List<Feedback> feedbackList = dao.getidnv(passengerId);

        if (feedbackList.isEmpty()) {
            System.out.println("Không tìm thấy phản hồi cho PassengerID: " + passengerId);
            return;
        }

        boolean result = dao.updateFeedback(feedbackResponse, 1, employId, feedbackList);

        if (result) {
            System.out.println("Cập nhật phản hồi thành công!");
        } else {
            System.out.println("Cập nhật phản hồi thất bại.");
        }
    }

}
