<!DOCTYPE html>

<%@ page import="java.sql.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int index = 0;
%>

<html lang="es">
<HEAD>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Royal Academy</title>

<!-- Bootstrap core CSS -->
<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">

<link href="/css/listaexamenes.css" rel="stylesheet">



</HEAD>

<BODY>
	<input type="hidden" id="cursada" value='${cursada}' />
	<input type="hidden" id="turno" value='${turno}' />

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="/">Inicio</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/Login">Ingresar</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#services">Alumnos</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/Examen/select">Examenes</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/PreguntaMC/add">Pregunta VF</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="/PreguntaVF/add">Pregunta MC</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div>




		<div class="page-container">
			<h3 class="title">Notas totales</h3>



			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Alumno</th>
						<th scope="col">Documento</th>
						<th scope="col">Nota obtenida</th>
					</tr>
				</thead>
				<tbody id="preguntas">

					<c:forEach items="${notas}" var="nota">
						<tr>
							<td><%=index%></td>
							<td>${nota.getPersona().getApellido()}</td>
							<td>${nota.getPersona().getNumeroDocumento()}</td>
							<td>${nota.getNota()}</td>
						</tr>
					</c:forEach>
					
							<%index++;%>
						
				</tbody>
			</table>
			<div class="buttons-container">
				<a class="btn btn-primary" id="submit">Volver</a>
			</div>
		</div>



		<footer class="py-5 bg-dark">
			<div class="container">
				<p class="m-0 text-center text-white">Copyright &copy; Royal
					Academy 2019</p>
			</div>
			<!-- /.container -->
		</footer>


	</div>

	<!-- Footer -->

	<!-- Bootstrap core JavaScript -->
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>

	<!-- Custom JavaScript for this theme -->
	<script src="/js/manual-exam.js"></script>

</BODY>
</HTML>
