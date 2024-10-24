<%-- 
    Document   : list-product
    Created on : Oct 22, 2024, 4:21:04 PM
    Author     : ADMIN
--%>

<%@page import="model.Hoa"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../shared/header.jsp"/>
<jsp:include page="../shared/nav.jsp"/>

<% if (request.getAttribute("success") != null) { %>
<script>
    Swal.fire({
        position: "top-center",
        icon: "success",
        title: "<%= request.getAttribute("success") %>",
        showConfirmButton: false,
        timer: 1500
    });
</script>
<%
    }%>
<div class="container">
    <h2 class="py-3">DANH SÁCH SẢN PHẨM</h2>
    <div class="mb-2 text-end">
        <a href="ProductManagement?action=add" class="btn btn-primary m-lg-2"><i class="bi bi-plus-circle"></i>Thêm mới</a>
    </div>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th scope="col">Tên hoa</th>
                <th scope="col">Giá</th>
                <th scope="col">Hình ảnh</th>
                <th scope="col">Loại</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                ArrayList<Hoa> dsHoa = (ArrayList<Hoa>) request.getAttribute("dsHoa");
                if (dsHoa != null) {
                    for (Hoa hoa : dsHoa) {
            %>
            <tr>
                <th scope="row"><%= hoa.getTenhoa() %></th>
                <td><%= hoa.getGia() %> VND</td>
                <td>
                    <img style="width: 100px" src="assets/images/products/<%= hoa.getHinh() %>"/>
                </td>
                <td><%= hoa.getMaloai() %></td>
                <td>
                    <a href="ProductManagement?action=edit&mahoa=<%= hoa.getMahoa() %>" class="btn btn-secondary"><i class="bi bi-pencil-square"></i>Sửa</a>
                    <a href="ProductManagement?action=delete&mahoa=<%= hoa.getMahoa() %>" class="btn btn-danger" onclick="return confirm("Bạn có chắc chắn muốn xoá sản phẩm này?")" ><i class="bi bi-trash"></i>Xóa</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>

<jsp:include page="../shared/footer.jsp"/>
