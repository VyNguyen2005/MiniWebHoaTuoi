<%-- 
    Document   : changepassword
    Created on : Nov 6, 2024, 10:30:46 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--nhung noi dung header.jsp-->
<jsp:include page="shared/header.jsp" />
<!--nhung noi dung nav.jsp-->
<jsp:include page="shared/nav.jsp" />
<div class="container">
    <form action="ChangePasswordServlet" method="post">
        <h1>Change your password</h1>
        <div>
            <label>Old password</label>
            <input type="password" name="oldpassword" required class="form-control" autofocus autocomplete="off"/>
        </div>
        <div>
            <label>New password</label>
            <input type="password" name="newpassword" required class="form-control"/>
        </div>
        <div>
            <label>Confirm password</label>
            <input type="password" name="confirmpassword" required class="form-control"/>
        </div>
        <div class="mt-2">
            <button type="submit" class="btn btn-primary">Change</button>
        </div>
        <%
            if (request.getAttribute("error") != null) {
        %>
        <div class="text-danger">
            <%=request.getAttribute("error")%>
        </div>
        <% 
            }%>
    </form>
</div>
<!--nhung noi dung footer.jsp-->
<jsp:include page="shared/footer.jsp" />  
