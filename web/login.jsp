<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: thuan
  Date: 22 déc. 2009
  Time: 20:33:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login</title></head>
<body>

<s:form action="login">
    <s:textfield id="userName" label="User Name" name="userName"/>
    <s:textfield id="password" label="Password" name="password"/>    
    <s:submit/>
</s:form>

</body>
</html>