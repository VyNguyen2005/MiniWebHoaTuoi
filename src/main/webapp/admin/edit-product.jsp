<%-- 
    Document   : edit-product
    Created on : Oct 22, 2024, 2:48:22 PM
    Author     : ADMIN
--%>

<%@page import="model.Loai"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Hoa"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../shared/header.jsp"/>
<jsp:include page="../shared/nav.jsp"/>

<div class="container">
    <%
        Hoa hoa = (Hoa)request.getAttribute("hoa");
        ArrayList<Loai> dsLoai = (ArrayList<Loai>) request.getAttribute("dsLoai");
    %>
    <h2 class="mt-2">Sửa sản phẩm (hoa)</h2>

    <form method="post" class="form-group" enctype="multipart/form-data">
        <div class="mb-2">
            <label>Tên hoa</label>
            <input type="text" name="tenhoa" class="form-control" value="<%= hoa.getTenhoa()%>" required/>
        </div>
        <div class="mb-2">
            <label>Giá</label>
            <input type="text" name="gia" class="form-control" value="<%= hoa.getGia()%>" required/>
        </div>
        <div class="mb-2">
            <label>Hình ảnh</label>
            <input type="file" name="hinhanh" value="" class="form-control"/>
            <img src="assets/images/products/<%=hoa.getHinh()%>" alt="alt" width="150px"/>
            <input type="hidden" name="oldImg" value="<%= hoa.getHinh()%>"/>
        </div>
        <div class="mb-2">
            <label>Thể loại</label>
            <select name="theloai" class="form-control">
                <option value="">==Chọn thể loại==</option>
                <%
                   for (Loai loai : dsLoai) {

                %>
                <option value="<%= loai.getMaloai()%>" <%= hoa.getMaloai() == loai.getMaloai() ? "selected" : ""%>><%= loai.getTenloai()%></option>
                <%
                    }%>
            </select>
        </div>
        <div>
            <button class="btn btn-primary">Edit</button>
        </div>
    </form>
</div>

<jsp:include page="../shared/footer.jsp"/>
