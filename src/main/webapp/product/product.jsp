<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import ="java.util.*,vo.ProductTBL"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
<title>상품 리스트 </title>
<style type="text/css">

	#listForm{
		max-width:95%;
		max-height:100%;
		margin: 0px auto;
		margin-bottom: 20px;
	}
	
	h2{
		text-align:center;
	}
	
	.productListTBL{
		margin: 0px auto;
		max-width:550px;
	}
	
	.productListTD {
		padding: 40px;
	}

	.div_empty{
		background-color:red;
		max-width: 100%;
		max-height: 100%;
		text-align: center;
	}

	#productImage{
		max-width: 200px;
		max-height: 200px;
		border: none;
	}
	
	@media (max-width: 768px) {
		.productListTD {
			padding: 0px;
		}
		
	}
		
	
	.pageDIV {
		display: flex;
		
	}
	
	.pageUL {
		list-style-type: none;
		margin: 0px auto;
	}
	
	.pageUL_LI {
		float: left; 
		margin-right: 20px;
		line-height: 40px;
	}
.carbox {
  display: block; 
    margin-bottom: 20px;
    line-height: 1.42857143;
    background-color: #fff;
    border-radius: 3px;
    box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12); 
    transition: box-shadow .25s; 
}
.carbox:hover {
  box-shadow: 0 8px 17px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
}
	
</style>
</head>
<body>
<% 
int productPageNum = (int)request.getAttribute("productPageNum"); 
int MaxProductPageNum = (int)request.getAttribute("MaxProductPageNum"); 
%>

 <section id="listForm">
	<c:if test ="${productList != null }">
		<table class="productListTBL">
		
			<tr>
			  
				<c:forEach var="productTBL"  items="${productList}" varStatus="i">
					<td class="productListTD">
					<div class="carbox">
						<a href="productView.shoes?product_no=${productTBL.product_no}">
						<img src="images/${productTBL.product_image }" id="productImage" >
						</a><br>
						상품명 : ${productTBL.product_name}<br/>
						사이즈 : ${productTBL.product_size}<br/>
						가격 : ${productTBL.product_price}원<br/>
						</div>
					</td>
				
					<input type="hidden" id="width-var" value="">
					<c:if test="${((i.index+1) mod 3)==0}">
					
						</tr>
						<tr>
					</c:if>	
				</c:forEach>
			</tr>
		</table>
	</c:if>

	<c:if test="${productList == null }"> 
		<div class="div_empty">신발이 없습니다.</div>
	</c:if>

			<div class="pageDIV">
				<ul class="pageUL" >
					<%if(productPageNum ==1 ) { %>
					<li class="pageUL_LI">
						<a href="#" class="btn btn-success" onclick="firstPage();">이전</a>
					</li>
					<% }else if(productPageNum!=1) { %>
					<li class="pageUL_LI">
						<a href="productList.shoes?productPageNum=${productPageNum-1 }" class="btn btn-success">이전</a>
					</li>
					<%} %>
					<c:forEach var="i" begin="1" end="${MaxProductPageNum }" step="1">
					<li class="pageUL_LI"><a href="productList.shoes?productPageNum=${i}">${i }</a> </li>
					</c:forEach>
					<%if(productPageNum<MaxProductPageNum){%>
					<li class="pageUL_LI">
						<a href="productList.shoes?productPageNum=${productPageNum+1 }" class="btn btn-success">다음</a>
					</li>
					<%}else if(productPageNum == MaxProductPageNum){%>
					<li class="pageUL_LI">
						<a href="#" class="btn btn-success" onclick="lastPage();">다음</a>
					</li>
					<%} %>
				</ul>
			</div>
</section> 

<script>//첫페이지, 마지막페이지 표시용
	function firstPage() {alert("첫 페이지입니다.");}
	function lastPage() {alert("마지막 페이지입니다.");}
</script>

</body>
</html>
