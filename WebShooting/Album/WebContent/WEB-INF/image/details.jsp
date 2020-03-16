<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details Image</title>
<link rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>">
<script src="https://kit.fontawesome.com/7f5918d569.js"
	crossorigin="anonymous"></script>

</head>
<body>
	<nav class="navbar fixed-top navbar-expand-sm navbar-dark bg-dark">
		<a class="navbar-brand" href="<c:url value='/accueil'/>"><i>Web<i
				class="fa fa-picture-o" aria-hidden="true"></i>Shooting
		</i></a>
		<button class="navbar-toggler d-lg-none" type="button"
			data-toggle="collapse" data-target="#collapsibleNavId"
			aria-controls="collapsibleNavId" aria-expanded="false"
			aria-label="Toggle navigation" style="color: white;"></button>
		<div class="collapse navbar-collapse" id="collapsibleNavId">
			<ul class="navbar-nav ml-lg-auto">
				<li class="nav-item active"><a class="nav-link"
					href="<c:url value='/user/create'/>" style="color: white;"><i>Créer Compte</i></a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value='/user/connect'/>"
					style="color: white;"><i>Se Connecter</i></a></li>
			</ul>
		</div>
	</nav>
	<div class="container" style="margin-top: 70px;">
		<div class="row">
			<div class="col-md-5">
				<img alt=""
					src="<c:url value='/imagesuploaded/${ requestScope.imageDetails.url }'/>"
					style="width:100%; heigth:auto;">
			</div>
			<div class="col-md-5">
			<b class="text-dark">Titre:</b><br>
			<h4><c:out value="${requestScope.imageDetails.titre }"></c:out></h4>
			<b class="text-dark">Description:</b><br>
			<h4><c:out value="${requestScope.imageDetails.description }"></c:out></h4><br>
			<b class="text-dark">Date de Création:</b><br>
			<h4><c:out value="${requestScope.imageDetails.date_creation }"></c:out></h4><br>
			<b class="text-dark">Date de dernier mis à jour:</b><br>
			<h4><c:out value="${requestScope.imageDetails.date_update }"></c:out></h4><br>
			<b class="text-dark">Hauteur:</b><br>
			<h4><c:out value="${requestScope.imageDetails.hauteur }"></c:out></h4><br>
			<b class="text-dark">Largeur:</b><br>
			<h4><c:out value="${requestScope.imageDetails.largeur }"></c:out></h4>
			</div>
		</div>
	</div>
</body>
</html>