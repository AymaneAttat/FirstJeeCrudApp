<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Style</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="header.jsp" %>
	<p></p>
	<div class="container">
		<div class="card">
			<div class="card-header">
				Confirmation Ajouter/Modifier Produit
			</div>
			<div class="card-body">
				<div class="form-group">
					<label class="control-label">ID :</label>
					<input type="text" name="Nom" class="form-control" value="${produit.idProduit }"/>
					<label class="control-label">Nom Produit :</label>
					<input type="text" name="Nom" class="form-control" value="${produit.nomProduit }"/>
				</div>
				<div class="control-label">
					<label class="control-label">Prix :</label>
					<input type="text" name="Prix" class="form-control" value="${produit.prix }"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>