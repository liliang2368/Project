<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品管理</title>
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
			$.post("music.do",{
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
	
	function query(){
		$('#dg').datagrid('load',{
			name:$("#cname").val(),
			info:$("#cinfo").val(),
			minPrice:$("#cMinPrice").val(),
			maxPrice:$("#cMaxPrice").val(),
			type:$("#type").val()
		});
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
	function fmtPic(value ,row ,index){
		//value ---image/5000.jpg
		//row   一行的数据以json的格式
		//index  行号
	    return '<div style="width=100px;word-break:break-all; word-wrap:break-word;white-space:pre-wrap;">'+value+'</div>';

	}

</script>
<table class="easyui-datagrid" style="width:400px;height:250px"   id="dg"   
        data-options="url:'music.do?op=query',
        fitColumns:true,
        singleSelect:true,
        pagination:true,
        pageSize:8,
        pageList:[2,5,8],
        fit:true,
        toolbar:'#tb'">   
    <thead>   
        <tr>   
            <th data-options="field:'artist',width:100">演唱者</th> 
            <th data-options="field:'name',width:100">词曲</th>   
            <th data-options="field:'album',width:100">专辑</th>
             <th data-options="field:'music_url',width:100">歌曲链接</th>
             <th data-options="field:'lrc',width:100,formatter:fmtPic">歌词</th>

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
			<label>演唱者</label><input name="artise"><br>
			<label>词曲</label><input name="name"><br>
			<label>专辑：</label><input name="album"><br>
			<label>歌曲链接：</label><input name="music_url"><br>
	</div>


	
		<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"onclick="add()">新增歌曲</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"onclick="mod()">修改歌曲</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"onclick="del()">删除歌曲</a>
		</div>

	</div>
</body>
</html>