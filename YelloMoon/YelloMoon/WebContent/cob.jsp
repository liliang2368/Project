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
<title>Insert title here</title>
</head>
<body>
<body class="easyui-layout">

    
	<div data-options="region:'north',split:true" style="height:100px;">
		<div class="header">
			<p>YC信息网上订餐系统后台</p>
			<ul>
				<li><a class="easyui-linkbutton " data-options="plain:true" href="javascript:void(0)">修改密码</a></li>
				<li><a class="easyui-linkbutton " data-options="plain:true" href="javascript:void(0)">退出系统</a></li>
				
			</ul>
		</div>
	</div>
    <div data-options="region:'south',split:true" style="height:60px;"></div>
    <div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>
    <div data-options="region:'west',title:'导航',split:true" style="width:200px;">
    	<div id="menu" class="easyui-accordion" style="overflow:auto:padding:10px;" data-options="fit:true">
    		<div title="菜品管理" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">
	    		<ul>
	    			<li>
	    			<a class="easyui-linkbutton a_button" data-options="iconCls:'icon-add',plain:true" href="index.jsp">添加菜品</a>
	    			</li>
	    			<li>
	    			<a class="easyui-linkbutton a_button" data-options="iconCls:'icon-search',plain:true" href="food.jsp">查看菜品</a>
	    			</li>
	    			<li>
	    			<a class="easyui-linkbutton a_button" data-options="iconCls:'icon-edit',plain:true" href="javascript:void(0)">修改菜品</a>
	    			</li>
	    		</ul>
    		</div>
	    	<div title="用户管理" style="padding:10px;">
	    		<ul>
	    			<li><a class="easyui-linkbutton a_button" data-options="iconCls:'icon-add',plain:true" href="javascript:void(0)">添加用户</a></li>
	    			<li><a class="easyui-linkbutton a_button" data-options="iconCls:'icon-search',plain:true" href="javascript:void(0)">查看用户</a></li>
	    			<li><a class="easyui-linkbutton a_button" data-options="iconCls:'icon-edit',plain:true" href="javascript:void(0)">修改用户</a></li>
	    		</ul>
	    	</div>
	
	    	<div title="订单管理" >
	    		<ul>
	    			<li><a class="easyui-linkbutton a_button" data-options="iconCls:'icon-search',plain:true" href="order.html">处理订单</a></li>
	    			<li><a class="easyui-linkbutton a_button" data-options="iconCls:'icon-edit',plain:true" href="findorder.html">查看订单</a></li>
	    		</ul>
	    	</div>
    	
    		<div title="系统管理" >
	    		<ul>
	    			<li><a class="easyui-linkbutton a_button" data-options="iconCls:'icon-search',plain:true" href="javascript:void(0)">分配权限</a></li>
	    			<li><a class="easyui-linkbutton a_button" data-options="iconCls:'icon-search',plain:true" href="javascript:void(0)">备份数据</a></li>
	    		</ul>
	    	</div>
	    	
	    	<div title="统计管理" >
		    		<ul>
		    			<li><a class="easyui-linkbutton a_button" data-options="iconCls:'icon-search',plain:true" href="javascript:void(0)">查看报表</a></li>
		    		</ul>
	    	</div>
	    	
    	</div>
    	
    </div>
    	
    

    <div data-options="region:'center',split:true" >
    	<div id="content" class="easyui-tabs">
    		<div title="欢迎"></div>
    	</div>
    	
    </div>
    

	<!-- 选项卡 -->
    <script type="text/javascript">
    	$(function(){
    		$('#menu .a_button').click(function(){
    			var href=$(this).attr('href');
    			var value=$(this).text();
    			//显示在center中以选项卡的形式显示，如果显示了，重新点击不会新建定位到选项卡
    			//如果该选项卡存在
    			if( $("#content").tabs("exists",value) ){
    				$("#content").tabs("select",value);		
    			}else{
    				$("#content").tabs("add",{
    					title:value,
    					href:href,
    					closable:true
    				});
    			}
    			return false;
    		});
    	});
    </script>
    
</body>
</html>