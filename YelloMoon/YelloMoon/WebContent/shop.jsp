<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Gifty Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Custom Theme files -->
<link href="css/style2.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="css/jquery.countdown.css" />
<!-- Custom Theme files -->
<!--webfont-->
<link href='http://fonts.useso.com/css?family=Raleway:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<!-- dropdown -->
<script src="js/jquery.easydropdown.js"></script>
<!-- start menu -->s
<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script src="js/responsiveslides.min.js"></script>
<script>
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,
      	nav: false,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });
    });
</script>
<script src="js/jquery.countdown.js"></script>
<script src="js/script.js"></script>

	<c:if test="${foodlistshop==null}">
	<jsp:forward page="food.do">
		<jsp:param name="op" value="queryList"/>
	</jsp:forward>
</c:if>   
  
	<script type="text/javascript">
	function cart(data){
		var id1=data;
		var param={op:"cart",
				id:id1
				}//javaScript 对象定义
		$.get("cart.do",param,function(data){
			var row=$("#dd").html();
			alert("添加成功");
		});
	}
	</script> 
</head>
<body>
		<!--搜索栏-->
<div class="header_bottom">
	<div class="container">
	 <div class="header_bottom-box">
		<div class="header_bottom_left">
			<div class="logo">
				<a href="index.jsp"><img src="images/logo1.png" alt=""/></a>
			</div>
			<ul class="clock">
				<i class="clock_icon"> </i>
				<li class="clock_desc">Justo 24/h</li>
			</ul>
			<div class="clearfix"> </div>
		</div>
		<div class="header_bottom_right">
			<div class="search">
			  <input type="text" value="Your email address" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Your email address';}">
			  <input type="submit" value="">
	  		</div>
	  		
	  		<ul class="bag">
	  			<a href="#"><i class="bag_left"> </i></a>
	  			<div class="clearfix"> </div>
	  		</ul>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
</div>
<!--结束搜索栏-->
		<div class="sellers_grid">
			<ul class="sellers">
				<i class="star"> </i>
				<li class="sellers_desc"><a href="index.jsp"><h2>${foodname}</h2></a></li>
				<div class="clearfix"> </div>
			</ul>
		</div>
		
		<!--最新的商品-->
		<div class="grid_2">
		<c:forEach var="f" items="${foodlistshop}">
			<div class="col-md-3 span_6">
			  <div class="box_inner">
				<a href="fooddetil.do?op=detail&id=${f.id}"><img src="${f.pic }" class="img-responsive" alt=""/></a>
				 <div class="sale-box"> </div>
				 <div class="desc">
				 	<h3>${f.foodname}</h3>
				 	<h4>${f.newprice}$</h4>
				 	<ul class="list2">
				 	  <li class="list2_left"><span class="m_1"><a href="#" class="link" onclick="cart(${f.id})">加入购物车</a></span></li>
				 	  <li class="list2_right"><span class="m_2"><a href="#" class="link1">See More</a></span></li>
				 	  <div class="clearfix"> </div>
				 	</ul>
				 	<div class="heart"> </div>
				 </div>
			   </div>
			</div>
			</c:forEach>
				
			<div class="clearfix"> </div>
		</div> 
               <!--
               	作者：offline
               	时间：2019-06-02
               	描述：最新商品的结束
               -->
          	<!--全部商品-->


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>