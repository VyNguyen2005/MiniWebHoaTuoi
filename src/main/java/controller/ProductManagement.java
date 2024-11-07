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
import javax.servlet.http.HttpSession;
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
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        
        if(username == null || password == null){
           request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
        HoaDAO hoaDao = new HoaDAO();
        LoaiDAO loaiDao = new LoaiDAO();

        String action = "list";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        switch (action) {
            case "list":
                int pageIndex=1;
                int pageSize=5;
                if(request.getParameter("page")!=null){
                    pageIndex=Integer.parseInt(request.getParameter("page"));
                }
                int pageSum=(int)Math.ceil((double)hoaDao.getAll().size()/pageSize);
                
                ArrayList<Hoa> dsHoa = hoaDao.getBypage(pageIndex, pageSize);
                request.setAttribute("dsHoa", dsHoa);
                request.setAttribute("pageSum", pageSum);
                request.setAttribute("pageIndex", pageIndex);
//                ArrayList<Hoa> dsHoa = hoaDao.getAll();
                request.setAttribute("dsHoa", dsHoa);
                request.getRequestDispatcher("admin/list-product.jsp").forward(request, response);
                break;
            case "add":
                if (request.getMethod().equalsIgnoreCase("GET")) {
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
                if (hoaDao.Insert(hoa)) {
                    request.setAttribute("success", "Thêm sản phẩm thành công");
                } else {
                    request.setAttribute("error", "Thêm sản phẩm thất bại");
                }
                request.getRequestDispatcher("ProductManagement?action=list").forward(request, response);
                break;
            case "edit":
                if (request.getMethod().equalsIgnoreCase("GET")) {
                    int maHoa = Integer.parseInt(request.getParameter("mahoa"));
                    request.setAttribute("hoa", hoaDao.getById(maHoa));
                    request.setAttribute("dsLoai", loaiDao.getAll());
                    request.getRequestDispatcher("admin/edit-product.jsp").forward(request, response);
                } else if (request.getMethod().equalsIgnoreCase("POST")) {
                    int maHoa = Integer.parseInt(request.getParameter("mahoa"));
                    String tenHoa = request.getParameter("tenhoa");
                    double giaThanh = Double.parseDouble(request.getParameter("gia"));
                    Part p = request.getPart("hinhanh");
                    
                    int maLoai = Integer.parseInt(request.getParameter("theloai"));
                    String file = request.getParameter("oldImg");
                    if (p.getSize() > 0) {
                        String path = request.getServletContext().getRealPath("/assets/images/products");
                        file = Paths.get(p.getSubmittedFileName()).getFileName().toString();
                        p.write(path + "/" + file);

                    }
                    Hoa objUpdate = new Hoa(maHoa, tenHoa, giaThanh, file, maLoai, new Date(new java.util.Date().getTime()));
                    if(hoaDao.Update(objUpdate)){
                       request.setAttribute("success", "Cập nhật sản phẩm thành công");
                    }
                    else{
                       request.setAttribute("error", "Cập nhật sản phẩm thất bại");
                    }
                    request.getRequestDispatcher("ProductManagement?action=list").forward(request, response);
                }

                break;
            case "delete":
                String maHoaXoa = request.getParameter("mahoa");
                if (maHoaXoa != null) {
                    int maHoa = Integer.parseInt(maHoaXoa);
                    if (hoaDao.Delete(maHoa)) {
                        request.setAttribute("success", "Xóa sản phẩm thành công.");
                    } else {
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
