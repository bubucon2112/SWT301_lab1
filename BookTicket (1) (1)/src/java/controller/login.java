/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Acount;
import dal.DAODangNhap;
import dal.DAOEMPLOYEE;
import model.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Employee;
import model.Passager;

/**
 *
 * @author Administrator
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     request.getRequestDispatcher("dangnhap.jsp").forward(request, response);    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("register")) {
            String username = request.getParameter("tendangnhap");
            String matkhau = request.getParameter("matkhau");
            String matkhaulai = request.getParameter("matkhaulai");
            String paName = request.getParameter("hoten");
            String paTel = request.getParameter("sdt");
            String paEmail = request.getParameter("email");
            String paID = request.getParameter("idcard");
            DAODangNhap dao = new DAODangNhap();
            if (!dao.checkEmail1(paEmail)) {
                request.setAttribute("error", "Email đã được sủ dụng");
                request.setAttribute("ten", username);
                request.setAttribute("matkhau", matkhau);
                request.setAttribute("again", matkhaulai);
                request.setAttribute("name", paName);
                request.setAttribute("phone", paTel);
                request.setAttribute("email", paEmail);
                request.setAttribute("idcard", paID);
                request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
                return;
            }
            if (dao.CheckUser(username)) {
                request.setAttribute("error", "Tên người dùng đã tồn tại");
                request.setAttribute("ten", username);
                request.setAttribute("matkhau", matkhau);
                request.setAttribute("again", matkhaulai);
                request.setAttribute("name", paName);
                request.setAttribute("phone", paTel);
                request.setAttribute("email", paEmail);
                request.setAttribute("idcard", paID);
                request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
                return;
            }

            if (!matkhau.equals(matkhaulai)) {
                request.setAttribute("error", "Mật khẩu không khớp.");
                request.setAttribute("ten", username);
                request.setAttribute("matkhau", matkhau);
                request.setAttribute("again", matkhaulai);
                request.setAttribute("name", paName);
                request.setAttribute("phone", paTel);
                request.setAttribute("email", paEmail);
                request.setAttribute("idcard", paID);
                request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
                return;
            }

            Role r = new Role(3, "Khách hàng");
            Passager p = new Passager(paName, paTel, paID, paEmail);
            boolean check = dao.addPassenger(p);
           
            Acount account = new Acount(username, matkhau, r, dao.getIDPassenger());
            boolean add = dao.addAccount(account);

            if (add) {
                request.setAttribute("error", "Đăng kí thành công!");
                request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
            } else {
                request.setAttribute("ten", username);
                request.setAttribute("matkhau", matkhau);
                request.setAttribute("again", matkhaulai);
                request.setAttribute("name", paName);
                request.setAttribute("phone", paTel);
                request.setAttribute("email", paEmail);
                request.setAttribute("idcard", paID);
                request.setAttribute("error", "Đăng kí tài khoản thất bại");
                request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
            }

        } else if (action.equals("login") && !action.isEmpty()) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            DAODangNhap dao = new DAODangNhap();

            if (!dao.CheckUser(username)) {

                request.setAttribute("error", "Tên tài khoản không tồn tại!");
                request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
                return;
            }

            Acount account = dao.Dangnhap(username, password);
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            if (account != null && password.equals(account.getPass())) {

                HttpSession session = request.getSession();
                int employeeid = account.getEmployeeid();
                int passengerID = account.getPassagerid();
                session.setAttribute("employeeid", employeeid);
                session.setAttribute("passengerId", passengerID);
                session.setAttribute("account", account);
                session.setAttribute("role", account.getR().getRolename());
                session.setAttribute("user", username);
                switch (account.getR().getRolename()) {
                    case "Admin":
                        response.sendRedirect("admin");
                        break;
                    case "Nhân viên":

                        response.sendRedirect("nhanvien");
                        break;
                    case "Khách hàng":
                        request.getRequestDispatcher("home_dangnhap.jsp").forward(request, response);
                        break;

                }
            } else {

                request.setAttribute("error", "Sai Mật Khẩu");
                request.getRequestDispatcher("dangnhap.jsp").forward(request, response);

            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
