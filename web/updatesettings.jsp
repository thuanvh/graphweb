<%--
  Created by IntelliJ IDEA.
  User: thuan
  Date: 22 déc. 2009
  Time: 20:34:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>Update Settings</title></head>
<body>Update Settings
<form action="updateSettings.action" method="post">
    <table>
        <tr>
            <td>ration Of Coordination</td>
            <td><input type="text" id="rationOfCoordination" name="rationOfCoordination"
                       value='<s:property value="configuration.getRationOfCoordination()" />'/></td>
        </tr>
        <tr>
            <td>min X</td>
            <td><input type="text" id="minX" name="minX"
                       value='<s:property value="configuration.minX" />'/></td>
        </tr>
        <tr>
            <td>max X</td>
            <td><input type="text" id="maxX" name="maxX"
                       value='<s:property value="configuration.getMaxX()" />'/></td>
        </tr>
        <tr>
            <td>width</td>
            <td><input type="text" id="width" name="width"
                       value='<s:property value="configuration.getWidth()" />'/></td>
        </tr>
        <tr>
            <td>height</td>
            <td><input type="text" id="height" name="height"
                       value='<s:property value="configuration.getHeight()" />'/></td>
        </tr>
        <tr>
            <td>Color of Graph</td>
            <td><input type="text" id="colorOfGraph" name="colorOfGraph"
                       value='<s:property value="configuration.getColorOfGraph()" />'/></td>
        </tr>
        <tr>
            <td>Color of Axis</td>
            <td><input type="text" id="colorOfAxis" name="colorOfAxis"
                       value='<s:property value="configuration.getColorOfAxis()" />'/></td>
        </tr>
        <tr>
            <td><input type="submit" id="submit" value="submit"/></td>
        </tr>
    </table>
</form>
</body>
</html>