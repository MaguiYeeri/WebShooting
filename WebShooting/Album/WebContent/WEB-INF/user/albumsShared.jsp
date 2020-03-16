<%@include file="menu.jsp"%>
<div class="container" style="margin-top: 70px;">
	
	<div class="row">
		<div class="col-md-2">
		
			
					
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
							<td><a class="btn btn-dark" href="<c:url value='albumsShared/voir?id=${album.id}'/>"><i class="fa fa-eye" aria-hidden="true"></i>Voir</a></td>
								
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:otherwise>
			</c:choose>
		</div>


<script type="text/javascript"
	src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script src="https://kit.fontawesome.com/7f5918d569.js" crossorigin="anonymous"></script>
</body>
</html>