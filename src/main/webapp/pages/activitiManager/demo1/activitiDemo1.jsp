<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/pages/include_resource.jsp"/>
<script type="text/javascript">
var basePath = '${pageContext.request.contextPath}';
</script>
<script src="pages/activitiManager/demo1/activitiDemo1.js" type="text/javascript" ></script>
<title>activitDemo1</title>
</script>
</head>
<body>
<table id="actReModelGrid"></table>
<div id="addActReModelDialog" align="center">
	<input id="id" class="easyui-textbox" data-options="label:'ID_:'" style="width:200px; display: none;"></br>
	<input id="key" class="easyui-textbox" data-options="label:'KEY_:'" style="width:200px"></br>
	<input id="name" class="easyui-textbox" data-options="label:'NAME_:'" style="width:200px"></br>
	<input id="description" class="easyui-textbox" data-options="label:'DESCRIPTION_:'" style="width:200px"></br>
</div>
</body>
</html>