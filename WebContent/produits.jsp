<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
	<title>New Style</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="header.jsp" %>
	<p></p>
	<div class="container">
		<div class="card">
			<div class="card-header">
				Recherche des Produits
			</div>
			<div class="card-body">
				<form action="chercher.do" method="get">
					<div class="input-group">
						<label class="input-group-text">Mot Cl�</label>
					  	<input type="text" name="motCle" value="${model.motCle}" class="form-control" />
					  	<button type="submit" class="btn btn-primary btn-sm">Chercher</button>
					</div>
				</form>
				<table class="table table-striped">
					<tr>
						<th>ID</th><th>Nom Produit</th><th>Prix</th><th>Supprimer</th><th>Editer</th>
					</tr>
					<c:if test="${ !empty prods }">
				        <c:forEach items="${prods}" var="prod">
							<tr>
								<td>${prod.idProduit }</td>
								<td>${prod.nomProduit }</td>
								<td>${prod.prix }</td>
								<td><a onclick="return confirm('Etes-vous s�r ?')" href="supprimer.do?id=${prod.idProduit }">Supprimer</a></td>
								<td><a href="editer.do?id=${prod.idProduit }">Edit</a></td>
							</tr>
						</c:forEach>
				    </c:if>
					<c:forEach items="${model.produits}" var="p">
					<tr>
						<td>${p.idProduit }</td>
						<td>${p.nomProduit }</td>
						<td>${p.prix }</td>
						<td><a onclick="return confirm('Etes-vous s�r ?')" href="supprimer.do?id=${p.idProduit }">Supprimer</a></td>
						<td><a href="editer.do?id=${p.idProduit }">Edit</a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>