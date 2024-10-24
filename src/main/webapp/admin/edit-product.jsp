<%-- 
    Document   : edit-product
    Created on : Oct 22, 2024, 2:48:22 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../shared/header.jsp"/>
<jsp:include page="../shared/nav.jsp"/>

<div class="container">
    <h2 class="mt-2">Sửa sản phẩm (hoa)</h2>

    <form method="post" class="form-group">
        <div class="mb-2">
            <label>Tên hoa</label>
            <input type="text" name="tenhoa" class="form-control"/>
        </div>
        <div class="mb-2">
            <label>Giá</label>
            <input type="text" name="gia" class="form-control"/>
        </div>
        <div class="mb-2">
            <label>Hình ảnh</label>
            <input type="file" name="hinhanh" class="form-control"/>
        </div>
        <div class="mb-2">
            <label>Thể loại</label>
            <select name="theloai" class="form-control">
                <option value=""></option>
            </select>
        </div>
        <div>
            <button class="btn btn-primary">Edit</button>
        </div>
    </form>
</div>

<jsp:include page="../shared/footer.jsp"/>
