<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp" %>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				
				<form role="form" action="/modifyPage" method="post">
					<input type="hidden" name="bno" value="${boardVO.bno}">
					<input type="hidden" name="page" value="${cri.page}">
					<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
				</form>
				
				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label>
						<input type="text" name="title" class="form-control" value="${boardVO.title }" readonly="readonly">
					</div>
					
					<div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea name="content" class="form-control" rows="3" readonly="readonly">${boardVO.content }</textarea>
					</div>
					
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label>
						<input type="text" name="writer" class="form-control" value="${boardVO.writer }" readonly="readonly">
					</div>
				</div>
				
				<div class="box-footer">
					<button type="submit" class="btn btn-warning" id="btnModify">Modify</button>
					<button type="submit" class="btn btn-danger" id="btnRemove">REMOVE</button>
					<button type="submit" class="btn btn-primary" id="btnGoList">GO LIST</button>
				</div>
				
			</div>
		</div>
	</div>	
</section>
<script>
$(document).ready(function(){
	
	let formObj = $("form[role='form']");
	console.log(formObj);
	
	$("#btnModify").on("click", function(){
		formObj.attr("action", "/board/modify");
		formObj.attr("method", "get");
		formObj.submit();
	});
	
	$("#btnRemove").on("click", function(){
		formObj.attr("action", "/board/removePage");
		formObj.submit();
	});
	
	$("#btnGoList").on("click", function(){
		formObj.attr("action", "/board/listPage");
		formObj.attr("method", "get");
		formObj.submit();
	});
	
});

</script>

<%@include file="../include/footer.jsp" %>


