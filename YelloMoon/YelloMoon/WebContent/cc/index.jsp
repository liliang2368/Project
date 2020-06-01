<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--Custom Theme files-->
<link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">
<link href="css/menu.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
<!--//Custom Theme files-->
<!--js-->
<script src="js/jquery-2.2.3.min.js"></script> 
<script src="js/SmoothScroll.min.js"></script>
<!--//js-->
<!---<link href='http://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>--->
<!--start-smooth-scrolling-->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>	
<c:if test="${homelist==null}">
<jsp:forward page="home.do">
	<jsp:param value="query" name="op"/>
</jsp:forward>




</c:if>
<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
		
		$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
			});
		});
</script>
<!--//end-smooth-scrolling-->	
</head>
<body>
	<!--banner-->
	<div class="banner">
		<!--header-->
		<div class="header">
			<div class="container">		
				<div class="logo">
					<h1><a href="index.html">Trendy <span>Look</span></a></h1>
				</div>
				<div class="social-icons">
					<ul>
						<li><a href="#"> </a></li>
						<li><a href="#" class="fb"> </a></li>
						<li><a href="#" class="in"> </a></li>
						<li><a href="#" class="dott"> </a></li>
					</ul>
				</div>
				<div class="menu">
					<div class="overlay-navigation">
						<nav role="navigation">
							<ul>
								<li><a href="index.html" data-content="The beginning">Home</a></li>
								<li><a href="about.html" data-content="Curious?">About</a></li>
								<li><a href="gallery.html" data-content="Our projects">Gallery</a></li>
								<li><a href="codes.html" data-content="Only the finest">Codes</a></li>
								<li><a href="contact.html" data-content="Don't hesitate">Contact</a></li>
							</ul>
						</nav>
					</div>
					<section class="main">
						<div class="open-overlay"> <span class="bar-top"></span> <span class="bar-middle"></span> <span class="bar-bottom"></span> </div>
					</section>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<!--//header-->
		<!--banner-text-->
		<div class="banner-text">
			<div class="container">		
				<h5>Duis euismod massa ut sem fringilla blandit </h5>
				<h6>1</h6>
				<h2>Here Your Dream Home</h2>
				<h4><span class="glyphicon glyphicon-phone"></span> +11 222 333 444</h4>
			</div>
		</div>
		<!--//banner-text-->
	</div>
	<!--//banner-->
	<!--about-->

<div id="services">
  <div class="container">
    <div class="section-title">
      <h2>Our Services</h2>
    </div>
    <div class="row">
	<c:forEach var="f" items="${homelist}">
	      <div class="col-md-4">
        <div class="service-media"> <a href="home.do?op=singehome&id=${f.id }"><img src="${f.pic }" alt=" "></a> </div>
        <div class="service-desc">
        	<p>${f.home_info}</p>
          <span>价格</span><h3>${f.price }</h3>
          
          <span>地区</span><p>${f.are}</p>
        </div>
      </div>
	
	
	</c:forEach>

    </div>
  </div>
</div>
	<style>
		
#services {
    padding: 100px 0;
}
img {
    /* vertical-align: middle; */
    position: relative;
    display: block;
    width: 100%;
    height: auto;
}
* {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
* {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
user agent stylesheet
div {
    display: block;
}
body {
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 14px;
    line-height: 1.42857143;
    color: #333;
    background-color: #fff;
}
body, html {
    font-family: 'Open Sans', sans-serif;
    text-rendering: optimizeLegibility !important;
    -webkit-font-smoothing: antialiased !important;
    color: #666;
    font-weight: 400;
    width: 100% !important;
    height: 100% !important;
}
body, html {
    font-family: 'Open Sans', sans-serif;
    text-rendering: optimizeLegibility !important;
    -webkit-font-smoothing: antialiased !important;
    color: #666;
    font-weight: 400;
    width: 100% !important;
    height: 100% !important;
}
body {
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 14px;
    line-height: 1.42857143;
    color: #333;
    background-color: #fff;
}
html {
    font-family: sans-serif;
    -webkit-text-size-adjust: 100%;
    -ms-text-size-adjust: 100%;
}
html {
    font-size: 10px;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
html {
    font-size: 10px;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
html {
    font-family: sans-serif;
    -webkit-text-size-adjust: 100%;
    -ms-text-size-adjust: 100%;
}
user agent stylesheet
html {
    color: -internal-root-color;
}
*:before, *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
*:before, *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
*:before, *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
*:before, *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
*:before, *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
*:before, *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
	</style>
	<!--//about-->
	<!--slid-->
	<div class="slid">
		<div class="slid-text">
			<h4>Lorem ipsum dolor sit amet </h4>
			<p>Proin tincidunt sodales faucibus. Curabitur ut metus sed urna dignissim sodales ac a tellus. Sed varius justo tellus, at convallis libero cursus non. In malesuada accumsan felis, a imperdiet arcu blandit sed. Ut id faucibus eros. Fusce sed vulputate dui, non consectetur felis. Etiam id enim sem. Suspendisse commodo tempor magna </p>
			<a href="single.html" class="button button-w3ls" data-text="Read More"><span>Read More</span></a>
		</div>
		<div class="clearfix"> </div>

	<!--//slid-->
	<!--skills-->
	<div class="skills">
		<div class="container">
			<div class="col-md-6 skills-left">
				<h3 class="title-agile">Our Skills </h3>
				<div class="bar_group">
					<div class='bar_group__bar thin' label='Ut Commodo' show_values='true' tooltip='true' value='350'></div>
					<div class='bar_group__bar thin' label='Nulla Facilisi' show_values='true' tooltip='true' value='222'></div>
					<div class='bar_group__bar thin' label='Lorem ipsum dolor' show_values='true' tooltip='true' value='475'></div>
					<div class='bar_group__bar thin' label='Nam ex velit' show_values='true' tooltip='true' value='286'></div>
				</div>
				<script src="js/bars.js"></script>
			</div>
			<div class="col-md-6 skills-right">
				<h3 class="title-agile">Testimonials </h3>
				<section class="slider">
					<div class="flexslider">
						<ul class="slides">
							<li>
								<div class="skill-w3agile">
									<div class="col-md-2 skill-w3agile-left">
										<img src="images/img1.png" alt=""/>
									</div>
									<div class="col-md-10 skill-w3agile-right">
										<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum </p>
										<h5>Mark Sophia</h5>
									</div>
									<div class="clearfix"> </div>
								</div>
								<div class="skill-w3agile">
									<div class="col-md-2 skill-w3agile-left">
										<img src="images/img2.png" alt=""/>
									</div>
									<div class="col-md-10 skill-w3agile-right">
										<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum </p>
										<h5>Robinson Sede</h5>
									</div>
									<div class="clearfix"> </div>
								</div>
							</li>
							<li>
								<div class="skill-w3agile">
									<div class="col-md-2 skill-w3agile-left">
										<img src="images/img3.png" alt=""/>
									</div>
									<div class="col-md-10 skill-w3agile-right">
										<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum </p>
										<h5>Sandra Jeff</h5>
									</div>
									<div class="clearfix"> </div>
								</div>
								<div class="skill-w3agile">
									<div class="col-md-2 skill-w3agile-left">
										<img src="images/img4.png" alt=""/>
									</div>
									<div class="col-md-10 skill-w3agile-right">
										<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum </p>
										<h5>Daniel Nyari</h5>
									</div>
									<div class="clearfix"> </div>
								</div>
							</li>
							<li>
								<div class="skill-w3agile">
									<div class="col-md-2 skill-w3agile-left">
										<img src="images/img2.png" alt=""/>
									</div>
									<div class="col-md-10 skill-w3agile-right">
										<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum </p>
										<h5>Douglas Joe</h5>
									</div>
									<div class="clearfix"> </div>
								</div>
								<div class="skill-w3agile">
									<div class="col-md-2 skill-w3agile-left">
										<img src="images/img3.png" alt=""/>
									</div>
									<div class="col-md-10 skill-w3agile-right">
										<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum </p>
										<h5>Laura Hill</h5>
									</div>
									<div class="clearfix"> </div>
								</div>
							</li>
						</ul>
					</div>
				</section>
				<!--FlexSlider-->
				<script defer src="js/jquery.flexslider.js"></script>
				<script type="text/javascript">
					$(window).load(function(){
					  $('.flexslider').flexslider({
						animation: "slide",
						start: function(slider){
						  $('body').removeClass('loading');
						}
					  });
					});
				</script>
				<!--End-slider-script-->
			</div>
			<div class="clerfix"> </div>
		</div>
	</div>
	<!--//skills-->
	<!--footer-->
	<div class="footer">
		<div class="container">
			<div class="footer-info">
				<div class="col-md-4 footer-grids">
					<h3>More About Us</h3>
					<p>Nulla facilisi. Mauris pretium cursus tincidunt. Nam malesuada purus sit amet est rhoncus vulputate. Duis sit amet porttitor urna. Aliquam erat volutpat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Praesent dictum eros ac nisi scelerisque, eu imperdiet elit tempus.</p>
				</div>
				<div class="col-md-4 footer-grids">
					<h3>Popular Items</h3>
					<div class="footer-grd">
						<a href="single.html">
							<img src="images/f1.jpg" class="img-responsive" alt=" ">
						</a>
					</div>
					<div class="footer-grd">
						<a href="single.html">
							<img src="images/f2.jpg" class="img-responsive" alt=" ">
						</a>
					</div>
					<div class="footer-grd">
						<a href="single.html">
							<img src="images/f3.jpg" class="img-responsive" alt=" ">
						</a>
					</div>
					<div class="clearfix"> </div>
					<div class="footer-grd">
						<a href="single.html">
							<img src="images/f4.jpg" class="img-responsive" alt=" ">
						</a>
					</div>
					<div class="footer-grd">
						<a href="single.html">
							<img src="images/f5.jpg" class="img-responsive" alt=" ">
						</a>
					</div>
					<div class="footer-grd">
						<a href="single.html">
							<img src="images/f6.jpg" class="img-responsive" alt=" ">
						</a>
					</div>
					<div class="clearfix"> </div>
				</div>
				<div class="col-md-4 footer-grids footer-address">
					<h3>Contact Us:</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris pellentesque nisi at tortor suscipit ultricies.</p>
					<ul>
						<li> 123 San ,West street</li>
						<li>Sebastian dolor</li>
						<li> New York City, USA. </li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>	
	</div>	
	<!--//footer-->	
	<!--footer-nav-->	
	<div class="footer-nav">
		<div class="container">
			<div class="footer-nav-left">
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="about.html">About</a></li>
					<li><a href="gallery.html">Gallery</a></li>
					<li><a href="codes.html">Codes</a></li>
					<li><a href="contact.html">Contact</a></li>
				</ul>
			</div>
			<div class="footer-nav-right">
				<h4><a href="index.html">Trendy <span>Look</span></a></h4>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
	<!--//footer-nav-->	
	<div class="footer-copy">
		<div class="container">
			<p>Copyright &copy; 2016.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
		</div>
	</div>
	<!--nav-script-->
	<script>
		$('.open-overlay').click(function() {
		  var overlay_navigation = $('.overlay-navigation'),
			nav_item_1 = $('nav li:nth-of-type(1)'),
			nav_item_2 = $('nav li:nth-of-type(2)'),
			nav_item_3 = $('nav li:nth-of-type(3)'),
			nav_item_4 = $('nav li:nth-of-type(4)'),
			nav_item_5 = $('nav li:nth-of-type(5)'),
			top_bar = $('.bar-top'),
			middle_bar = $('.bar-middle'),
			bottom_bar = $('.bar-bottom');

		  overlay_navigation.toggleClass('overlay-active');
		  if (overlay_navigation.hasClass('overlay-active')) {

			top_bar.removeClass('animate-out-top-bar').addClass('animate-top-bar');
			middle_bar.removeClass('animate-out-middle-bar').addClass('animate-middle-bar');
			bottom_bar.removeClass('animate-out-bottom-bar').addClass('animate-bottom-bar');
			overlay_navigation.removeClass('overlay-slide-up').addClass('overlay-slide-down')
			nav_item_1.removeClass('slide-in-nav-item-reverse').addClass('slide-in-nav-item');
			nav_item_2.removeClass('slide-in-nav-item-delay-1-reverse').addClass('slide-in-nav-item-delay-1');
			nav_item_3.removeClass('slide-in-nav-item-delay-2-reverse').addClass('slide-in-nav-item-delay-2');
			nav_item_4.removeClass('slide-in-nav-item-delay-3-reverse').addClass('slide-in-nav-item-delay-3');
			nav_item_5.removeClass('slide-in-nav-item-delay-4-reverse').addClass('slide-in-nav-item-delay-4');
		  } else {
			top_bar.removeClass('animate-top-bar').addClass('animate-out-top-bar');
			middle_bar.removeClass('animate-middle-bar').addClass('animate-out-middle-bar');
			bottom_bar.removeClass('animate-bottom-bar').addClass('animate-out-bottom-bar');
			overlay_navigation.removeClass('overlay-slide-down').addClass('overlay-slide-up')
			nav_item_1.removeClass('slide-in-nav-item').addClass('slide-in-nav-item-reverse');
			nav_item_2.removeClass('slide-in-nav-item-delay-1').addClass('slide-in-nav-item-delay-1-reverse');
			nav_item_3.removeClass('slide-in-nav-item-delay-2').addClass('slide-in-nav-item-delay-2-reverse');
			nav_item_4.removeClass('slide-in-nav-item-delay-3').addClass('slide-in-nav-item-delay-3-reverse');
			nav_item_5.removeClass('slide-in-nav-item-delay-4').addClass('slide-in-nav-item-delay-4-reverse');
		  }
		})
	</script>
	<!--nav-script-->
	<!--smooth-scrolling-of-move-up-->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
			var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
			};
			*/
			
			$().UItoTop({ easingType: 'easeOutQuart' });
			
		});
	</script>
	<!--//smooth-scrolling-of-move-up-->
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.js"></script>
</body>

</html>