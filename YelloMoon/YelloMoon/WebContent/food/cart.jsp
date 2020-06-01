<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>jQueo</title>
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
	<c:if test="${foodlistshop==null}">
	<jsp:forward page="food.do">
		<jsp:param name="op" value="queryList"/>
		<jsp:param name="cc" value="1"/>
	</jsp:forward>
</c:if>    
<title>购物车</title>
<style type="text/css">
		.products{
			list-style:none;
			margin-right:300px;
			padding:0px;
			height:100%;
		}
		.products li{
			display:inline;
			float:left;
			margin:10px;
		}
		.item{
			display:block;
			text-decoration:none;
		}
		.item img{
			border:1px solid #333;
		}
		.item p{
			margin:0;
			font-weight:bold;
			text-align:center;
			color:#c3c3c3;
		}
		.cart{
			position:fixed;
			right:0;
			top:0;
			width:300px;
			height:100%;
			background:#ccc;
			padding:0px 10px;
		}
		h1{
			text-align:center;
			color:#555;
		}
		h2{
			position:absolute;
			font-size:16px;
			left:10px;
			bottom:20px;
			color:#555;
		}
		.total{
			margin:0;
			text-align:right;
			padding-right:20px;
		}
	</style>
	<script>
	function addcart(id,num){
		//使用ajax来添加到购物车
		var param={op:"addcart",
					foodid:parseInt(id.split('$')[1]),
					num:num
					}//javaScript 对象定义
			$.get("cart.do",param,function(data){
				alert("添加成功");
				//刷新表格
				 $('#cartcontent').datagrid('reload');
				
			});
	}
		var data = {"total":0,"rows":[]};//先设计一个对象数组
		var totalCost = 0;
		
		$(function(){
			//单行选择
			$('#cartcontent').datagrid({
				singleSelect:true
			});
			$('.item').draggable({
				revert:true,
				proxy:'clone',//克隆
				//开始触发
				onStartDrag:function(){
					$(this).draggable('options').cursor = 'not-allowed';
					$(this).draggable('proxy').css('z-index',10);
				},
				//终止触发
				onStopDrag:function(){
					$(this).draggable('options').cursor='move';
				}
			});
			$('.cart').droppable({
				onDragEnter:function(e,source){
					$(source).draggable('options').cursor='auto';
				},
				onDragLeave:function(e,source){
					$(source).draggable('options').cursor='not-allowed';
				},
				onDrop:function(e,source){
					var id = $(source).find('p:eq(2)').html();
					var foodname = $(source).find('p:eq(0)').html();
					var newprice = $(source).find('p:eq(1)').html();
					addcart(id,1);
					addProduct(foodname, parseFloat(newprice.split('$')[1]),parseInt(id.split('$')[1]));
				}
			});
		});
		//将要加入的产品价格和主键 都传进来其实在通过循环来进行比较 所有的数据应该都存储在data里面
		function addProduct(foodname,newprice,id){
			function add(){
				for(var i=0; i<data.total; i++){
					var row = data.rows[i];
					if (row.foodname == foodname){
						row.num += 1;
						//addcart(row.id,row.num);
						return;
					}
				}
				data.total += 1;
				//这里是入栈的操作
				data.rows.push({
					foodname:foodname,
					id:id,
					num:1,
					newprice:newprice
				});
			}
			add();
			totalCost += newprice;
			$('#cartcontent').datagrid('loadData', data);
			$('div.cart .total').html('Total: $'+totalCost);
		}
		function del(){
			var row=$("#cartcontent").datagrid('getSelected');
			alert(row.foodname);
		}
	</script>

</head>
<body>
<body style="margin:0;padding:0;height:100%;background:#fafafa;">
	<ul class="products">
	<c:forEach var="f" items="${foodlistshop}">
		<li>
			<a href="fooddetil.do?op=detail&id=${f.id}&userid=${LoginUser.id}" class="item">
				<img src="${f.pic}"/>
				<div>
					<p>${f.foodname}</p>
					<p>Price:$${f.newprice }</p>
					<p>商品id:$${f.id}</p>
				</div>
			</a>
		</li>
</c:forEach>
	</ul>
	<div class="cart">
		<h1>购物车</h1>
		<div style="background:#fff">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"onclick="add()">新增</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"onclick="mod()">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"onclick="del()">删除</a>
			<a href="order.do?op=order&userid=${LoginUser.id}" class="easyui-linkbutton" iconCls="icon-save" plain="true">下单</a>
		</div>
		<table id="cartcontent" fitColumns="true" style="width:300px;height:500px;"
			data-options="rownumbers:true,
			singleSelect:true,
			url:'cart.do?op=kjcart',
			method:'get',
			toolbar:'#tb'
			">
			<thead>
				<tr>
					<th field="id" width=140>商品id</th>
					<th field="foodname" width=140>商品名</th>
					<th field="num" width=60 align="right">数量</th>
					<th field="newprice" width=60 align="right">单价</th>
				</tr>
			</thead>
		</table>

		</div>
		<p class="total">总价: $0</p>
		<h2>Drop here to add to cart</h2>
	</div>
</body>
</html>