<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ResultSet resultset = null;
%>
<%
	ResultSet resultTurno = null;
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

<link href="/css/exam-selection.css" rel="stylesheet">



</HEAD>

<BODY>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="#page-top">Inicio</a>
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
	<input type="hidden" id="idExamen" value="${examen.getIdExamen()}" />
		<input type="hidden" id="documento" value="${documento}" />
	<div class="page-container">


		<div class="preguntas-mc-container">
			<c:forEach items="${preguntasMC}" var="item">
				<div class="pregunta-mc-row">
					${item.getPregunta()}<br>
				<input type="radio" name="opcion1v${item.getIdPregunta()}" value="opcion1"> ${item.getLstRespuestaMC().get(0).getRespuesta()}<br>
				<input type="radio" name="opcion2v${item.getIdPregunta()}" value="opcion2"> ${item.getLstRespuestaMC().get(1).getRespuesta()}<br>
				<input type="radio" name="opcion3v${item.getIdPregunta()}" value="opcion3"> ${item.getLstRespuestaMC().get(2).getRespuesta()}<br>
				<input type="hidden" name="idPregunta" class="idPregunta" id="idPregunta" value="${item.getIdPregunta()}" />
				<input type="hidden" name="valorcorrecto" class="valorcorrecto" id="valorcorrecto"  />
				</div>
			</c:forEach>
		</div>

		<div class="preguntas-vf-container">
			<c:forEach items="${preguntasVF}" var="item">
				<div class="pregunta-vf-row">
					${item.getPregunta()}<br> <input type="radio" name="verdaderov${item.getIdPregunta()}"
						value="verdadero"> Verdadero<br> <input type="radio"
						name="falsov${item.getIdPregunta()}" value="Falso"> Falso<br>
										<input type="hidden" name="idPregunta" class="idPregunta" id="idPregunta" value="${item.getIdPregunta()}" />
						<input type="hidden" name="valorcorrecto" class="valorcorrecto" id="valorcorrecto" />
				</div>
			</c:forEach>
		</div>


	<div class="buttons-container">
				<a class="btn btn-primary" id="submit">Enviar</a>
			</div>

	</div>

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Royal
				Academy 2019</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>

	<!-- Custom JavaScript for this theme -->
	<script src="/js/exam-view.js"></script>

</BODY>
</HTML>
