<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

	 .slideImg {
		 width: 1200px; max-height: 600px;
	 }
	 
</style>

</head>
<body>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
    		<ol class="carousel-indicators">
	    		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	    		<li data-target="#myCarousel" data-slide-to="1"></li>
	    		<li data-target="#myCarousel" data-slide-to="2"></li>
    		</ol>
    		<div class="carousel-inner">
	    		<div class="item active mainSlideIMG">
	    			<img src="images/nikelogo1.jpg" class="slideImg">
	    		</div>
	    		<div class="item mainSlideIMG">
	    			<img src="./images/nikelogo2.jpg" class="slideImg">
	    		</div>
	    		<div class="item mainSlideIMG">
	    			<img src="./images/nikelogo3.jpg" class="slideImg">
	    		</div>
    		</div>
    		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
    			<span class="glyphicon glyphicon-chevron-left"></span>
    		</a>
    		<a class="right carousel-control" href="#myCarousel" data-slide="next">
    			<span class="glyphicon glyphicon-chevron-right"></span>
    		</a>
    	</div>
    	
</body>
</html>