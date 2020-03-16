<%@include file="menu.jsp"%>
<div class="container" style="margin-top: 70px;">
	
	<div class="row">
		<div class="col-md-2">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-dark btn-md"
				data-toggle="modal" data-target="#modelAlbum">
<i class="fas fa-plus"></i> Album
			</button>

			<!-- Modal -->
			<div class="modal fade" id="modelAlbum" tabindex="-1" role="dialog"
				aria-labelledby="modelTitleId" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<form method="POST">
							<div class="modal-header">
								<h5 class="modal-title">Ajouter un album</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="container-fluid">

									<div class="form-group">
										<label for="">Nom:</label> <input type="text" name="nomAlbum"
											id="" class="form-control" placeholder="" required="required">
									</div>
									<div class="form-group">
										<label for="">Type</label> <select class="form-control"
											name="type" id="">
											<option>PUBLIC</option>
											<option>PRIVATE</option>
										</select>
									</div>
									<div class="form-group">
										<label for="">Theme</label> <select class="form-control"
											name="theme" id="">
											<option>Vacances</option>
											<option>Sport</option>
											<option>Loisir</option>
											<option>Sortie</option>
											<option>Autres</option>
										</select>
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
		<div class="col-md-10">
			<h4>Liste Albums</h4>
			<hr>
			<c:choose>
			<c:when test="${ empty requestScope.albums }">
				<p>La liste des albums est vide</p>
			</c:when>
			<c:otherwise>
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th colspan="3">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.albums}" var="album">
						<tr>
							<td scope="row"><i class="fa fa-folder-o" aria-hidden="true"></i> <c:out value='${album.nom }'/></td>
							<td><a class="btn btn-dark" href="<c:url value='album/voir?id=${album.id}'/>"><i class="fa fa-eye" aria-hidden="true"> Voir</i></a></td>
							<td><a class="btn btn-warning" href="<c:url value='album/modifier?id=${album.id}'/>"><i class="fa fa-pencil" aria-hidden="true"></i>Modifier</a></td>
							<td><a class="btn btn-danger" 	href="<c:url value='album/supprimer?id=${album.id}'/>"><i class="fa fa-trash" aria-hidden="true"></i>Supprimer</a></td>	
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:otherwise>
			</c:choose>
		</div>
	</div>

</div>

<script type="text/javascript"
	src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script src="https://kit.fontawesome.com/7f5918d569.js" crossorigin="anonymous"></script>
</body>
</html>