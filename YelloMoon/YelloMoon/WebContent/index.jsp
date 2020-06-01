<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:if test="${homelist==null}">
<jsp:forward page="home.do">
	<jsp:param value="query" name="op"/>
</jsp:forward>
</c:if>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
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
<script src="js/responsiveslides.min.js"></script>
<script>
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,
      	nav: true,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });
    });
</script>
 <!----font-Awesome----->
<link rel="stylesheet" href="fonts/css/font-awesome.min.css">
<!----font-Awesome----->
<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
		    <script type="text/javascript">
			    $(document).ready(function () {
			        $('#horizontalTab').easyResponsiveTabs({
			            type: 'default', //Types: default, vertical, accordion           
			            width: 'auto', //auto or any width like 600px
			            fit: true   // 100% fit in a container
			        });
			    });
</script>	
</head>
<body>
<div class="header">
	<div class="col-xs-4">
	  <div class="logo">
		<a href="index.html"><img src="images/logn.png" alt="" style="height:54px;width:174px"/></a>
	  </div>
	</div>
	<div class="col-xs-8 header_right">
	  <span class="menu"></span>
			<div class="top-menu">
				<ul>
					<li><a class="active scroll" href="index.jsp"><i class="fa fa-home"> </i>主页</a></li>
					<li><a href="about.html"><i class="fa fa-star"> </i> 关于我们</a></li>
					<li><a href="foodindex.jsp"><i class="fa fa-thumbs-up"> </i>旅游商品供应</a></li>
					<li><a href="gallery.html"><i class="fa fa-picture-o"> </i>广告</a></li>
					<li><a href="contact.html"><i class="fa fa-envelope-o"> </i>联系我们</a></li>
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
<div class="slider">
	  <div class="callbacks_container">
	      <ul class="rslides" id="slider">
	        <li><img src="images/banner.jpg" class="img-responsive" alt=""/>
	          <div class="banner_desc">
	          	<div class="container">
				  <h1>欢迎您预定酒店民宿</h1>
				  <h2>为您打造五星级的家</h2>
				</div>
				<div class="details">
			     <div class="container">
	    	     <div class="col-xs-10 dropdown-buttons">   
            	  <div class="col-xs-4 dropdown-button">           			
            		<div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">入住地点</option>
							<option value="null">商业区</option>         
							<option value="AX">豪华型</option>
							<option value="AX">经济型</option>
						 </select>
					  </div>
					</div>
				    <div class="col-xs-4 dropdown-button">
					  <div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">物业类型</option>
							<option value="null">House</option>         
							<option value="AX">Apartment</option>
							<option value="AX">Premium Economy</option>
						 </select>
					  </div>
					 </div>
					 <div class="col-xs-4 dropdown-button">
					  <div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">所有合约</option>
							<option value="null">特卖</option>         
							<option value="AX">短租</option>
							<option value="AX">长租</option>
						 </select>
					  </div>
					 </div>
				   </div> 
				   <div class="col-xs-2 submit_button"> 
				   	  <form>
				   	     <input type="submit" value="Search">
				   	  </form>
				   </div>
				   <div class="clearfix"> </div>
				</div>
			   </div>
			  </div>
			</li>
	        <li><img src="images/banner1.jpg" class="img-responsive" alt=""/>
	         <div class="banner_desc">
	            <div class="container">
				   <h1>欢迎您预定酒店民宿</h1>
				  <h2>为您打造五星级的家</h2>
			    </div>
				<div class="details">
			     <div class="container">
	    	     <div class="col-xs-10 dropdown-buttons">   
            	  <div class="col-xs-4 dropdown-button">           			
            		<div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">入住地点</option>
							<option value="null">商业区</option>         
							<option value="AX">豪华型</option>
							<option value="AX">经济型</option>
						 </select>
					  </div>
					</div>
				    <div class="col-xs-4 dropdown-button">
					  <div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">物业类型</option>
							<option value="null">House</option>         
							<option value="AX">Apartment</option>
							<option value="AX">Premium Economy</option>
						 </select>
					  </div>
					 </div>
					 <div class="col-xs-4 dropdown-button">
					  <div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">所有合约</option>
							<option value="null">特卖</option>         
							<option value="AX">短租</option>
							<option value="AX">长租</option>
						 </select>
					  </div>
					 </div>
				   </div> 
				   <div class="col-xs-2 submit_button"> 
				   	  <form>
				   	     <input type="submit" value="Search">
				   	  </form>
				   </div>
				   <div class="clearfix"> </div>
				</div>
			   </div>
			   </div>
	        </li>
	        <li><img src="images/banner2.jpg" class="img-responsive" alt=""/>
	          <div class="banner_desc">
	          	<div class="container">
				 <h1>欢迎您预定酒店民宿</h1>
				  <h2>为您打造五星级的家</h2>
			    </div>
				<div class="details">
			     <div class="container">
	    	     <div class="col-xs-10 dropdown-buttons">   
            	  <div class="col-xs-4 dropdown-button">           			
            		<div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">入住地点</option>
							<option value="null">商业区</option>         
							<option value="AX">豪华型</option>
							<option value="AX">经济型</option>
						 </select>
					  </div>
					</div>
				    <div class="col-xs-4 dropdown-button">
					  <div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">物业类型</option>
							<option value="null">House</option>         
							<option value="AX">Apartment</option>
							<option value="AX">Premium Economy</option>
						 </select>
					  </div>
					 </div>
					 <div class="col-xs-4 dropdown-button">
					  <div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">所有合约</option>
							<option value="null">特卖</option>         
							<option value="AX">短租</option>
							<option value="AX">长租</option>
						 </select>
					  </div>
					 </div>
				   </div> 
				   <div class="col-xs-2 submit_button"> 
				   	  <form>
				   	     <input type="submit" value="Search">
				   	  </form>
				   </div>
				   <div class="clearfix"> </div>
				</div>
				</div>
			    </div>
			 </li>
	      </ul>
	 </div>
</div>
<div class="smart_details">
			     <div class="container">
	    	     <div class="col-xs-10 dropdown-buttons">   
            	  <div class="col-xs-4 dropdown-button">           			
            		<div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">入住地点</option>
							<option value="null">商业区</option>         
							<option value="AX">豪华型</option>
							<option value="AX">经济型</option>
						 </select>
					  </div>
					</div>
				    <div class="col-xs-4 dropdown-button">
					  <div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">物业类型</option>
							<option value="null">House</option>         
							<option value="AX">Apartment</option>
							<option value="AX">Premium Economy</option>
						 </select>
					  </div>
					 </div>
					 <div class="col-xs-4 dropdown-button">
					  <div class="section_room">
						 <select id="country" onchange="change_country(this.value)" class="frm-field required">
							<option value="null">所有合约</option>
							<option value="null">特卖</option>         
							<option value="AX">短租</option>
							<option value="AX">长租</option>
						 </select>
					  </div>
					 </div>
				   </div> 
				   <div class="col-md-2 submit_button"> 
				   	  <form>
				   	     <input type="submit" value="Search">
				   	  </form>
				   </div>
				   <div class="clearfix"> </div>
				</div>
</div>
<div class="content_top">
   <div class="container">
   	  <h4 class="m_3">最受欢迎</h4>
   	  <div class="grid_1">
   	  <c:forEach var="f" items="${homelist }">
		<div class="col-md-3 box_1">
			<a href="home.do?op=singehome&id=${f.id}"><img src="${f.pic }" class="img-responsive" alt=""/></a>
		    <div class="box_2">
			  <div class="special-wrap"><div class="hot_offer"><span class="m_11">热卖商品</span></div><div class="forclosure"><span class="m_12">特价</span></div></div>
			</div>
		   <div class="box_3">
			 <h3><a href="home.do?op=singehome&id=${f.id}">${f.home_info }</a></h3>
			 <div class="boxed_mini_details clearfix">
			      <span class="area first"><strong>车库</strong><i class="fa fa-plane icon1"> </i>
			      ${f.cheku }</span>
			      <span class="status"><strong>浴室</strong><i class="fa fa-retweet icon1"> </i>
			      ${f.bash }</span>
			      <span class="bedrooms last"><strong>床</strong><i class="fa fa-bed">${f.bed_id}</i>
			      ${f.bash }</span>
             </div>
		   </div>
		</div>
		</c:forEach>



		<div class="clearfix"> </div>
	</div>
	   <div class="content_bottom">
		<div class="col-md-2">
			<div class="widget">
                <div class="title"><h3><i class="fa fa-meh-o men"> </i> 代售</h3></div>
                  <ul class="real-widget"><li><a href="#">商业式(3)</a></li>
                	<li><a href="#">房屋(16)</a><ul>
                		<li><a href="#">公寓式</a></li>
                		<li><a href="#">避暑别墅</a></li>
                		<li><a href="#">别墅</a></li></ul>
                  </ul></li>
                </ul>
             </div>
             <div class="widget">
                <div class="title"><h3><i class="fa fa-meh-o men"> </i> 出租</h3></div>
                  <ul class="real-widget"><li><a href="#">商业(3)</a></li>
                	<li><a href="#">房屋(16)</a><ul>
                		<li><a href="#">公寓式</a></li>
                		<li><a href="#">避暑别墅</a></li>
                		<li><a href="#">别墅</a></li></ul>
                  </ul></li>
                </ul>
             </div>
		</div>
		<div class="col-md-7">	
		   <div class="sap_tabs">
								<div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
						 <div class="tab_grid">
							  <ul class="resp-tabs-list">
							  	  <li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>公寓式</span></li>
								  <li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span>别墅</span></li>
								  <li class="resp-tab-item" aria-controls="tab_item-2" role="tab"><span>避暑别墅</span></li>
								  <div class="clearfix"></div>
							  </ul>	
							</div>	
							    <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
							    	
							    	<%int i=0 ;%>
							    	<c:forEach var="f" items="${homelimit1 }">
							    	<%i+=1 ;%>
							    	<%System.out.println(i);%>
							    	<%if(i==1){ %>
							    	<ul class="tab_img tab_1">
							    	<%} %>
									  
										<%if (i==1 || i==2){ %>
												  <li >
										  <div class="client_box1">
					       				    <img src="${f.pic }" class="img-responsive" alt=""/>
					       				     <div class="box_type">$&nbsp;${f.price }</div>
					       				     <h3 class="m_1"><a href="home.do?op=singehome&id=${f.id}">${f.home_info }</a></h3>
					       				    <div class="boxed_mini_details clearfix">
			                                    <span class="area first"><strong></strong><i class="fa fa-plane icon1"></i>
			                                    ${f.cheku }</span>
			                                    <span class="status"><strong></strong><i class="fa fa-retweet icon1"> </i>
			                                    ${f.bash }</span>
			                                    <span class="bedrooms last"><strong></strong><i class="fa fa-building-o icon1"></i>
			                                    ${f.bed_id }</span>
                                            </div>
					       				  </div>
										</li>
													
							    	<%}else if(i==3){ %>
							    	<li class="last">
										  <div class="client_box1">
					       				    <img src="${f.pic }" class="img-responsive" alt=""/>
					       				     <div class="box_type">$&nbsp;${f.price }</div>
					       				     <h3 class="m_1"><a href="home.do?op=singehome&id=${f.id}">${f.home_info }</a></h3>
					       				    <div class="boxed_mini_details clearfix">
			                                    <span class="area first"><strong>f.cheku</strong><i class="fa fa-plane icon1"></i>
			                                    2</span>
			                                    <span class="status"><strong>f.bash</strong><i class="fa fa-retweet icon1"> </i>
			                                    2</span>
			                                    <span class="bedrooms last"><strong>f.bed_id</strong><i class="fa fa-building-o icon1"></i>
			                                    2</span>
                                            </div>
					       				  </div>
										</li>
										<div class="clearfix"></div>
										</ul>
										<ul class="tab_img">
							    	<%}else if(i==4 || i==5){ %>
							    	
											<li >
										  <div class="client_box1">
					       				    <img src="${f.pic }" class="img-responsive" alt=""/>
					       				     <div class="box_type">$&nbsp;${f.price }</div>
					       				     <h3 class="m_1"><a href="home.do?op=singehome&id=${f.id}">${f.home_info }</a></h3>
					       				    <div class="boxed_mini_details clearfix">
			                                    <span class="area first"><strong></strong><i class="fa fa-plane icon1"></i>
			                                    ${f.cheku }</span>
			                                    <span class="status"><strong></strong><i class="fa fa-retweet icon1"> </i>
			                                    ${f.bash }</span>
			                                    <span class="bedrooms last"><strong></strong><i class="fa fa-building-o icon1"></i>
			                                    ${f.bed_id}</span>
                                            </div>
					       				  </div>
										</li>
							    	<%} else if(i==6){%>
							    	<%System.out.println("看这里"); %>
							    	<li class="last">
										  <div class="client_box1">
					       				    <img src="${f.pic }" class="img-responsive" alt=""/>
					       				     <div class="box_type">$&nbsp;${f.price }</div>
					       				     <h3 class="m_1"><a href="home.do?op=singehome&id=${f.id}">${f.home_info }</a></h3>
					       				    <div class="boxed_mini_details clearfix">
			                                    <span class="area first"><strong>f.cheku</strong><i class="fa fa-plane icon1"></i>
			                                    2</span>
			                                    <span class="status"><strong>f.bash</strong><i class="fa fa-retweet icon1"> </i>
			                                    2</span>
			                                    <span class="bedrooms last"><strong>f.bed_id</strong><i class="fa fa-building-o icon1"></i>
			                                    2</span>
                                            </div>
					       				  </div>
										</li>
							    	<%} %>
										</c:forEach>
										<div class="clearfix"></div>
										</ul>
							     </div>	
							     <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
									
									<ul class="tab_img">
										<li>
										  <div class="client_box1">
					       				    <img src="images/pic4.jpg" class="img-responsive" alt=""/>
					       				     <div class="box_type">$&nbsp;650</div>
					       				     <h3 class="m_1"><a href="single.html">Dolor Sit</a></h3>
					       				    <div class="boxed_mini_details clearfix">
			                                    <span class="area first"><strong>Garage</strong><i class="fa fa-plane icon1"></i>
			                                    2</span>
			                                    <span class="status"><strong>Baths</strong><i class="fa fa-retweet icon1"> </i>
			                                    2</span>
			                                    <span class="bedrooms last"><strong>Beds</strong><i class="fa fa-building-o icon1"></i>
			                                    2</span>
                                            </div>
					       				  </div>
										</li>
										<li>
										<div class="client_box1">
					       				    <img src="images/pic5.jpg" class="img-responsive" alt=""/>
					       				     <div class="box_type">$&nbsp;450</div>
					       				     <h3 class="m_1"><a href="single.html">Dolor Sit</a></h3>
					       				    <div class="boxed_mini_details clearfix">
			                                    <span class="area first"><strong>Garage</strong><i class="fa fa-plane icon1"></i>
			                                    2</span>
			                                    <span class="status"><strong>Baths</strong><i class="fa fa-retweet icon1"> </i>
			                                    2</span>
			                                    <span class="bedrooms last"><strong>Beds</strong><i class="fa fa-building-o icon1"></i>
			                                    2</span>
                                            </div>
					       				 </div>
										</li>
										<li class="last">
										 <div class="client_box1">
					       				    <img src="images/pic6.jpg" class="img-responsive" alt=""/>
					       				     <div class="box_type">$&nbsp;500</div>
					       				     <h3 class="m_1"><a href="single.html">Dolor Sit</a></h3>
					       				    <div class="boxed_mini_details clearfix">
			                                    <span class="area first"><strong>Garage</strong><i class="fa fa-plane icon1"></i>
			                                    2</span>
			                                    <span class="status"><strong>Baths</strong><i class="fa fa-retweet icon1"> </i>
			                                    2</span>
			                                    <span class="bedrooms last"><strong>Beds</strong><i class="fa fa-building-o icon1"></i>
			                                    2</span>
                                            </div>
					       				 </div>
										</li>
										<div class="clearfix"></div>
									</ul>
							     </div>	
							     <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-2">
							     	<ul class="tab_img">
										<li>
										<div class="client_box1">
					       				    <img src="images/pic5.jpg" class="img-responsive" alt=""/>
					       				     <div class="box_type">$&nbsp;450</div>
					       				     <h3 class="m_1"><a href="single.html">Dolor Sit</a></h3>
					       				    <div class="boxed_mini_details clearfix">
			                                    <span class="area first"><strong>Garage</strong><i class="fa fa-plane icon1"></i>
			                                    2</span>
			                                    <span class="status"><strong>Baths</strong><i class="fa fa-retweet icon1"> </i>
			                                    2</span>
			                                    <span class="bedrooms last"><strong>Beds</strong><i class="fa fa-building-o icon1"></i>
			                                    2</span>
                                            </div>
					       				 </div>
										</li>
										<li class="last">
										 <div class="client_box1">
					       				    <img src="images/pic6.jpg" class="img-responsive" alt=""/>
					       				     <div class="box_type">$&nbsp;500</div>
					       				     <h3 class="m_1"><a href="single.html">Dolor Sit</a></h3>
					       				    <div class="boxed_mini_details clearfix">
			                                    <span class="area first"><strong>Garage</strong><i class="fa fa-plane icon1"></i>
			                                    2</span>
			                                    <span class="status"><strong>Baths</strong><i class="fa fa-retweet icon1"> </i>
			                                    2</span>
			                                    <span class="bedrooms last"><strong>Beds</strong><i class="fa fa-building-o icon1"></i>
			                                    2</span>
                                            </div>
					       				 </div>
										</li>
										<div class="clearfix"></div>
									</ul>
							     </div>	
				  </div>
           </div>
        </div>
  <div class="col-md-3">
			<div class="blog_list2">
				 <h3>Our Agents</h3>
					 <ul class="blog-list3 list_1">
					 	<li class="blog-list3-img"><img src="images/pic12.jpg" class="img-responsive" alt=""/></li>
					 	<li class="blog-list3-desc">
					 	  <h4><a href="#">张三</a></h4>
					 	    <ul class="admin_desc">
							    <li class="list_top"><i class="fa fa-phone-square ph"> </i>
								<p class="m_2">123456789</p></li>
								<div class="clearfix"> </div>
								<li class="list_top"><i class="fa fa-envelope ph"> </i>
								<p class="m_2"><a href="malito:mail@demolink.org">2363582258</a></p></li>
								<div class="clearfix"> </div>
					        </ul>
		                 </li>
					 	<div class="clearfix"> </div>
					 </ul>
					 <ul class="blog-list3 list_1">
					 	<li class="blog-list3-img"><img src="images/pic13.jpg" class="img-responsive" alt=""/></li>
					 	<li class="blog-list3-desc">
					 	  <h4><a href="#">李四</a></h4>
					 	    <ul class="admin_desc">
							    <li class="list_top"><i class="fa fa-phone-square ph"> </i>
								<p class="m_2">20-1257-2587</p></li>
								<div class="clearfix"> </div>
								<li class="list_top"><i class="fa fa-envelope ph"> </i>
								<p class="m_2"><a href="malito:mail@demolink.org">34654321</a></p></li>
								<div class="clearfix"> </div>
					        </ul>
		                 </li>
					 	<div class="clearfix"> </div>
					 </ul>
					 <ul class="blog-list3">
					 	<li class="blog-list3-img"><img src="images/pic7.jpg" class="img-responsive" alt=""/></li>
					 	<li class="blog-list3-desc">
					 	  <h4><a href="#">李阳</a></h4>
					 	    <ul class="admin_desc">
							    <li class="list_top"><i class="fa fa-phone-square ph"> </i>
								<p class="m_2">20-1257-2587</p></li>
								<div class="clearfix"> </div>
								<li class="list_top"><i class="fa fa-envelope ph"> </i>
								<p class="m_2"><a href="malito:mail@demolink.org">22111244</a></p></li>
								<div class="clearfix"> </div>
					        </ul>
		                 </li>
					 	<div class="clearfix"> </div>
					 </ul>
			    </div>
		</div>
        <div class="clearfix"> </div>
        </div>
        <div class="grid_4">
                <div class="col-md-7 grid_6">
                    <h3>
                        你想选择<br>
                        最舒适得民宿吗
                    </h3>
                </div>
                <div class="col-md-5 grid_5">
                    <div class="banner2">
                        <a class="btn2" href="#">点击这里</a>
                        <h3>
                            详细 <br>
                            信息
                        </h3>
                        <div class="clearfix"> </div>
                    </div>
                </div>
                <div class="clearfix"> </div>
        </div>
        <div class="grid_7">
        	<div class="col-md-4 box_4">
        		<h4>最新的新闻</h4>
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
                        关于民宿 <br>
                        为提供最优质的服务<br>
                        E-MAIL:
                        <a href="#">MAIL@2363582258.ORG</a>

                        <div class="phone">
                            <span>(500)</span> 1234 5678
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