<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品管理</title>
<c:if test="${typelist==null}">
	<jsp:forward page="type.do">
		<jsp:param name="op" value="query"/>
	</jsp:forward>
</c:if> 

<!-- easyui 导入资源   -->
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">   
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>   
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script> 

</head>
<body>
<script type="text/javascript">
	function add(){
		//重置表单
		$('#ff').form('reset');
		$("#dlg").dialog('open');
	}
	function mod(){
		//获取选中行的row值  row包括菜品的所有字段值
		var row=$("#dg").datagrid('getSelected');
			if(row==null){
				alert("请选中要修改的菜品");
				return;
			}
		//将表格数据写到表单上
		$('#ff').form('load',row);
		//打开窗口
		$("#dlg").dialog('open');
	}
	function addcart(){
		//获取当前行的数据
		var row=$("#dg").datagrid('getSelected');
		if(row==null){
			alert("请选中要加入购物车的菜品");
			return;
		}
		var param={op:"cart",
				userid:$("#userid").val(),
				id:row.id//food
				}//javaScript 对象定义
		$.get("cart.do",param,function(data){
			var row=$("#dd").html();
			alert("添加成功");
		});
	}
	function del(){
		//获取到选中行的row值
		var row=$("#dg").datagrid('getSelected');
		if(row==null){
			alert("请选中要删除的菜品");
			return;
		}
		if(confirm("确定要删除次菜品？")){
			//jquery ajax的升级版post方法
			$.post("food.do",{
				id:row.id,
				op:'remove',
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
	}
	function detail(){
		var row=$("#dg").datagrid('getSelected');
		if(row==null){
			alert("请选中要查看的菜品");
			return;
		}
			//jquery ajax的升级版post方法
			$.post("fooddetil.do",{
				id:row.id,
				op:'detail',
				userid:$("#userid").val()
			},function(data){
				 window.location.href="single-product.jsp";
			});
	}
	function fmtPic(value ,row ,index){
		//value ---image/5000.jpg
		//row   一行的数据以json的格式
		//index  行号
		return "<img src='"+value+"'height='70px'/>"
	}

</script>
<table class="easyui-datagrid" style="width:400px;height:250px"   id="dg"   
        data-options="url:'food.do?op=query&foodname=${name}',
        fitColumns:true,
        singleSelect:true,
        pagination:true,
        pageSize:8,
        pageList:[2,5,8],
        fit:true,
        toolbar:'#tb'">   
    <thead>   
        <tr>   
            <th data-options="field:'foodname',width:100">菜名</th>  
            <th data-options="field:'dec',width:100">描述</th>    
            <th data-options="field:'oldprice',width:100">价格</th>
            <th data-options="field:'newprice',width:100">特价</th>     
            <th data-options="field:'pic',width:100,formatter:fmtPic">图片</th>    
            <th data-options="field:'createtime',width:100">创建时间</th>   
            <th data-options="field:'num',width:100">库存量</th>   
             <th data-options="field:'type',width:100">菜品分类</th>  
        </tr>   
    </thead>   
</table>	
		<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
		<input type="hidden" name="userid" value="${LoginUser.id}" id="userid">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"onclick="addcart()">加入购物车</a>
			
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="detail()">商品详情页</a>
	
			
		</div>
		<div>
		</div>
	</div>
</body>
</html>