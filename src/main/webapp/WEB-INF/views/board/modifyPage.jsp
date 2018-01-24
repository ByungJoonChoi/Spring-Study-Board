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
					<h3 class="box-title">MODIFY BOARD</h3>
				</div>
				
				<div class="box-body">
					<form role="form" action="modifyPage" method="post">
						
						<input type="hidden" name="page" value="${cri.page }">
						<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
						
						<div class="form-group">
							<label for="exampleInputEmail1">Bno</label>
							<input type="text" name="bno" class="form-control" value="${boardVO.bno }" readonly="readonly">
						</div>
					
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label>
							<input type="text" name="title" class="form-control" value="${boardVO.title }">
						</div>
						
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea name="content" class="form-control" rows="3" >${boardVO.content }</textarea>
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1">Writer</label>
							<input type="text" name="writer" class="form-control" value="${boardVO.writer }" readonly="readonly">
						</div>
					</form>
				</div>
				
				<div class="box-footer">
					<button type="submit" id="btnSave" class="btn btn-danger">Save</button>
					<button type="submit" id="btnCancel" class="btn btn-primary">Cancel</button>
				</div>
				
			</div>
		</div>
	</div>	
</section>
<script>
$(document).ready(function(){
	let formObj = $("form[role='form']");
	
	$("#btnSave").on("click", function(){
		formObj.submit();
	})
	
	$("#btnCancel").on("click", function(){
		window.location="/board/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}";
	})
})
</script>

<%@include file="../include/footer.jsp" %>


