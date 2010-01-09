<%--
  Created by IntelliJ IDEA.
  User: thuan
  Date: 22 déc. 2009
  Time: 20:05:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
<head>

</head>
<body>

<table>
    <tr>
        <td>
            <div style="width: 300px;border-style: solid">
                <div style="text-align: right;">
                    <%--<s:a theme="ajax" notifyTopics="/refresh">Refresh</s:a>--%>
                </div>
                <%--<s:div id="persons" theme="ajax" href="%{descrsUrl}" loadingText="Loading..." listenTopics="/refresh"/>--%>
            </div>

            <br/>

            <div style="width: 300px;border-style: solid">
                <p>Person Data</p>
                <s:form action="save" validate="true">
                    <s:textfield id="id" name="person.id" cssStyle="display:none"/>
                    <s:textfield id="firstName" label="First Name" name="person.firstName"/>
                    <s:textfield id="lastName" label="Last Name" name="person.lastName"/>
                    <s:submit/>
                </s:form>
            </div>
            <div>

            </div>
        </td>
        <td>
            <%--Graph--%>
            <img src="/displaygraph" alt="graph"/>
        </td>
    </tr>
</table>

</body>
</html>
