<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>主页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

	<!-- Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:400,700' rel='stylesheet' type='text/css'>

	<!-- Css -->
	<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" />
	<link rel="stylesheet" href="css/owl.carousel.css">
	<link rel="stylesheet" href="css/owl.theme.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/style3.css">
	<link rel="stylesheet" href="css/responsive.css">

	<!-- jS -->
	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/jquery.nivo.slider.js" type="text/javascript"></script>
	<script src="js/owl.carousel.min.js" type="text/javascript"></script>
	<script src="js/jquery.nicescroll.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/main.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function(){
			$(".close").css({
				'float':'right',
				'display':'block',
				'width':'20px',
				'height':'17px',
				'board': '1px solid #fff',
				'border-radius':'50%',
				'text-align':'10px',
				'text-height':'25px',
				'margin-left':'16px',
				'color':'red'
			});
			var param={op:"cartquery"
					}//javaScript 对象定义
			$.get("cart.do",param,
				function(data){
				showCartTable(data.arrdata);
				//将数量要写入到指定的地方
				$("#num").html('<i class="fa fa-shopping-cart"></i>购物车('+data.num+')');
			},'json')
			
		})
		function cart(data){
			var id1=data;
			var param={op:"cart",
					id:id1
					}//javaScript 对象定义
			$.get("cart.do",param,
				function(data){
				showCartTable(data.arrdata);
				//将数量要写入到指定的地方
				$("#num").html('<i class="fa fa-shopping-cart"></i>购物车('+data.num+')');
				alert("添加成功");
			},'json');
		}
      	function showCartTable(arr){
    		if(null==arr){
    			alert('购物车为空');
    			return;
    		}
    		var str='';
    		for(var i=0;i<arr.length;i++){
		  		str+='<li class="media">'
			    +'<img class="pull-left" src="'+arr[i].pic+'" alt="">'
			    +'<div class="media-body">'
			    +'<h6>'+arr[i].foodname+''
			    +'<span>$'+arr[i].newprice+'</span> <span id="close" class="close" onclick="del('+arr[i].id+')">X</span>'
			    +'</h6>'
			    +'</div>'
			    +'</li>';
		
    		}
    	    $("#dd").html(str);
    	};
		function del(data){
		//	alert("看这里");
			var id1=data;
			var param={op:"del",
					id:id1
					}//javaScript 对象定义
			$.get("cart.do",param,function(data){
				showCartTable(data.arrdata);
				$("#num").html('<i class="fa fa-shopping-cart"></i>购物车('+data.num+')');
				alert("删除成功");
			},'json');
		}
		function queryfood(){
			$('#ff1').form('submit', {      
			    onSubmit: function(){    
			        // 前端的验证    
			        // return false to prevent submit;    
			        
			    },    
			    success:function(data){
			    	if(data.indexOf('成功')>0){//添加成功

			    	} 
			    }    
			}); 
		}
		function sendemail(){
			//利用ajax来向user页面来传递参数信息
			var param={
					op:"findpwd",
					email:$("#btn").val()
			}
			$.get("user.do",param,function(data){
						alert("发送成功");
					});

		}
		function getpwd(){
			//利用ajax来向user页面来传递参数信息
			var param={
					op:"getpwd",
					vcode1:$("#vcode").val(),
					email:$("#email").val()
			}
			$.get("user.do",param,function(data){
						alert("密码已经通过邮箱发送");
					});

		}
	</script>
	<!--typelistIndex  -->
 	<c:if test="${typelistIndex==null}">
	<jsp:forward page="type.do">
		<jsp:param name="op" value="queryIndex"/>
	</jsp:forward>
</c:if>  
 <c:if test="${foodlist==null}">
	<jsp:forward page="food.do">
		<jsp:param name="op" value="queryfood"/>
		<jsp:param name="limit" value="8"/>

	</jsp:forward>
	</c:if>
	<c:if test="${href==null}">
	<jsp:forward page="a.do?op=query">
		<jsp:param name="op" value="query"/>
		<jsp:param name="cc" value="1"/>
	</jsp:forward>
</c:if>  

<c:if test="${msg!=null }">
	<script type="text/javascript">
	alert('${msg}')
	</script>
</c:if>
 
</head>
<body>
<!-- TOP HEADER Start ================================================== -->
	<section id="top">
		<div class="container">
			<div class="row">
				<div class="col-md-7">
					<p class="contact-action"><i class="fa fa-phone-square"></i>如果有任何的疑问，请拨打<strong>+565 975 658</strong></p>
				</div>
				<div class="col-md-3 clearfix">
					<ul class="login-cart">
					<c:if test="${LoginUser==null}">
						<li>
							<a data-toggle="modal" data-target="#myModal" href="#">
							<i class="fa fa-user"></i>
								登陆
							</a>
						</li>
						</c:if>
						<c:if test="${LoginUser!=null }">
					<li>
							<a  href="user.do?op=logout">
							<i class="fa fa-user"></i>
								退出
							</a>
						</li>
						<li>
							<a  href="cart.do?op=cart">
							<i class="fa fa-user"></i>
								刷新
							</a>
						</li>
						<li>
							<div class="cart dropdown">
						  		<a data-toggle="dropdown" id="num"></a>
					  			<div class="dropdown-menu dropup" style="width:259px">
					  				<span class="caret"></span>
						  			<ul class="media-list" id="dd">

									</ul>
									<a href="order.do?op=order&userid=${LoginUser.id}"><button class="btn btn-primary btn-sm">下单</button></a>
							    </div>
							</div>
						</li>
						</c:if>
					</ul>
				</div>
				<div class="col-md-2">
					<div class="search-box">
					<form action="food.do" id="ff1">
						<div class="input-group">
					    	<input placeholder="请输入关键字进行查询" type="text" class="form-control" name="foodname">
					    	<input type="hidden" name="op" value="queryfoodlist">
					      	<span class="input-group-btn">
					       <button class="btn btn-default" onclick="queryfood()"></button>
					      	</span>
					    </div><!-- /.input-group -->
					    </form>
					</div><!-- /.search-box -->
				</div>
			</div> <!-- End Of /.row -->
		</div>	<!-- End Of /.Container -->
	<!-- MODAL Start
    	================================================== -->

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
		    	<div class="modal-content">
		    		<div class="modal-header">
		        		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        		<h4 class="modal-title" id="myModalLabel">完善身份</h4>
		      		</div>
			      	<div class="modal-body clearfix">

						<form action="#" method="post" id="create-account_form" class="std">
							<fieldset>
								<h3>创建你的身份</h3>
								<div class="form_content clearfix">
									<h4>请输入你的Email来进行创建账户</h4>
									<p class="text">
										<label for="email_create">E-mail address</label>
										<span>
											<input placeholder="E-mail address"  type="text" id="email_create" name="email_create" value="" class="account_input">
					                    </span>
									</p>
									<p class="submit">
										<button class="btn btn-primary">创建账户</button>
									</p>
								</div>
							</fieldset>
						</form>
			      		<form action="user.do" method="post" id="login_form" class="std">
			      		<input type="hidden" name="op" value="login">
							<fieldset>
								<h3>已经注册</h3>
								<div class="form_content clearfix">
									<p class="text">
									<label for="email">E-mail address</label>
										<span><input placeholder="E-mail address" type="text" id="email" name="email" value="" class="account_input"></span>
									</p>
									<p class="text">
									<label for="passwd">Password</label>
										<span><input placeholder="Password" type="password" id="passwd" name="pwd" value="" class="account_input"></span>
									</p>
									<p class="lost_password">
										<a href="#" class="popab-password-link" onclick="f('hide')">忘记密码</a>

										<script type="text/javascript">
											function f(a){
										        var obj = document.getElementById(a);
										        if(obj.style.display==""){
										        	obj.style.display = "none";   
										        }else{
										        	obj.style.display = "";
										        }        
											}  
										</script>
									<p class="submit">
										<input class="btn btn-success"  type="submit">提交
									</p>

								</div>
							</fieldset>
						</form>
						<div style="display:none" id="hide">
											<input id="btn" type="email" name="email" id="email" width="100px"	
											height="60px" style="margin-top: 19px" placeholder="请输入您的邮箱地址">
											<input type="submit" class="btn btn-success"  value="获取验证码" onclick="sendemail()">
											<input type="text" id="vcode" width="100px"
											height="60px" style="margin-top: 19px;margin-left:270px" placeholder="请输入您的验证码" >									
											<input type="submit" style="margin-left:2px" class="btn btn-success"  value="获取密码" onclick="getpwd()">
										</div>
			      	</div>
			      	<div class="modal-footer">
			        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      	</div>
		    	</div>
		  	</div>
		</div>	
	</section>  <!-- End of /Section -->
	
	<!-- LOGO Start
    ================================================== -->
	
	<header>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<a href="#">
						<img src="images/logn.png" alt="logo" style="width:421px;height:100px;">
					</a>
				</div>	<!-- End of /.col-md-12 -->
			</div>	<!-- End of /.row -->
		</div>	<!-- End of /.container -->
	</header> <!-- End of /Header -->
	<!-- MENU Start
    ================================================== -->

	<nav class="navbar navbar-default">
		<div class="container">
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		    </div> <!-- End of /.navbar-header -->

		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      	<ul class="nav navbar-nav nav-main">
		        	<li class="active"><a href="#">主页</a></li>
					<li><a href="food.do?op=query&rows=6&page=1&ff=1">当地特色美食</a></li>
					<li><a href="cart.jsp">快捷购物</a></li>
					<li><a href="index.jsp">去找民宿</a></li>
					<li class="dropdown">
						<a href="#">
							页
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
						   <li><a  href="#">活动</a></li>
						    <li><a  href="#">其他活动</a></li>
						    <li><a  href="#">Something else here</a></li>
						    <li><a  href="#">Separated link</a></li>
						</ul>
					</li> <!-- End of /.dropdown --
		        </ul> <!-- End of /.nav-main -->
		    </div>	<!-- /.navbar-collapse -->
		</div>	<!-- /.container-fluid -->
	</nav>	<!-- End of /.nav -->
	<!-- SLIDER Start
    ================================================== -->

<!--这里是轮播图部分  -->
	<section id="slider-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div id="slider" class="nivoSlider">
				    	<img src="images/spider1.jpg" alt="" />
				    	<img src="images/spider4.jpg" alt=""/>
				    	<img src="images/spider5.jpg" alt="" />
					</div>	<!-- End of /.nivoslider -->
				</div>	<!-- End of /.col-md-12 -->
			</div>	<!-- End of /.row -->
		</div>	<!-- End of /.container -->
	</section> <!-- End of Section -->
	<!-- FEATURES Start
    ================================================== -->

	<section id="features">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="block">
						<div class="media">
							<div class="pull-left" href="#">
						    	<i class="fa fa-ambulance"></i>
						  	</div>
						  	<div class="media-body">
						    	<h4 class="media-heading">免费航运</h4>
						    	<p>将提供最高效的空运服务</p>
						  </div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="media">
							<div class="pull-left" href="#">
								<i class=" fa fa-foursquare"></i>
						  	</div>
						  	<div class="media-body">
						    	<h4 class="media-heading">特色</h4>
						    	<p>打造当地特色文化产品</p>
						  </div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="block">
						<div class="media">
							<div class="pull-left" href="#">
						    	<i class=" fa fa-phone"></i>
						  	</div>
						  	<div class="media-body">
						    	<h4 class="media-heading">住最好的房</h4>
						    	<p>吃最好的食物</p>
						  </div>	<!-- End of /.media-body -->
						</div>	<!-- End of /.media -->
					</div>	<!-- End of /.block -->
				</div> <!-- End of /.col-md-4 -->
			</div>	<!-- End of /.row -->
		</div>	<!-- End of /.container -->
	</section>	<!-- End of section -->
	<!-- CATAGORIE Start
    ================================================== -->

	<section id="catagorie">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block">
						<div class="block-heading">
							<h2>我们的特色食品分类</h2>
						</div>	
						<div class="row">
						<c:forEach var="f" items="${typelistIndex}">
					
						<div class="col-sm-6 col-md-4">
							    <div class="thumbnail">
							    	<a class="catagotie-head" href="query.jsp?type=牛肉类">
										<img src="${f.head}" alt="..." style="width:230px; height:250px;">
										<h3>${f.type}</h3>
									</a>
							      	<div class="caption">
							        	<p>${f.info }<p>
							        	<p>
							        		<a href="blog-single.html" class="btn btn-default btn-transparent" role="button">
							        			<span>Check Items</span>
							        		</a>
							        	</p>
							      	</div>	<!-- End of /.caption -->
							    </div>	<!-- End of /.thumbnail -->
						  	</div>	<!-- End of /.col-sm-6 col-md-4 -->
						  	</c:forEach>


						</div>	<!-- End of /.row -->
					</div>	<!-- End of /.block --> 
				</div>	<!-- End of /.col-md-12 -->
			</div>	<!-- End of /.row -->
		</div>	<!-- End of /.container -->
	</section>	<!-- End of Section -->



	
		<!-- PRODUCTS Start
    ================================================== -->

	<section id="products">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="products-heading">
						<h2>最新特色产品</h2>
					</div>
				</div>
			</div>
			<div class="row">
			<c:forEach var="f" items="${foodlist}">
				<div class="col-md-3">
					<div class="products">
					<!-- 将foodid  id写入到 商品详情页面，先通过这个商品id查询出所有的商品信息        -->
						<a href="fooddetil.do?op=detail&id=${f.id}" onclick="query()">
							<input type="hidden" id="id" name="id" value="${f.id}">
							<img src="${f.pic}" alt="" style="width:215px;height:166px;">
						</a>
						<a href="fooddetil.do?op=detail&id=${f.id}&userid=${LoginUser.id}">
							<h4>${f.foodname }</h4>
						</a>
						<p class="price">From: £${f.newprice}</p>
						
						<a class="view-link shutter" onclick="cart(${f.id})">
							<i class="fa fa-plus-circle"></i>加入购物车</a>
					</div>	<!-- End of /.products -->
				</div>	<!-- End of /.col-md-3 -->
				</c:forEach>
				
			</div>	<!-- End of /.row -->
		</div>	<!-- End of /.container -->
	</section>	<!-- End of Section -->
		<!-- CALL TO ACTION Start
    ================================================== -->

	<section id="call-to-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="block">
						<div class="block-heading">
							<h2>我们的伙伴</h2>
						</div>
					</div>	<!-- End of /.block -->
					<div id="owl-example" class="owl-carousel">
						<c:forEach var="f" items="${href }">
						<a href="${f.href }"><div> <img src="${f.pic }" alt=""></div></a>
						</c:forEach>
					</div>	<!-- End of /.Owl-Slider -->
				</div>	<!-- End of /.col-md-12 -->
			</div> <!-- End Of /.Row -->
		</div> <!-- End Of /.Container -->
	</section>	<!-- End of Section -->
	
	

	<!-- FOOTER Start
    ================================================== -->

	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="block clearfix">
						<a href="#">
							<img src="images/footer-logo.png" alt="">
						</a>
						<p>
							Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
						</p>
						<h4 class="connect-heading">CONNECT WITH US</h4>
						<ul class="social-icon">
							<li>
								<a class="facebook-icon" href="#">
									<i class="fa fa-facebook"></i>
								</a>
							</li>
							<li>
								<a class="plus-icon" href="#">
									<i class="fa fa-google-plus"></i>
								</a>
							</li>
							<li>
								<a class="twitter-icon" href="#">
									<i class="fa fa-twitter"></i>
								</a>
							</li>
							<li>
								<a class="pinterest-icon" href="#">
									<i class="fa fa-pinterest"></i>
								</a>
							</li>
							<li>
								<a class="linkedin-icon" href="#">
									<i class="fa fa-linkedin"></i>
								</a>
							</li>
						</ul>	<!-- End Of /.social-icon -->
					</div>	<!-- End Of /.block -->
				</div> <!-- End Of /.Col-md-4 -->
				<div class="col-md-4">
					<div class="block">
						<h4>GET IN TOUCH</h4>
						<p ><i class="fa  fa-map-marker"></i> <span>Food Code d.o.o.,</span>1000 Ljubljana Celovska cesta 135, Slovenia</p>
						<p> <i class="fa  fa-phone"></i> <span>Phone:</span> (+386) 40 123 456 </p>

						<p> <i class="fa  fa-mobile"></i> <span>Mobile:</span> (+386) 40 654 123 651</p>
 
						<p class="mail"><i class="fa  fa-envelope"></i>Eamil: <span>info@sitename.com</span></p>
					</div>	<!-- End Of /.block -->
				</div> <!-- End Of Col-md-3 -->
				<div class="col-md-4">
					<div class="block">
						<h4>UPCOMING ITEMS</h4>
						<div class="media">
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="images/product-item.jpg" alt="...">
						  	</a>
						  	<a class="pull-left" href="#">
						    	<img class="media-object" src="images/product-item.jpg" alt="...">
						  	</a>
						</div>	<!-- End Of /.media -->
					</div>	<!-- End Of /.block -->
				</div> <!-- End Of Col-md-3 -->
			</div> <!-- End Of /.row -->
		</div> <!-- End Of /.Container -->
		

	<!-- FOOTER-BOTTOM Start
    ================================================== -->

		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<ul class="cash-out pull-left">
							<li>
								<a href="#">
									<img src="images/American-Express.png" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="images/PayPal.png" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="images/Maestro.png" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="images/Visa.png" alt="">	
								</a>
							</li>
							<li>
								<a href="#">
									<img src="images/Visa-Electron.png" alt="">	
								</a>
							</li>
						</ul>
						<p class="copyright-text pull-right">Copyright &copy; 2019.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
					</div>	<!-- End Of /.col-md-12 -->	
				</div>	<!-- End Of /.row -->	
			</div>	<!-- End Of /.container -->	
		</div>	<!-- End Of /.footer-bottom -->
	</footer> <!-- End Of Footer -->
	
	<a id="back-top" href="#"></a>


</body>
	
</html>