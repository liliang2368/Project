<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- easyui 导入资源   -->
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">   
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>   
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script> 
<title>管理用户界面</title>
</head>
<body>
<table class="easyui-datagrid" style="width:400px;height:250px"   id="dg"   
        data-options="url:'user.do?op=query',
        fitColumns:true,
        singleSelect:true,
        pagination:true,
        pageSize:8,
        pageList:[2,5,8],
        fit:true,
        toolbar:'#tb'">   
    <thead>   
        <tr>   
            <th data-options="field:'name',width:100">用户名</th> 
            <th data-options="field:'phone',width:100">电话</th>   
            <th data-options="field:'email',width:100">邮件</th>
            <th data-options="field:'createtime',width:100">创建时间</th>     
        </tr>   
    </thead>   
</table>
		<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"onclick="user()">用户</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"onclick="mananger()">管理员</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"onclick="del()">修改权限</a>
		
		</div>

	</div>
	<!-- 刷新页面  -->
	<script type="text/javascript">
	function user(){
		$('#dg').datagrid('load',{
			name:$("#cname").val(),
			info:$("#cinfo").val(),
			minPrice:$("#cMinPrice").val(),
			maxPrice:$("#cMaxPrice").val(),
			type:$("#type").val()
		});
	}
	function mananger(){
		
	}
	
	
	</script>
</body>
</html>