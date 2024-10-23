<%-- 
    Document   : add-product
    Created on : Oct 22, 2024, 2:48:12 PM
    Author     : ADMIN
--%>

<%@page import="model.Loai"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../shared/header.jsp"/>
<jsp:include page="../shared/nav.jsp"/>

<div class="container">
    <h2 class="mt-2">Thêm sản phẩm (hoa)</h2>

    <form method="post" class="form-group" action="ProductManagement?action=insert" enctype="multipart/form-data">
        <div class="mb-2">
            <label>Tên hoa</label>
            <input type="text" name="tenhoa" class="form-control" autofocus autocomplete="off"/>
        </div>
        <div class="mb-2">
            <label>Giá</label>
            <input type="text" name="gia" class="form-control" autocomplete="off"/>
        </div>
        <div class="mb-2">
            <label>Hình ảnh</label>
            <input type="file" name="hinhanh" class="form-control"/>
        </div>
        <div class="mb-2">
            <label>Thể loại</label>
            <select name="theloai" class="form-control">
                <option value="">==Chọn thể loại==</option>
                <%
                    ArrayList<Loai> dsLoai = (ArrayList<Loai>) request.getAttribute("dsLoai");
                    if (dsLoai != null) {
                        for (Loai loai : dsLoai) {

                %>
                <option value="<%= loai.getMaloai()%>"><%= loai.getTenloai()%></option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form>
</div>

<jsp:include page="../shared/footer.jsp"/>
