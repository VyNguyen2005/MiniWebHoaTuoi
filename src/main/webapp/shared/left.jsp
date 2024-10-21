<%-- 
    Document   : Left
    Created on : Sep 28, 2023, 12:08:09 PM
    Author     : KHOACNTT
--%>

<%@page import="model.Loai"%>
<%@page import="dao.LoaiDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Hoa"%>
<%@page import="dao.HoaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="card mb-3">
    <%
        LoaiDAO loaiDao = new LoaiDAO();
        ArrayList<Loai> dsLoai = loaiDao.getAll();
    %>
    <h3 class="card-header">Category</h3>  
    <ul class="list-group list-group-flush">
        <%
            for (Loai x : dsLoai) {

        %>
        <li class="list-group-item"><a class="text-decoration-none" href="product.jsp?maloai=<%=x.getMaloai()%>"><%=x.getTenloai()%></a></li>
            <%
                }%>
    </ul>   

</div>
