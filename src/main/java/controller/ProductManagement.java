/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.HoaDAO;
import dao.LoaiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Hoa;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductManagement", urlPatterns = {"/ProductManagement"})
@MultipartConfig
public class ProductManagement extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
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
                if(request.getMethod().equalsIgnoreCase("GET")){
                   request.setAttribute("dsLoai", loaiDao.getAll());
                   request.getRequestDispatcher("admin/add-product.jsp").forward(request, response);
                }
                
                String tenhoa = request.getParameter("tenhoa");
                double gia = Double.parseDouble(request.getParameter("gia"));
                Part part = request.getPart("hinhanh");
                int maloai = Integer.parseInt(request.getParameter("theloai"));
                // Xu li upload
                String realPath = request.getServletContext().getRealPath("/assets/images/products");
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                part.write(realPath + "/" + filename);
                // Add product
                Hoa hoa = new Hoa(0, tenhoa, gia, filename, maloai, new Date(new java.util.Date().getTime()));
                if(hoaDao.Insert(hoa)){
                   request.setAttribute("success", "Thêm sản phẩm thành công");
                }
                else{
                   request.setAttribute("error", "Thêm sản phẩm thất bại");
                }
                request.getRequestDispatcher("ProductManagement?action=list").forward(request, response);
                break;
            case "edit":
                if(request.getMethod().equalsIgnoreCase("GET")){
                   request.setAttribute("dsLoai", loaiDao.getAll());
                   request.getRequestDispatcher("admin/add-product.jsp").forward(request, response);
                }
                break;
            case "delete":
                String maHoaXoa = request.getParameter("mahoa"); 
                if (maHoaXoa != null) {
                    int maHoa = Integer.parseInt(maHoaXoa);
                    boolean isDeleted = hoaDao.Delete(maHoa);
                    if (isDeleted) {
                       request.setAttribute("message", "Xóa sản phẩm thành công.");
                    }
                    else{
                       request.setAttribute("error", "Xóa sản phẩm thất bại.");
                    }
                }
                request.getRequestDispatcher("ProductManagement?action=list").forward(request, response);
                break;
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
