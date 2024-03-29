<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:if test="${allhomelist==null}">
<jsp:forward page="home.do">
	<jsp:param value="queryall" name="op"/>
</jsp:forward>
</c:if>
<!DOCTYPE HTML>
<html>
<head>
<title>所有商品</title>
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
<script type="text/javascript" src="js/jquery.fancybox.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" media="screen" />
<script type="text/javascript">
		$(document).ready(function() {
			$('.fancybox').fancybox();
		});
	</script>
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
					<li><a href="index.html"><i class="fa fa-home"> </i>Home</a></li>
					<li><a href="about.html"><i class="fa fa-star"> </i> About</a></li>
					<li><a class="active scroll" href="services.html"><i class="fa fa-thumbs-up"> </i>Services</a></li>
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
       <div class="about">
       <%int i=0;%>
       <c:forEach var="f" items="${allhomelist}">
       <%i+=1;%>
       <%if (i==1){ %>
       	 <div class="service_grid">
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s1.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s1.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>${f.home_info }</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<%}else if(i==2 || i==3){ %>
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s2.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s2.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House2</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s3.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s3.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House3</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	
	       	<%}else if(i==4){ %>
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s4.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s4.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House4</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="clearfix"> </div>
         </div>
         <%} %> 
         </c:forEach>
         <div class="service_grid">
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s5.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s5.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House5</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s6.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s6.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House6</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s7.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s7.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House7</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s8.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s8.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House8</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="clearfix"> </div>
         </div> 
         <div class="service_grid1">
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s9.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s9.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House9</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s10.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s10.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House10</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s11.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s11.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House11</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="col-md-3 service_box">
	       		<a class="fancybox" href="images/s12.jpg" data-fancybox-group="gallery" title="Product Name"><img src="images/s12.jpg" class="img-responsive" alt=""/><span> </span></a>
			    <h3>House12</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua<span>  <a href="#">[...]</a></span></p>
	       	</div>
	       	<div class="clearfix"> </div>
         </div> 
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