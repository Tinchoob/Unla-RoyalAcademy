<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ResultSet resultset = null;
%>

<html lang="en">
<head>
<title>Agregar pregunta verdadero o falso</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap core JavaScript -->
<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/css/addPreguntaVF.css" rel="stylesheet">
<script href="/webjars/bootstrap/4.0.0/js/bootstrap.min.js">
	
</script>
</head>
<body>
	<!-- Navigation -->
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
						href="Login">Ingresar</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#services">Alumnos</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#contact">Contact</a></li>
				</ul>
			</div>
		</div>
	</nav>



	<div class="container">

		<%
			try {
				//Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connection = DriverManager
						.getConnection("jdbc:mysql://localhost/bd_royal_academy?user=admin&password=1234");

				Statement statement = connection.createStatement();

				resultset = statement.executeQuery("select idMateria,nombre from materia");
		%>

		<h2>Agregar pregunta verdadero o falso</h2>
		<form method="post">

			<div class="form-group">
				<label for="pregunta">Pregunta:</label> <input type="text"
					class="form-control" id="pregunta" placeholder="Ingresar pregunta"
					name="pregunta">
			</div>

			<div class="form-check form-check-inline" id="container">
				<input type="checkbox" class="form-check-input" id="true"> <label
					class="form-check-label" for="true">Verdadero</label> <input
					type="checkbox" class="form-check-input" id="false"> <label
					class="form-check-label" for="false">Falso</label> 
					
					
					<select	class="materia">

					<option value="">Seleccionar</option>
					<%
						while (resultset.next()) {
					%>
					<option value=<%=resultset.getString(1)%>><%=resultset.getString(2)%></option>

					<%
						}
					%>
				</select>
			</div>


			<%
				//**Should I input the codes here?**
				} catch (Exception e) {
					out.println("wrong entry" + e);
				}
			%>

			<button type="submit" id="submit" class="btn btn-default">Agregar</button>
		</form>

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
	<script src="/js/addPreguntaVF.js"></script>
</body>
</html>