/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.HoaDAO;
import dao.LoaiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Hoa;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductManagement", urlPatterns = {"/ProductManagement"})
public class ProductManagement extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HoaDAO hoaDao = new HoaDAO();
        LoaiDAO loaiDao = new LoaiDAO();

        String action = "list";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        switch (action) {
            case "list":
                ArrayList<Hoa> dsHoa = hoaDao.getAll();
                request.setAttribute("dsHoa", dsHoa);
                request.getRequestDispatcher("admin/list-product.jsp").forward(request, response);
                break;
            case "add":
                request.setAttribute("dsLoai", loaiDao.getAll());
                request.getRequestDispatcher("admin/add-product.jsp").forward(request, response);
                break;
            case "delete":
                String maHoaXoa = request.getParameter("mahoa"); 
                if (maHoaXoa != null) {
                    int maHoa = Integer.parseInt(maHoaXoa);
                    boolean isDeleted = hoaDao.Delete(maHoa);
                    if (isDeleted) {
                        request.setAttribute("message", "Xóa sản phẩm thành công.");
                    }
                }
                request.getRequestDispatcher("ProductManagement?action=list").forward(request, response);
                break;
            case "insert":

//            case "insert":
//                int mahoa = hoaDao.getNextMahoa();
//
//                String tenhoa = request.getParameter("tenhoa");
//                String gia = request.getParameter("gia");
//                double giaDouble = Double.parseDouble(gia);
//                String hinhanh = request.getParameter("hinhanh");
//                String theloai = request.getParameter("theloai");
//
//                int maLoai = 0; 
//                if (theloai != null && !theloai.trim().isEmpty()) {
//                    maLoai = Integer.parseInt(theloai);
//                }
//
//                Hoa hoa = new Hoa(mahoa, tenhoa, giaDouble, hinhanh, maLoai, new Date(System.currentTimeMillis()));
//                
//                hoaDao.Insert(hoa);
//                
//                response.sendRedirect("ProductManagement?action=list");
//                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
