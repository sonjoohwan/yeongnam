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
<title>제품등록</title>
</head>
<!-- <script type="text/javascript">
	function changeValue(a) {
		var filePath = a ;
		
		var fakePath = "C:\\fakepath\\";
		
		var result = filePath.substring(fakePath.length());
		
		return f.product_image.value = result;
		
	}

</script> -->
<!-- <script type="text/javascript">
function readURL(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    reader.onload = function(e) {
	      document.getElementById('preview').src = e.target.result;
	    };
	    reader.readAsDataURL(input.files[0]);
	  } else {
	    document.getElementById('preview').src = "";
	  }
	}
</script> -->
<body>
	
		<div class="row"> 
			<form action="insertProduct.mgr" method="post" name="f">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd; width: 80%; margin: 0px auto;">
				<thead>
					<tr>
						<th colspan="3"
							style="background-color: #eeeeee; text-align: center;">제품등록</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td style="width: 20%;">제품번호</td>
							<td colspan="2">
								<input type="text" name="product_no" required="required" size="50"/>
							</td>
						</tr>
						<tr>
							<td>제조사</td>
							<td colspan="2">
								<input type="text" name="category_code" required="required" size="50"/>
							</td>
						</tr>
						<tr>
							<td>제품명</td>
							<td colspan="2">
								<input type="text" name="product_name" required="required" size="50"/>
							</td>
						</tr>
						<tr>
							<td>사이즈</td>
							<td colspan="2">
								<input type="text" name="product_size" required="required" size="50"/>
							</td>
						</tr>
						<tr>
							<td>가격</td>
							<td colspan="2">
								<input type="text" name="product_price" required="required" size="50"/>
							</td>
						</tr>
						<tr>
							<td>재고수량</td>
							<td colspan="2">
								<input type="text" name="product_amount" required="required" size="50"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: center; vertical-align: middle;">제품설명</td>
							<td colspan="2">
							<textarea name="product_decs" rows="10" cols="51" required="required" ></textarea>
							</td>
						</tr>
						<tr>
							<td>제품등록일</td>
							<td colspan="2">
								<input type="text" name="product_date" value="자동입력" readonly="readonly" style="text-align: center;"/>
							</td>
						</tr>
						<tr>
							<td>조회수</td>
							<td colspan="2">
								<input type="text" name="product_hits" value="0" readonly="readonly" style="text-align: center;"/>
							</td>
						</tr>
						<tr>
							<td>이미지 파일</td>
							<td colspan="2">
								<input type="file" name="product_image" required="required" />
								
								<!-- onclick="changeValue(this.value);" onchange="readURL(this);" <img id="preview" />-->
								<!-- <input type="hidden" name="product_image1" value=""/> -->
							</td>
						</tr>
						
					
				</tbody>
			</table>
			
				<a href="showProductList.mgr" class="btn btn-primary">전체 상품 목록보기</a>
				<input type="reset" class="btn btn-primary pull-right" value="다시쓰기"/>
				<input type="submit"  class="btn btn-primary pull-right" value="제품등록"/><!-- formaction="insertProduct.mgr" -->
			</form>
		</div>
</body>
</html>