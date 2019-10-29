<!DOCTYPE html>

<%@ page import="java.sql.*"%>
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
						href="Login">Ingresar</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#services">Alumnos</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="Examen">Examenes</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="page-container">


		<%
			try {
				//Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connection = DriverManager
						.getConnection("jdbc:mysql://localhost/bd_royal_academy?user=admin&password=1234");

				Statement statement = connection.createStatement();

				resultset = statement.executeQuery("select * from cursada");

				Statement statement2 = connection.createStatement();
				resultTurno = statement2.executeQuery("select * from turno");
		%>

		<div>
			<h3>Seleccione la cursada</h3>
			<select class="cursada">
				<%
					while (resultset.next()) {
				%>
				<option><%=resultset.getString(2)%></option>
				<%
					}
				%>
			</select>
			<div class="buttons-container">

				<h3>Seleccione el turno</h3>
				<select class="turno">
					<%
						while (resultTurno.next()) {
					%>
					<option><%=resultTurno.getString(2)%></option>
					<%
						}
					%>
				</select>
			</div>

			<div class="buttons-container">
				<h3>Seleccione tipo de examen</h3>
				<input type="radio" name="automatico" value="automatico">
				Automatico<br> <input type="radio" name="manual" value="manual">
				Manual<br>

			</div>

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
  <script src="/js/exam-selection.js"></script>

</BODY>
</HTML>
