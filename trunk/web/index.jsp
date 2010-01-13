<%@ page import="java.net.URLEncoder" %>
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
    <%--<meta http-equiv="refresh" content="0;url=populateMainForm.action">--%>
    <link type="text/css" href="/css/style.css" rel="stylesheet"/>
</head>
<body>
<div class="header">
    Web Graph
    <%--<s:a theme="ajax" notifyTopics="/refresh">Refresh</s:a>--%>
</div>
<table class="maintable">
    <tr>
        <td>
            <div>

                <%--<s:div id="persons" theme="ajax" href="%{descrsUrl}" loadingText="Loading..." listenTopics="/refresh"/>--%>
            </div>

            <br/>

            <div class="formZone">
                <div class="formTitle">Graph Data</div>
                <div class="formbody">
                <s:form action="loadgraph">
                    <%--<input type="text" id="formuleExp0" value="x^2"/>--%>
                    <%--<input type="text" id="formuleExp1" value="x^2+1"/>--%>
                    <%--<s:textfield id="id" name="person.id" cssStyle="display:none"/>--%>
                    <s:textfield id="formuleExp0" label="Expression 1" name="formuleExp0"/>
                    <s:textfield id="formuleExp1" label="Expression 2" name="formuleExp1"/>
                    <s:iterator value="formuleExps" status="fes">
                        <input type="text" id="formuleExp<s:property value="#fes.index" />" value="x^2"/>
                    </s:iterator>
                    <s:textfield id="minX" label="MinX" name="minX"/>
                    <s:textfield id="maxX" label="MaxX" name="maxX"/>
                    <s:textfield id="width" label="Width" name="width"/>
                    <s:textfield id="height" label="Height" name="height"/>
                    <s:submit/>
                </s:form>
                    </div>
            </div>
            <div class="formZone">
                <div class="formTitle">Embeded Code</div>
                <div class="formbody">
                <input class="embededcode" type="text" name="embededCode"
                       value='<img src="<s:property value="graphParas" />" alt="graph"/>'/>
                    </div>
            </div>
        </td>
        <td>
            <%--Graph--%>

            <img src="<s:property value="graphParas" />" alt="graph"/>
        </td>
    </tr>
</table>

</body>
</html>
