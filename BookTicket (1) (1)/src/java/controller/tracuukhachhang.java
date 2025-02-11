/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.TRACUU;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.PassagerInfor;

/**
 *
 * @author Administrator
 */
public class tracuukhachhang extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            out.println("<title>Servlet tracuukhachhang</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet tracuukhachhang at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        TRACUU d = new TRACUU();
        List<PassagerInfor> list = null;
        PassagerInfor p = null;
        int page = 1;
        int numpage = 100;
        int numberpage = 0;
        List<PassagerInfor> list22 = null;
        if (id != null && !id.isEmpty()) {
            int idd = Integer.parseInt(id);

            list = new ArrayList<>();

            p = d.getById(idd);
        } else {
            list = d.getAll();
            int num1 = list.size();

            numberpage = (num1 % numpage == 0) ? (num1 / numpage) : (num1 / numpage) + 1;

            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end = 0;
            start = (page - 1) * numpage;
            end = Math.min(page * numpage, num1);
            list22 = d.getlistbyPage(list, start, end);
            if (page == numberpage && num1 % numpage != 0) {
              
                list22 = list.subList(start, num1);
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("passengerId", id);
        session.setAttribute("pp", p);
        session.setAttribute("list", list22);
        session.setAttribute("page", page);
        session.setAttribute("num", numberpage);
        request.getRequestDispatcher("admin_tracuukh.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchname = request.getParameter("search");
        HttpSession session = request.getSession();
        TRACUU d = new TRACUU();
        List<PassagerInfor> list = null;
        if (searchname != null && !searchname.isEmpty()) {
            list = d.getName(searchname);
        }
        session.setAttribute("list", list);
        request.getRequestDispatcher("admin_tracuukh.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
