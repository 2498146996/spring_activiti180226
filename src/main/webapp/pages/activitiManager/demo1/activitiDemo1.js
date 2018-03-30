/**
 * @description 初始化加载方法
 */
$(function(){
	initActReModelGrid();
	initAddActReModelDialog();
});
/**
 * 初始化列表actReModelGrid
 */
function initActReModelGrid() {
	//1、初始化列表
	$('#actReModelGrid').datagrid({
		title: 'ACT_RE_MODEL', 
	    url: 'com/zqy/activitiManager/demo1/ctl/ActivitiManagerCTL/qryActReModel', 
	    columns:[[
	        {field:'opration', title:'操作'
	        	, formatter: function(value, row, index) {
	        		return '<a title="修改" class="easyui-tooltip"><img onclick="editBtnClick('+index+')" alt="修改" src="jquery/easyui/themes/icons/pencil.png"/></a>&nbsp;' 
	        			+ '<a title="删除" class="easyui-tooltip"><img onclick="removeBtnClick('+index+')" alt="删除" src="jquery/easyui/themes/icons/edit_remove.png"/></a>&nbsp;'
	        			+ '<a title="设置" class="easyui-tooltip"><img onclick="moreBtnClick('+index+')" alt="设置" src="jquery/easyui/themes/icons/more.png"/></a>';
	        	}
	        }, 
	        {field:'id', title:'ID_'}, 
	        {field:'rev', title:'REV_'}, 
	        {field:'name', title:'NAME_'}, 
	        {field:'key', title:'KEY_'}, 
	        {field:'category', title:'CATEGORY_'}, 
	        {field:'create_time', title:'CREATE_TIME_'}, 
	        {field:'last_update_time', title:'LAST_UPDATE_TIME_'}, 
	        {field:'version', title:'VERSION_'}, 
	        {field:'meta_info', title:'META_INFO_'}, 
	        {field:'deployment_id', title:'DEPLOYMENT_ID_'}, 
	        {field:'editor_source_value_id', title:'EDITOR_SOURCE_VALUE_ID_'}, 
	        {field:'editor_source_extra_value_id', title:'EDITOR_SOURCE_EXTRA_VALUE_ID_'}, 
	        {field:'tenant_id', title:'TENANT_ID_'}
	    ]], 
	    toolbar: [{
	    	text: '新增', 
			iconCls: 'icon-add', 
			handler: addActReModelBtn
		}]
	});
}
function editBtnClick(index) {
	var row = $('#actReModelGrid').datagrid('getRows')[index];
}
function removeBtnClick(index) {
	var row = $('#actReModelGrid').datagrid('getRows')[index];
	$.ajax({
		type: 'post', 
		url: 'com/zqy/activitiManager/demo1/ctl/ActivitiManagerCTL/removeActReModel', 
		data: {modelId: row.id}, 
		success: function(data) {
			$('#actReModelGrid').datagrid('reload');
		}
	});
}
function moreBtnClick(index) {
	var row = $('#actReModelGrid').datagrid('getRows')[index];
	location.href = baseUrl + "/activiti/modeler.html?modelId=" + row.id;
}
/**
 * 初始化列表addActReModelDialog
 */
function initAddActReModelDialog() {
	$('#addActReModelDialog').dialog({
	    title: 'Add ACT_RE_MODEL Dialog', 
	    width: 400, 
	    height: 200, 
	    closable: false, 
	    closed: true, 
	    cache: false, 
	    modal: true, 
	    toolbar:[{
			text:'Save', 
			iconCls:'icon-save', 
			handler:saveActReModelBtn
		}, {
			text:'Close', 
			iconCls:'icon-cancel', 
			handler:function(){
				$('#addActReModelDialog').dialog('close');
			}
		}]
	});
}
/**
 * 新增model按钮
 */
function addActReModelBtn() {
	$('#addActReModelDialog').dialog('open');
}
function saveActReModelBtn() {
	var name = $("#name").textbox('getValue');
	$.ajax({
		type: 'post', 
		url: 'com/zqy/activitiManager/demo1/ctl/ActivitiManagerCTL/saveActReModel', 
		data: {name: name}, 
		success: function(data) {
			$('#actReModelGrid').datagrid('reload');
		}
	});
	$('#addActReModelDialog').dialog('close');
}