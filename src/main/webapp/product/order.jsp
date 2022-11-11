<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- <%@page import="com.sun.org.apache.bcel.internal.generic.Select"%> --%>
       <%@page import="java.util.*"%>
    <%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.CartTBL" %>
<%@page import="vo.ProductTBL" %>
    <link rel="stylesheet" href="./css/bootstrap.css"/>
 
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width", initial-scale="1" >
<title>장바구니 목록</title>
<!-- <style type="text/css">
#listForm {
   width: 1200px;
   margin: auto;
   
}

h3 {
   text-align: left;
}

table {
   width: 1000px;
   margin: auto;
  
}
.table2{
 width: 1000px;
   margin: auto;
}

.tr_top {
   background-color: #EAEAEA;
}

.div_empty {
   text-align: center;
   background-color:
}

.td_command {
   text-align: right;
}
#todayImageList{
   text-align: center;
}
#productImage{
   width:100px;
   height:150px;
   border:none;
}
#cartImage{
   width:100px;
   height:100px;
   border:none;
}
#select{
	text-align: right;
}
#commandList{
	text-align: center;
}
.row {
background-color: #EAEAEA;
text-align : center;
}



</style> -->
<style type="text/css">
#listForm {
 max-width:100%;
		max-height:100%;
		margin: 0px auto;
   
}

h3 {
   text-align: left;
}

table {
   width: 90%;
   margin: auto;
  
}


.tr_top {
   background-color: #EAEAEA;
}


#cartImage{
   width:100px;
   height:100px;
   border:none;
}
#select{
	text-align: right;
}

tbody {
text-align:"center";
backgrround-color: #EAEAEA;
}
#commandList{
		text-align: center;
	}
.row {
text-align : center;
}
.container2{
padding-left : 25%;
}
</style>

</head>
<body>


<section id="listForm">

<c:if test="${sessionScope.productList != null && sessionScope.productList.size() > 0}">
   <h3>${sessionScope.member_id }님의 주문내역</h3>
   
<form method="post" action="#" name="f">

 <table>
        <tr class = "tr_top">
        
             <td>번호</td><!-- 제품번호 추가해서 -->
             <td>상품 이미지</td>
             <td>상품명</td>
             <td>가격</td>
             <td>수량</td>
         </tr>

        <c:forEach var="product" items="${sessionScope.productList}" varStatus = "i">
        
        <tr>
        	
             <td>
             
          	   ${i.index+1}<!-- 번호값계산 -->
            </td>
             <td>
            	 <img src = "images/${product.product_image }" id ="cartImage"/>
            </td>
             <td>
            	 ${product.product_name }
            </td>
             <td>
           	  ${product.product_price}원
            </td>
             <td>
            	 <br>
            	 ${product.product_amount } <!--현재 수량  -->
            	 <br>
         </tr>
       
        </c:forEach>
        <tr>
              <td colspan="7" style="text-align:right;">
           <h4> 총 금액 : ${sessionScope.totalMoney}원</h4>
         </td>
      </tr>
      </table>
     <%--  <button type="submit" class="btn btn-default" formaction="productCartOrder.shoes?totalmoney = ${totalMoney }">구매하기</button> --%>
	</form>
	
 </c:if>

		<!-- 배송정보 -->

			<br><br>
			<hr class="one">
			<h3>배송 정보</h3>
	<div class="container2">
 
	<div class="col-lg-8 well">
	<div class="row">
				<form>
					<div class="col-sm-15">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>주문자 아이디</label>
								<p class="form-control">${memberTBL.member_id }</p>
							</div>
							<div class="col-sm-6 form-group">
								<label>받으시는 분</label>
								<p class="form-control">${memberTBL.member_name }</p>
							</div>
						</div>					
						<div class="form-group">
							<label>주소</label>
							<p class="form-control">${memberTBL.address1 }</p>
							<p class="form-control">${memberTBL.address2 }</p>
							<p class="form-control">${memberTBL.address3 }</p>
						</div>	
					
					<div class="form-group">
						<label>휴대전화</label>
						<p class="form-control">${memberTBL.member_phone }</p>
					</div>		
					<div class="form-group">
						<label>이메일</label>
						<p class="form-control">${memberTBL.member_email }</p>
					</div>	
					
								
					</div>
				</form> 
				</div>
				<nav id = "commandList">
			<button type="button" class="btn btn-default"><a href="mainTemplate.jsp">홈으로</a></button>
		</nav>
	</div>
	</div>
		<br>
<br>

</body>
</html>