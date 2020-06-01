<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    
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
		function update(){
			//重置表单
			$('#ffsum').form('reset');
			$("#dlgsum").dialog('open');
		}
		function save(){
			$.post("order.do",{
			
				
			},function(data){
				alert(data);
				if(data.indexOf('成功')>0){
					//关闭窗口
			   	 $('#dlg').dialog('close');
			    	 //刷新页面
			     $('#dg').datagrid('reload');
				}
			}); 
		}
		function query(){
			$('#dg').datagrid('load',{
				id:$("#id").val(),
				name:$("#name").val(),
				comment:$("#comment").val(),
				addrid:$("#jtaddr").val()
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
<h2>订单详情</h2>
	<div style="margin:20px 0;"></div>
	<table id="dg" class="easyui-datagrid" title="DataGrid Complex Toolbar" style="width:700px;height:250px"
			data-options="rownumbers:true,
			singleSelect:true,
			url:'order.do?op=orderquery&userid=${LoginUser.id}&id=${order.id}',
			method:'get',
			toolbar:'#tb',
			fit:true
			" >
		<thead>
			<tr>
				<th data-options="field:'foodname',width:80,align:'right'">商品名</th>
				<th data-options="field:'addrid',width:150,align:'center'">默认收货地址</th>
				<th data-options="field:'pic',width:100,align:'right',formatter:fmtPic">图片</th>
				<th data-options="field:'newprice',width:80,align:'right'">价格</th>
				<th data-options="field:'count',width:80,align:'right'">购买数量</th>
				<th data-options="field:'comment',width:80">备注</th>
				<th data-options="field:'status',width:60,align:'center'">订单状态</th>
				<th data-options="field:'phone',width:100">电话</th>
				<th data-options="field:'cratetime',width:100">创建订单时间</th>
			</tr>
		</thead>

	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="add()" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		<div>
			<input type="hidden" name="id" value="${order.id}">
			收件人: <input class="easyui-textbox"  id="name" style="width:80px" value="${order.name}">
			默认收货地址: <input class="easyui-textbox" id="addr" style="width:80px" value="${order.addrid}">
			备注: <input class="easyui-textbox" id="comment" style="width:80px" value="${order.comment}">
			选择收获地址: 
			<select class="easyui-combobox" panelHeight="auto" style="width:100px" id="jtaddr">
			<c:forEach var="f" items="${addrlist}">
				<option value="${f.jtaddr}">${f.jtaddr}</option>
			</c:forEach>
			</select>
			<span>总金额</span><font style="color:red">${num}</font>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="query()">付款</a>
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
			<div id="dlgnum" class="easyui-dialog" title="订单编辑" style="width:400px;height:300px;padding:10px" 
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
		<form action="order.do" id="ffnum">
			<input type="hidden" name="op" value="num">
			<input type="hidden" name="id">
			<label>数量：</label><input name="num"><br>
		</form>
	</div>
	</div>
</body>
</html>