<%@include file="menu.jsp"%>
<div class="container" style="margin-top: 70px;">
	<div class="col-md-2">
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-dark btn-md"
			data-toggle="modal" data-target="#modelImage">
			<i class="fas fa-plus"></i> Ajouter photo
		</button>

		<!-- Modal -->
		<div class="modal fade" id="modelImage" tabindex="-1" role="dialog"
			aria-labelledby="modelTitleId" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<form method="POST" enctype="multipart/form-data" action="">
						<div class="modal-header">
							<h5 class="modal-title">Ajouter une Image</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="container-fluid">

								<div class="form-group">
									<label for="">Titre:</label> <input type="text" name="titre"
										id="" class="form-control" placeholder="" required="required">
								</div>
								<div class="form-group">
									<label for="">Description:</label>
									<textarea name="description" id="" class="form-control"
										placeholder="" required="required"></textarea>
								</div>
								<div class="form-group">
									<label for="">Image:</label> <input type="file" name="url"
										id="" class="form-control" placeholder="" required="required">
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-dark">Save</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="row">

		<c:choose>
			<c:when test="${ empty requestScope.imagesAlbum }">
				<p>Cette album ne contient pas encore d'images</p>
			</c:when>
			<c:otherwise>
				<c:forEach items="${requestScope.imagesAlbum}" var="image">
					<div class="col-md-4">
						<div class="card">
							<img class="card-img-top image"
								src="<c:url value='/imagesuploaded/${image.url }'/>"
								alt="<c:out value='${image.titre }'></c:out>"
								style="width: auto; height: 400px;" 
								data-id="${image.url}"
								data-toggle="modal" data-target="#modelImg">
								<a class="btn btn-warning" href="<c:url value='images/modifier?id=${image.id}'/>"><i class="fa fa-pencil" aria-hidden="true"></i>Modifier</a>
							<a class="btn btn-danger" 	href="<c:url value='images/supprimer?id=${image.id}'/>"><i class="fa fa-trash" aria-hidden="true">Supprimer</i></a>	
						
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div>
</div>

<div class="modal fade" id="modelImg" tabindex="-1" role="dialog"
	aria-labelledby="modelTitleId" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			
			<div class="modal-body" id="imgModalBody">
				
			</div>
		</div>
	</div>
</div>
<script>
$(document).on("click", ".image", function (e){
	let url = $(e.target).closest("img").data("id");
    $('#imgModalBody').html('<img alt="test" src="<c:url value="/imagesuploaded/'+url+'"/>" style="width: 100%; height:auto">');    	        
});
</script>

</body>
</html>