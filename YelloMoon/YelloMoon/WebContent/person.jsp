   <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
<!-- 获取所有的显示地址 -->

<link rel="stylesheet" type="text/css" href="css/person.css" />
<script src="js/jquery.citys.js" type="text/javascript" charset="utf-8"></script>
			
		<script type="text/javascript">
			$(function() {

				$("#mangeraddr").click(function() {

					$("#manger-addr").show('slow');
				});
				$("#close").click(function() {
					$("#manger-addr").hide('slow');
				});
				$("#sub").click(function() {
						alert($("#province option:selected").text())
					})
					//				打开我的收获地址
				$("#center").click(function() {
					if($("#showaddr").css("display") == "block") {
						//						关闭
						$("#showaddr").hide("slow");
					} else {
						$("#showaddr").show("slow");
					}
				})
				$("#showtable tr td:last-child div").click(function() {
					//				如果点击没有class属性
					//  
					if($(this).attr("class") != "nextlast") {
						//先关掉所有的class属性
						$(".nextlast").removeClass('nextlast');
						$(this).attr("class", "nextlast");
					}
				})
				 $("#wite-play").click(function(){
						$('#dg').datagrid('load',{
							status:"待支付"
						});
					
				})
				$("#al-play").click(function(){
					$('#dg').datagrid('load',{
						status:"待收货"
					});
				})
				$("#dai-pay").click(function(){
					$('#dg').datagrid('load',{
						status:"待发货"
					});
				})
				$("#tui").click(function(){
						$('#dg').datagrid('load',{
							status:"退款"
						});
				}) 
			})
		</script>
		<script type="text/javascript">

		</script>

<title>个人主页</title>
</head>
<c:if test="${queryaddr2==null}">
<jsp:forward page="addr.do">
	<jsp:param value="query" name="op"/>
	<jsp:param value="1" name="userid"/>
</jsp:forward>
</c:if>
<body>
<div id="" style="width: 1250px; height: 1250px; color: red; margin: auto;">

			<!--左边-->
			<div class="left">
				<ul>
					<a href="#">
						<li>我的购物车</li>
					</a>
					<a href="#">
						<li>我的购物车</li>
					</a>
					<a href="#">
						<li>我的购物车</li>
					</a>
					<a href="#">
						<li>我的购物车</li>
					</a>
					<a href="#">
						<li>我的购物车</li>
					</a>
					<a href="#">
						<li>我的购物车</li>
					</a>
					<a href="#">
						<li>我的购物车</li>
					</a>
					<a href="#">
						<li>我的购物车</li>
					</a>
				</ul>
			</div>
			<!--右边-->
			<div class="right" style="margin-top: 37px;">

				<!--个人信息的主页面-->

				<div class="head-top">
					<div class="left1">
						<a href="#" class="head"></a>
						<a class="name">
							<em>liyang2368</em> (liyang2368)
						</a>
					</div>
					<div class="center" id="mangeraddr">
						<a>
							管理收货地址
						</a>
					</div>
					<div class="center" id="center">
						<a>
							我的收获地址
						</a>
					</div>
				</div>
				<!--这里应该是管理收获地址-->
				<div class="head-last">
					<div id="wite-play" >
						<a>待付款</a>
					</div>
					<div id="al-play">
						<a>待收货</a>
						</div>
					<div id="dai-pay">
						<a>待发货</a>
					</div>
					<div id="tui">
						<a>退款</a>
					</div>
				</div>
			</div>

			<!--显示收货地址-->
			<div id="showaddr" class="showaddr" style="display: none;">
					<table id="dg2" class="easyui-datagrid" style="width:400px;height:250px"   
        data-options="url:'addr.do?op=query&userid=${LoginUser.id}',
        fitColumns:true,
        singleSelect:true,
        pagination:true,
        pageSize:8,
        pageList:[2,5,8],
        fit:true,
        toolbar:'#tb'">   
    <thead>   
        <tr>   
       		 <th data-options="field:'name',width:100">收货人</th> 
            <th data-options="field:'area',width:100,height:100">所在地区</th>  
             <th data-options="field:'jtaddr',width:100,formatter:fmtPic">详细地址</th>   
            <th data-options="field:'email',width:100">邮编</th>
            <th data-options="field:'phone',width:100">电话</th> 
        </tr>   
    </thead>   
				</table>	
			</div>
			<div style="display: block;width:716px;height:300px;float:left;margin:-56px 1px 1px 1px" id="showgood">
		<div style="float:left">
			<input type="hidden" name="id" value="${order.id}">
			选择订单种类
			<select class="easyui-combobox" panelHeight="auto" style="width:100px" id="jtaddr">
				<option value="待付款">待付款</option>
				<option value="待收货">待收货</option>
				<option value="待发货">待发货</option>
				<option value="退款">退款</option>
			</select>
			
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
		</div>
 			<table id="dg" class="easyui-datagrid" style="width:400px;height:250px"   
        data-options="url:'order.do?op=query&userid=${LoginUser.id}',
        fitColumns:true,
        singleSelect:true,
        pagination:true,
        pageSize:8,
        pageList:[2,5,8],
        fit:true,
        toolbar:'#tb'">   
    <thead>   
        <tr>   
       		 <th data-options="field:'id',width:100">订单号</th> 
            <th data-options="field:'foodname',width:100,height:100">商品名称</th>  
             <th data-options="field:'pic',width:100,formatter:fmtPic">商品图片</th>   
            <th data-options="field:'newprice',width:100">价格</th>
            <th data-options="field:'num',width:100">数量</th> 
              <th data-options="field:'newprice',width:100">实付款</th>
              <th data-options="field:'status',width:100">交易状态</th>    
        </tr>   
    </thead>   
</table>

	<script type="text/javascript">
	function fmtPic(value ,row ,index){
		//value ---image/5000.jpg
		//row   一行的数据以json的格式
		//index  行号
		return "<img src='"+value+"'height='70px'/>"
	}
	function query(){
		$('#dg').datagrid('load',{
			status:$("#jtaddr").val()
		});
	}
	$('#dg').datagrid({
		onLoadSuccess:function(){
			var merges = [{
				index:2,
				rowspan:3
			},{
				index:5,
				rowspan:2
			},{
				index:7,
				rowspan:2
			}];
			for(var i=0; i<merges.length; i++)
				$('#tt').datagrid('mergeCells',{
					index:merges[i].index,
					field:'productid',
					rowspan:merges[i].rowspan
				});
		}
	});
	</script>

			</div>
		</div>

		<!--新增收货地址-->
		<div style="display: none;" class="manger-addr" id="manger-addr">
			<div id="demo" class="citys">
				<p>
					<select name="province" id="province"></select>
					<select name="city" id="city"></select>
					<select name="area" id="area"></select>
				</p>
				<script type="text/javascript">
					$('#demo').citys({
						code: 350206
					});
				</script>
				<div id="" style="margin:3px 0px 1px 3px;">
					<textarea rows="20" cols="20" style="width: 260px;height: 70px;"> </textarea>
				</div>
				<button class="sub" id="sub">提交</button>
				<span id="close">X</span>
			</div>
		</div>
		<!--新增收货地址-->
		<!--待付款订单-->

</body>
</html>