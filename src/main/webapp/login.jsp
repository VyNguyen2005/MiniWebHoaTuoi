<%-- 
    Document   : login
    Created on : Oct 31, 2024, 3:49:49 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="LoginServlet" method="post">
            <h2>Login System</h2>
            <div>
                <label>Username</label>
                <input type="text" name="username" class="form-control" required/>
            </div>
            <div>
                <label>Password</label>
                <input type="text" name="password" class="form-control" required/>
            </div>
            <div class="mt-2">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
            <%
               if(request.getAttribute("error") != null){
            %>
            <div class="text-danger">
                <%= request.getAttribute("error")%>
            </div>
            <%
                }%>
        </form>
    </body>
</html>
