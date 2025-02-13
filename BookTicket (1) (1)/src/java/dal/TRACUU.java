/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.PassagerInfor;
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class TRACUU extends DBContext {

    public List<PassagerInfor> getAll() {
        List<PassagerInfor> list = new ArrayList<>();
        String sql = "select p.PassengerId,p.PassengerName,c.ClassName,t.BookingDate ,f.DepartureTime,f.ArrivalTime,\n"
                + "ar.AirportName,arr.AirportName,ft.FlightNumber,t.price,t.IsPaid\n"
                + "from Passenger p join Ticket t on p.PassengerId = t.TicketIdPassenger join Account a on p.PassengerId = a.PassengerId join Class c on c.ClassId = t.ClassId JOIN  FlightTime f\n"
                + "on f.IdFlightTime = t.IdFlightTime\n"
                + "join Flight ft on f.IdFlight = ft.IdFlight \n"
                + "join Airport ar on ft.DepartureAirportId = ar.AirportId\n"
                + "join Airport arr on ft.ArrivalAirportId = arr.AirportId";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new PassagerInfor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getInt(10), rs.getInt(11)));
            }

        } catch (SQLException ex) {

        }
        return list;
    }

    public PassagerInfor getById(int id) {

        String sql = "SELECT \n"
                + "    p.PassengerId,\n"
                + "    p.PassengerName,\n"
                + "    c.ClassName,\n"
                + "    t.BookingDate,\n"
                + "    f.DepartureTime,\n"
                + "    f.ArrivalTime,\n"
                + "    ar.AirportName AS DepartureAirport,\n"
                + "    arr.AirportName AS ArrivalAirport,\n"
                + "    ft.FlightNumber,\n"
                + "    SUM(t.Price) AS TotalAmount,\n"
                + "    t.IsPaid\n"
                + "FROM \n"
                + "    Passenger p \n"
                + "RIGHT JOIN \n"
                + "    Ticket t ON p.PassengerId = t.TicketIdPassenger \n"
                + "JOIN \n"
                + "    Account a ON p.PassengerId = a.PassengerId \n"
                + "JOIN \n"
                + "    Class c ON c.ClassId = t.ClassId \n"
                + "JOIN \n"
                + "    FlightTime f ON f.IdFlightTime = t.IdFlightTime \n"
                + "JOIN \n"
                + "    Flight ft ON f.IdFlight = ft.IdFlight \n"
                + "JOIN \n"
                + "    Airport ar ON ft.DepartureAirportId = ar.AirportId \n"
                + "JOIN \n"
                + "    Airport arr ON ft.ArrivalAirportId = arr.AirportId \n"
                + "WHERE \n"
                + "    p.PassengerId = 2\n"
                + "GROUP BY \n"
                + "    p.PassengerId, \n"
                + "    p.PassengerName, \n"
                + "    c.ClassName, \n"
                + "    t.BookingDate, \n"
                + "    f.DepartureTime, \n"
                + "    f.ArrivalTime, \n"
                + "    ar.AirportName, \n"
                + "    arr.AirportName, \n"
                + "    ft.FlightNumber, \n"
                + "    t.IsPaid;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return new PassagerInfor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getInt(10), rs.getInt(11));

            }
        } catch (SQLException e) {

        }
        return null;

    }

    public List<PassagerInfor> getName(String name) {
        List<PassagerInfor> list = new ArrayList<>();
        String sql = "select p.PassengerId,p.PassengerName,c.ClassName,t.BookingDate ,f.DepartureTime,f.ArrivalTime,\n"
                + "ar.AirportName,arr.AirportName,ft.FlightNumber,t.price,t.IsPaid\n"
                + "from Passenger p join Ticket t on p.PassengerId = t.TicketIdPassenger join Account a on p.PassengerId = a.PassengerId join Class c on c.ClassId = t.ClassId JOIN  FlightTime f\n"
                + "on f.IdFlightTime = t.IdFlightTime\n"
                + "join Flight ft on f.IdFlight = ft.IdFlight \n"
                + "join Airport ar on ft.DepartureAirportId = ar.AirportId\n"
                + "join Airport arr on ft.ArrivalAirportId = arr.AirportId where p.PassengerName =?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                ;
                list.add(new PassagerInfor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getInt(10), rs.getInt(11)));
            }
        } catch (SQLException e) {

        }
        return list;

    }

    public List<PassagerInfor> getlistbyPage(List<PassagerInfor> list, int start, int end) {
        if (start >= list.size()) {
            return new ArrayList<>();
        }
        end = Math.min(end, list.size());
        return list.subList(start, end);
    }

    public static void main(String[] args) {
        TRACUU t = new TRACUU();
        List< PassagerInfor> p = t.getName("Đào Thị Ánh");
        System.out.println(p);
    }
}
