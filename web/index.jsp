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
                <s:form action="loadgraph" >
                    <%--<s:textfield id="id" name="person.id" cssStyle="display:none"/>--%>
                    <s:textfield id="formuleExp" label="Expression" name="formuleExp"  />
                    <s:textfield id="minX" label="MinX" name="minX" />
                    <s:textfield id="maxX" label="MaxX" name="maxX"/>
                    <s:textfield id="width" label="Width" name="width"/>
                    <s:textfield id="height" label="Height" name="height"/>
                    <s:submit/>
                </s:form>
            </div>
            <div><p>Embeded Code</p>
                
                
                 <input type="text" name="embededCode" value='<img src="<s:property value="graphParas" />" alt="graph"/>'  />
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
