<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath()+"/";
%>
	<base href='<%=path %>'>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>jquery/easyui/themes/default/easyui.css" type="text/css" >
	<link rel="stylesheet" href="<%=path%>jquery/easyui/themes/icon.css" type="text/css" >
	<link rel="stylesheet" href="<%=path%>jquery/easyui/themes/color.css" type="text/css" >
	<!--公共js库-->
	<script src="<%=path%>jquery/jquery.min.js" type="text/javascript" ></script>
	<script src="<%=path%>jquery/easyui/jquery.easyui.min.js" type="text/javascript" ></script>
	<script src="<%=path%>jquery/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript" ></script>
	<script src="<%=path%>jquery/easyui/plugins/jquery.messager.js" type="text/javascript" ></script>
	<script type="text/javascript">
	var baseUrl = window.document.location.protocol + "//" + window.document.location.host + "/";
	var shortenedUrl = window.document.location.href.replace(baseUrl, "");
	baseUrl = baseUrl + shortenedUrl.substring(0, shortenedUrl.indexOf("/"));
	</script>
	

	
	