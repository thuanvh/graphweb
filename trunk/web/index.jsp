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
<%--<%@ taglib uri="/tags/struts-html" prefix="html" %>--%>
<html>
<head>
    <%--<meta http-equiv="refresh" content="0;url=populateMainForm.action">--%>
    <link type="text/css" href="/css/style.css" rel="stylesheet"/>
    <link type="text/css" href="/js/colorpicker/css/colorpicker.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.4.min.js"></script>
    <script type="text/javascript" src="/js/colorpicker/js/colorpicker.js"></script>
</head>
<body>
<div class="header">
    Web Graph
</div>
<s:property value="message"/>
<table class="maintable">
    <tr>
        <td>
            
            <div class="formZone">
                <div class="formTitle">Graph Data</div>
                <div class="formbody">
                    <s:form action="loadgraph" theme="simple">
                        <table>
                            <tr>
                                <td>
                                    <s:label id="lbl0" value="Expression 1"/>
                                </td>
                                <td>
                                    <s:textfield id="formuleExp0" name="formuleExp0"/>

                                </td>
                                <td><s:div id="divformuleColor0" cssClass="colorselect" ></s:div>
                                    <s:hidden id="formuleColor0" name="formuleColor0"></s:hidden></td>
                            </tr>
                            <tr>
                                <td><s:label id="lbl1" value="Expression 2"/></td>
                                <td>
                                    <s:textfield id="formuleExp1" label="Expression 2" name="formuleExp1"/>

                                </td>
                                <td><s:div id="divformuleColor1" cssClass="colorselect" ></s:div>
                                    <s:hidden id="formuleColor1" name="formuleColor1"></s:hidden></td>
                            </tr>
                            <tr>
                                <td><s:label id="lbl2" value="Min X"/></td>
                                <td><s:textfield id="minX" label="MinX" name="minX"/></td>
                            </tr>
                            <tr>
                                <td><s:label id="lbl3" value="Max X"/></td>
                                <td><s:textfield id="maxX" label="MaxX" name="maxX"/></td>
                            </tr>
                            <tr>
                                <td><s:label id="lbl4" value="Width"/></td>
                                <td><s:textfield id="width" label="Width" name="width"/></td>
                            </tr>
                            <tr>
                                <td><s:label id="lbl5" value="Height"/>
                                </td>
                                <td><s:textfield id="height" label="Height" name="height"/>
                                </td>
                            </tr>
                        </table>
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
<script type="text/javascript">
    $('#divformuleColor0').css('backgroundColor', '#<s:property value="formuleColor0"/>');
    $('#divformuleColor0').ColorPicker({
        color: '<s:property value="formuleColor0"/> ',
        onShow: function (colpkr) {
            $(colpkr).fadeIn(500);
            return false;
        },
        onHide: function (colpkr) {
            $(colpkr).fadeOut(500);
            return false;
        },
        onChange: function (hsb, hex, rgb) {
            $('#divformuleColor0').css('backgroundColor', '#' + hex);
            $('#formuleColor0')[0].value = hex;
            //            alert($('#formuleColorHid0')[0].value + ";" + hex);
        }
    });
    $('#divformuleColor1').css('backgroundColor', '#<s:property value="formuleColor1"/>');
    $('#divformuleColor1').ColorPicker({
        color: '<s:property value="formuleColor1"/>',
        onShow: function (colpkr) {
            $(colpkr).fadeIn(500);
            return false;
        },
        onHide: function (colpkr) {
            $(colpkr).fadeOut(500);
            return false;
        },
        onChange: function (hsb, hex, rgb) {
            $('#divformuleColor1').css('backgroundColor', '#' + hex);
            $('#formuleColor1')[0].value = hex;
        }
    });

</script>
</body>
</html>
