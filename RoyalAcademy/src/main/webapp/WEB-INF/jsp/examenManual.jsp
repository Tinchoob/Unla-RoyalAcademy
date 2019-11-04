<!DOCTYPE html>

<%@ page import="java.sql.*"%>
<%
	ResultSet resultset = null;
%>
<%
	int index = 1;
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

<link href="/css/manual-exam.css" rel="stylesheet">



</HEAD>

<BODY>
<input type="hidden" id="cursada" value='${cursada}'/>
<input type="hidden" id="turno" value='${turno}'/>

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

	<div>


		<%
			try {
				//Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connection = DriverManager
						.getConnection("jdbc:mysql://localhost/bd_royal_academy?user=admin&password=1234");

				Statement statement = connection.createStatement();

				resultset = statement.executeQuery("select idPregunta,pregunta from pregunta");
		%>

		<div class="page-container">
			<h3 class="title" >Examen Manual</h3>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Pregunta</th>
						<th scope="col">Incluir</th>
					</tr>
				</thead>
				<tbody id="preguntas">
					<%
						while (resultset.next()) {
					%>
					<tr class="table-row">
						<th class="idPregunta" scope="row"><%=resultset.getString(1) %></th>
						<td><%=resultset.getString(2)%></td>
						<td>
							<div class="checkbox">
								<input type="checkbox" name="check">
							</div>
						</td>
					</tr>
					<%
						index++;
							}
					%>

				</tbody>
			</table>
					<div class="buttons-container">
				<a class="btn btn-primary" id="submit">Aceptar</a>
			</div>
		</div>


		<%
			//**Should I input the codes here?**
			} catch (Exception e) {
				out.println("wrong entry" + e);
			}
		%>
		
	

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
