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
	function adtail(){
		//先获取到选中的行
		var row=$("#dg").datagrid("getSelected");
		if(row==null){
			alert("请选择需要需改的商品的详情");
			return;
		}
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
	function del(){
		//获取到选中行的row值
		var row=$("#dg").datagrid('getSelected');
		alert(row.id);
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
	//表单提交   普通的ajax提交方式
	function save(){
		//利用点击事件来进行ajax的方式来提交
		$('#ff1').form('submit', {      
		    onSubmit: function(){    
		   
		    },    
		    success:function(data){
		    //	alert(data);

		    		 //关闭窗口
		    		 $('#dlg1').dialog('close');
		    		 //刷新页面
		    		 $('#dg').datagrid('reload');
		    	
		    }    
		});  
	}
	//表单提交   普通的ajax提交方式
	function savefood(){
		//利用点击事件来进行ajax的方式来提交
		$('#ff').form('submit', {      
		    onSubmit: function(){    
		   
		    },    
		    success:function(data){
		    	alert(data);
		    	if(data.indexOf('成功')>0){//添加成功
		    		 //关闭窗口
		    		 $('#dlg').dialog('close');
		    		 //刷新页面
		    		 $('#dg').datagrid('reload');
		    	} 
		    }    
		});  
	}
	function fmtPic(value ,row ,index){
		//value ---image/5000.jpg
		//row   一行的数据以json的格式
		//index  行号
		return "<img src='"+value+"'height='70px'/>"
	}

	
	function query(){
		$('#dg').datagrid('load',{
			name:$("#cname").val(),
			info:$("#cinfo").val(),
			minPrice:$("#cMinPrice").val(),
			maxPrice:$("#cMaxPrice").val(),
			type:$("#type").val()
		});
	}
	function addtype(){
		
	}
	function adtail(){
		//获取选中行的row值  row包括菜品的所有字段值
		var row=$("#dg").datagrid('getSelected');
			if(row==null){
				alert("请选中要修改的菜品");
				return;
			}
		//将表格数据写到表单上
		//先清除表单所有的值
	//	$("#ff1").form("clear");
		$('#ff1').form('load',row);
		//打开窗口
		$("#dlg1").dialog('open');
		}

</script>
<table class="easyui-datagrid" style="width:400px;height:250px"   id="dg"   
        data-options="url:'food.do?op=query',
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
            <th data-options="field:'pic',width:100,formatter:fmtPic">商品图</th>
            <th data-options="field:'head1',width:100,formatter:fmtPic">详情图</th> 
            <th data-options="field:'head2',width:100,formatter:fmtPic">详情图</th> 
            <th data-options="field:'head3',width:100,formatter:fmtPic">详情图</th> 
            <th data-options="field:'head4',width:100,formatter:fmtPic">详情图</th>   
            <th data-options="field:'head',width:100,formatter:fmtPic">详情图</th>
            <th data-options="field:'createtime',width:100">创建时间</th>   
            <th data-options="field:'num',width:100">库存量</th>   
             <th data-options="field:'type',width:100">菜品分类</th>  
        </tr>   
    </thead>   
</table>
<div id="dlg" class="easyui-dialog" title="菜品编辑" style="width:400px;height:300px;padding:10px" 
			data-options="
			modal:true,
				buttons: [{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						savefood();
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
			<label>菜名：</label><input name="foodname"><br>
			<label>描述：</label><input name="dec"><br>
			<label>库存：</label><input name="num"><br>
			<label>价格：</label><input name="oldprice" type="number"><br>
			<label>特价：</label><input name="newprice" type="number"><br>
			<label>商品图：</label><input name="pic"><br>
			<la bel>食品分类：</label>
			<select name="type">
				<c:forEach var="f" items="${typelist}">
				<option value="${f.type }">${f.type}</option>	
				</c:forEach>

			</select>
		</form>
	</div>
	<div id="dlg2" class="easyui-dialog" title="菜品编辑" style="width:400px;height:300px;padding:10px" 
			data-options="
			modal:true,
				buttons: [{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						addtype();
					}
				},{
					text:'取消',
					handler:function(){
						$('#dlg2').dialog('close')
					}
				}],
				closed:true
			">
		<form action="type.do" id="ff2">
			<input type="hidden" name="op" value="save">
			<input type="hidden" name="id">
			<label>菜名：</label><input name="foodname"><br>
			<label>描述：</label><input name="dec"><br>
			<label>库存：</label><input name="num"><br>
			<label>价格：</label><input name="oldprice" type="number"><br>
			<label>特价：</label><input name="newprice" type="number"><br>
			<label>商品图：</label><input name="pic"><br>
			<label>食品分类：</label>
			<select name="type">
				<c:forEach var="f" items="${typelist}">
				<option value="${f.type }">${f.type}</option>	
				</c:forEach>
			</select>
		</form>
	</div>
	<div id="dlg1" class="easyui-dialog" title="菜品详情页面编辑" style="width:400px;height:500px;padding:10px"
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
						$('#dlg1').dialog('close')
					}
				}],
				closed:true
			">
		<form action="fooddetil.do" id="ff1">
		<input type="hidden" name="op" value="save">
			<input type="hidden" name="foodid">
			<label>商品名：</label><img alt="" src="" style="width:20px;high=20px;"><input name="foodname"><br>
			<label>图片1：</label><input type="text"    name="head"><br>
			<label>图片2：</label><input type="text"  name="head1"><br>
			<label>图片3：</label><input type="text" name="head2" ><br>
			<label>图片4：</label><input type="text" name="head3" ><br>
			<label>图片5：</label><input  type="text" name="head4"><br>
			<label>商品介绍：</label><input class="easyui-textbox" data-options="multiline:true" name="info" style="width:300px;height:100px">
		</form>
	</div>
	
	
		<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="mod()">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="del()">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="adtail()">新增商品详情</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addtype()">新增商品分类</a>
		</div>

	</div>
</body>
</html>