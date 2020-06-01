<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单管理</title>
<!-- easyui 导入资源   -->
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">   
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>   
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript">

		function add(){
			//重置表单
			$('#ff').form('reset');
			$("#dlg").dialog('open');
		}
		function query(){
			$('#dg').datagrid('load',{
				name:$("#name").val(),
				jtaddr:$("#addr").val(),
				minprice:$("#minprice").val(),
				maxprice:$("#maxprice").val(),
				status:$("#status").val(),
				id:$("#id").val()
			});
		}
		function fmtPic(value ,row ,index){
			//value ---image/5000.jpg
			//row   一行的数据以json的格式
			//index  行号
			return "<img src='"+value+"'height='70px'/>"
		}
	
</script>
</head>
<body>
<h2>订单管理</h2>
	<div style="margin:20px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="DataGrid Complex Toolbar" style="width:700px;height:250px"
			data-options="rownumbers:true,
			singleSelect:true,
			url:'order.do?op=query',
			method:'get',
			toolbar:'#tb',
			fit:true
			">
		<thead>
			<tr>
				<th data-options="field:'id',width:30">订单号</th>
				<th data-options="field:'jtaddr',width:200">用户地址</th>
				<th data-options="field:'name',width:100">姓名</th>
				<th data-options="field:'foodname',width:80,align:'right'">商品名</th>
				<th data-options="field:'pic',width:80,align:'right',formatter:fmtPic">图片</th>
				<th data-options="field:'newprice',width:80,align:'right'">价格</th>
				<th data-options="field:'count',width:80,align:'right'">购买数量</th>
				<th data-options="field:'comment',width:80">备注</th>
				<th data-options="field:'status',width:60,align:'center'">订单状态</th>
				<th data-options="field:'phone',width:100">电话</th>
				<th data-options="field:'createtime',width:100">创建订单时间</th>
			</tr>
		</thead>

	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add()" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		<div>
			收件人: <input class="easyui-textbox"  id="name" style="width:80px">
			收货地址: <input class="easyui-textbox" id="addr" style="width:80px">
			订单号: <input class="easyui-textbox" id="id" style="width:80px">
			最小价格: <input class="easyui-textbox" id="minprice" style="width:80px">
			最大价格: <input class="easyui-textbox" id="maxprice" style="width:80px">
			订单状态: 
			<select class="easyui-combobox" panelHeight="auto" style="width:100px" id="status">
				<option value="待支付">待支付</option>
				<option value="已支付">已支付</option>
				<option value="已失效">已失效</option>
			</select>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
		</div>
	<div id="dlg" class="easyui-dialog" title="订单编辑" style="width:400px;height:300px;padding:10px" 
			data-options="
			modal:true,
				buttons: [{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						save();
					}
				},{
					text:'取消',
					handler:function(){
						$('#dlg').dialog('close')
					}
				}],
				closed:true
			">
		<form action="food.do" id="ff">
			<input type="hidden" name="op" value="save">
			<input type="hidden" name="id">
			<label>菜品名：</label><input name="foodname"><br>
			<label>描述：</label><input name="info"><br>
			<label>价格：</label><input name="price" type="number"><br>
			<label>特价：</label><input name="realprice" type="number"><br>
			<label>图片：</label><input name="head"><br>
		</form>
		</div>
	</div>
</body>
</html>