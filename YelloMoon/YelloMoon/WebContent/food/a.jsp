<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>超链接管理页面</title>
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
	$('#ff').form('submit', {      
	    onSubmit: function(){    
	   
	    },    
	    success:function(data){
	    //	alert(data);

	    		 //关闭窗口
	    		 $('#dlg').dialog('close');
	    		 //刷新页面
	    		 $('#dg').datagrid('reload');
	    	
	    }    
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
        data-options="url:'a.do?op=query',
        fitColumns:true,
        singleSelect:true,
        pagination:true,
        pageSize:8,
        pageList:[2,5,8],
        fit:true,
        toolbar:'#tb'">   
    <thead>   
        <tr>   
            <th data-options="field:'id',width:100">id</th> 
            <th data-options="field:'href',width:100">网址</th>   
            <th data-options="field:'pic',width:100,formatter:fmtPic">超链接图</th>

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
		<form action="a.do" id="ff">
			<input type="hidden" name="op" value="add">
			<input type="hidden" name="id">
			<label>超链接：</label><input name="href"><br>
			<label>导航图：</label><input name="pic" ><br>
		</form>
	</div>
	
		<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"onclick="add()">新增</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"onclick="mod()">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"onclick="del()">删除</a>
		</div>
	</div>
</body>
</html>