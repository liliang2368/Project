<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Single</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Realist Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.1.min.js"></script>
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href='http://fonts.useso.com/css?family=Grand+Hotel:400' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Roboto' rel='stylesheet:100,300,400,500,600,700,800,900' type='text/css'>
<!-- Menu -->
<!----font-Awesome----->
<link rel="stylesheet" href="fonts/css/font-awesome.min.css">
<!----font-Awesome----->
<script src="js/scripts.js" type="text/javascript"></script>
</head>
<body>
<div class="header">
	<div class="col-xs-4">
	  <div class="logo">
		<a href="index.html"><img src="images/logo.png" alt=""/></a>
	  </div>
	</div>
	<div class="col-xs-8 header_right">
	  <span class="menu"></span>
			<div class="top-menu">
				<ul>
					<li><a class="active scroll" href="index.html"><i class="fa fa-home"> </i>主页</a></li>
					<li><a href="about.html"><i class="fa fa-star"> </i> 关于我们</a></li>
					<li><a href="services.html"><i class="fa fa-thumbs-up"> </i>Services</a></li>
					<li><a href="gallery.html"><i class="fa fa-picture-o"> </i>Gallery</a></li>
					<li><a href="contact.html"><i class="fa fa-envelope-o"> </i>Contact</a></li>
					<div class="clearfix"></div>
				</ul>
			 </div>
			<!-- script for menu -->
				<script>
				$( "span.menu" ).click(function() {
				  $( ".top-menu" ).slideToggle( "slow", function() {
				    // Animation complete.
				  });
				});
			</script>
			<!-- script for menu -->
	</div>
	<div class="clearfix"> </div>
</div>
<div class="about_top">
    <div class="container">
                <div class="standards">
						<ul class="selectors_wrapper">
							<li class="selector active" data-selector="1">详情图片</li>
							<li class="selector" data-selector="2">位置</li>
							<li class="selector" data-selector="3">音乐</li>
					    </ul>
						<div class="standard_content" style="height: 439px;">
							<div class="standard active" data-selector="1">
						 	     <ul class="single_grid">
						 	     	<li><img src="${singlehome.pic1}" class="img-responsive" alt=""/></li>
						 	     	<li><img src="${singlehome.pic1}" class="img-responsive" alt=""/></li>
						 	     	<li><img src="${singlehome.pic2}" class="img-responsive" alt=""/></li>
						 	     	<li><img src="${singlehome.pic3}" class="img-responsive" alt=""/></li>
						 	     	<li><img src="${singlehome.pic4}" class="img-responsive" alt=""/></li>
						 	     	<li><img src="${singlehome.pic5}" class="img-responsive" alt=""/></li>
						 	     	<li><img src="${singlehome.pic6}" class="img-responsive" alt=""/></li>
						 	     	<li><img src="${singlehome.pic7}" class="img-responsive" alt=""/></li>
						 	     	<div class="clearfix"> </div>
						 	     </ul> 
							</div>
							<div class="standard" data-selector="2">
							   <div class="map1">
			                     <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d3150859.767904157!2d-96.62081048651531!3d39.536794757966845!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sin!4v1408111832978"> </iframe>
			                   </div>
			                </div>
							<div class="standard video" data-selector="3">
							   <iframe width="100%" height="350" src="https://www.youtube.com/embed/lSXGxOiRp7A" frameborder="0" allowfullscreen></iframe>
							</div>
					   </div>
					   <div class="clearfix"> </div>
			    </div>
		<div class="col-md-9 single_box1">
			<ul class="single_box">
		     <li><i class="fa fa-building-o icon1"> </i> ${singlehome.bed_id} 间卧室</li>
		     <li><i class="fa fa-retweet icon1"> </i> ${singlehome.bash} 间浴室</li>
		     <li><i class="fa fa-plane icon1"> </i> ${singlehome.cheku } 个车库</li>	
		    </ul>
		    <p>${singlehome.info1 }</p>
	        <p class="single_desc">${singlehome.info2 }</p>	
	    </div>
		<div class="col-md-3">
			<div class="blog_list2">
				 <h3>房间管理者</h3>
				 <c:forEach var="f" items="${sigle_peizhi }">
					 <ul class="blog-list3 list_1">
					 	<li class="blog-list3-img"><img src="images/pic12.jpg" class="img-responsive" alt=""></li>
					 	<li class="blog-list3-desc">
					 	  <h4><a href="#">${f.username }</a></h4>
					 	    <ul class="admin_desc">
							    <li class="list_top"><i class="fa fa-phone-square ph"> </i>
								<p class="m_2">${f.phone }</p></li>
								<div class="clearfix"> </div>
								<li class="list_top"><i class="fa fa-envelope ph"> </i>
								<p class="m_2"><a href="malito:mail@demolink.org">邮件${f.email }</a></p></li>
								<div class="clearfix"> </div>
					        </ul>
		                 </li>
					 	<div class="clearfix"> </div>
					 </ul>
</c:forEach>
			    </div>
		</div>
		<div class="clearfix"> </div>			
		<div class="about_grid1">
        	<h3>我们的优势</h3>
        	<div class="col-md-4">
        		<div class="list styled custom-list">
				  <ul>
					 <li><span class="dropcap">01 / </span>  
					   <div class="about_desc">
					 	<h5><a href="#">具体历时</a></h5>
					 	<p>爱彼迎<br>
					 	
工作机会<br>
爱彼迎新闻<br>
政策<br>
帮助<br>
多元化与归属感<br>
无障碍设施</p>
					   </div>
					 </li>
				  </ul>
				</div>
        	</div>
        	<div class="col-md-4">
        		<div class="list styled custom-list">
				  <ul>
					 <li><span class="dropcap">02 / </span>  
					   <div class="about_desc">
					 	<h5><a href="#">发现</a></h5>
					 	<p>信任与安全<br>
旅行基金<br>
爱彼迎公民<br>
商务差旅<br>
缤纷体验新推出<br>
爱彼迎杂志</p>
					   </div>
					 </li>
				  </ul>
				</div>
        	</div>
        	<div class="col-md-4">
        		<div class="list styled custom-list">
				  <ul>
					 <li><span class="dropcap">03 / </span>  
					   <div class="about_desc">
					 	<h5><a href="#">The standard chunk of Lorem Ipsum used since the 1500s</a></h5>
					 	<p>为什么要出租?<br>
待客之道<br>
房东义务<br>
开展体验新推出<br>
Open Homes</p>
					   </div>
					 </li>
				  </ul>
				</div>
        	</div>
        	<div class="clearfix"> </div>
        </div>
        <div class="about_grid2">
        	<h3>值得推荐</h3>
        	<c:forEach var="f" items="${homelist }">
        	<div class="col-md-3 about_box2">
        		<img src="${f.pic}" class="img-responsive" alt=""/> 
        		<h3>${f.home_info }</h3>
        		<p>Hello,您所浏览的房源[白可可]是一套46mi一室一厅，无敌江景落地窗!房间配有马歇尔蓝牙音响与移动网络坚果投影。床.上配备宜家高品质床垫，宜家乳胶枕头和纯棉床品,- - -客-换的高品质毛巾与浴巾,高品质洗发水，沐浴露超高品质一次性用品等绝对给您带来超棒的居住体验,是一套美景与地段绝佳，兼具美貌与舒适度的房子</p>
        	</div>
        	</c:forEach>

        	<div class="clearfix"> </div>
        </div>
        <div class="grid_4">
                <div class="col-md-7 grid_6">
                    <h3>
                        Want to sell <br>
                        your real estate?
                    </h3>
                </div>
                <div class="col-md-5 grid_5">
                    <div class="banner2">
                        <a class="btn2" href="#">click here</a>
                        <h3>
                            FOR A FREE <br>
                            APPRAISAL
                        </h3>
                        <div class="clearfix"> </div>
                    </div>
                </div>
                <div class="clearfix"> </div>
        </div>
        <div class="grid_7">
        	<div class="col-md-4 box_4">
        		<h4>Receive our Newsletter</h4>
        		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliq.</p>
        	    <div class="search">
						  <form>
							   <input type="text" value="">
							   <input type="submit" value="Subscribe">
						  </form>
					    </div>
        	</div>
        	<div class="col-md-4">
        		<address class="footer-addr">
                        totam rem aperiam, <br>
                        voluptatum deleniti , USA <br>
                        E-MAIL:
                        <a href="#">MAIL@DEMOLINK.ORG</a>

                        <div class="phone">
                            <span>(500)</span> 1254 6487
                        </div>
                    </address>
        	</div>
        	<div class="col-md-2">
        		<ul class="list_2">
                        <li>
                            <a href="#">1st &amp; 2nd Mortgages</a>
                        </li>
                        <li>
                            <a href="#">Construction Loans</a>
                        </li>
                        <li>
                            <a href="#">Fractional Ownerships</a>
                        </li>
                        <li>
                            <a href="#">Home Refinancing</a>
                        </li>
                        <li>
                            <a href="#">Home Equity Lines</a>
                        </li>
                </ul>
        	</div>
        	<div class="col-md-2">
        		<ul class="list_2">
                        <li>
                            <a href="#">1st &amp; 2nd Mortgages</a>
                        </li>
                        <li>
                            <a href="#">Construction Loans</a>
                        </li>
                        <li>
                            <a href="#">Fractional Ownerships</a>
                        </li>
                        <li>
                            <a href="#">Home Refinancing</a>
                        </li>
                        <li>
                            <a href="#">Home Equity Lines</a>
                        </li>
                </ul>
        	</div>
        	<div class="clearfix"> </div>
        </div>
        <div class="copy">
			<p>Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
	    </div>
	</div>
</div>
</body>
</html>		