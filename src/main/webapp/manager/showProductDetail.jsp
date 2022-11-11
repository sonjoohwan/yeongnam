<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<!-- 반응형 웹에 사용하는 메타태그 -->
<title>제품상세(수정/삭제)</title>
</head>

<body>
	
		<div class="row"> 
			<form action="#" method="post" name="f">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"
							style="background-color: #eeeeee; text-align: center;">제품상세 보기</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${productDetail != null}">
						<tr>
							<td style="width: 20%;">제품번호</td>
							<td colspan="2">
								<input type="text" name="product_no" value="${productDetail.product_no}" size="50"/>
							</td>
						</tr>
						<tr>
							<td>제조사</td>
							<td colspan="2">
								<input type="text" name="category_code" value="${productDetail.category_code }" size="50"/>
							</td>
						</tr>
						<tr>
							<td>제품명</td>
							<td colspan="2">
								<input type="text" name="product_name" value="${productDetail.product_name }" size="50"/>
							</td>
						</tr>
						<tr>
							<td>사이즈</td>
							<td colspan="2">
								<input type="text" name="product_size" value="${productDetail.product_size }" size="50"/>
							</td>
						</tr>
						<tr>
							<td>가격</td>
							<td colspan="2">
								<input type="text" name="product_price" value="${productDetail.product_price }" size="50"/>
							</td>
						</tr>
						<tr>
							<td>재고수량</td>
							<td colspan="2">
								<input type="text" name="product_amount" value="${productDetail.product_amount }" size="50"/>
							</td>
						</tr>
						<tr>
							<td>제품설명</td>
							<td colspan="2">
								<textarea rows="10" cols="51" name="product_decs">${productDetail.product_decs }</textarea>
							</td>
						</tr>
						<tr>
							<td>제품등록일</td>
							<td colspan="2">
								<input type="text" name="product_date" value="${productDetail.product_date }"/>
							</td>
						</tr>
						<tr>
							<td>조회수</td>
							<td colspan="2">
								<input type="text" name="product_hits" value="${productDetail.product_hits }"/>
							</td>
						</tr>
						<tr>
							<td>이미지 파일</td>
							<td colspan="2">
								<input type="file" name="product_image"/>
								<!-- 
								input type="file"은 default value를 세팅할 수 없기때문에 
								기존 파일명을 가져와 파일 선택하지 않았을 때 다시 넣어줄 값을 hidden타입에 넣어둔다 -->
								<input type="hidden" name="orgin_product_image" value="${productDetail.product_image }" />
								현재 등록된 파일 :  ${productDetail.product_image } <br>
								<img src="../images/${productDetail.product_image }"/>
							</td>
						</tr>
						
					</c:if>
					
					<c:if test="${productDetail == null}">
					<tr>
						<td colspan="3">상품 정보를 불러오지 못했습니다.</td>
					</tr>
					</c:if>
				</tbody>
			</table>
			
			<div class="alignCenter">
				<a href="showProductList.mgr" class="btn btn-primary">전체 상품 목록보기</a>
				<input type="submit" formaction="deleteProduct.mgr?product_no=${productDetail.product_no }" class="btn btn-primary pull-right" value="제품삭제"/>
				<input type="submit" formaction="updateProduct.mgr" class="btn btn-primary pull-right" value="제품수정"/>
			</div>
			</form>
		</div>
</body>
</html>