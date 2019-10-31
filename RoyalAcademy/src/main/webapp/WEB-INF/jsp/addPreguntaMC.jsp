<!DOCTYPE html>
<html lang="en">
<head>
<title>Agregar pregunta multiple choice</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap core JavaScript -->
<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
<link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/css/form.css" rel="stylesheet">
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
		<h2>Agregar pregunta multiple choice</h2>
		<form method="post">

			<div class="form-group">
				<label for="pregunta">Pregunta:</label> <input type="text"
					class="form-control" id="pregunta" placeholder="Ingresar pregunta"
					name="pregunta">
			</div>

			<div class="form-row align-items-center">
				<div class="col-auto">
					 <label for="valorCorrecto">Opcion 1:</label> <input type="text"
						class="form-control" id="valorCorrecto"
						placeholder="Ingresar valor correcto" name="valorCorrecto">
						
						<label class="checkboxLabel" for="opcion1">Correcta</label>
						<input type="checkbox" id="opcion1"/>
				</div>
				<div class="col-auto" >
					 <label for="valorCorrecto">Opcion 2:</label> <input type="text"
						class="form-control" id="valorCorrecto"
						placeholder="Ingresar valor correcto" name="valorCorrecto">
						<label class="checkboxLabel" for="opcion2">Correcta</label>
						<input type="checkbox" id="opcion2"/>
				</div>
				<div class="col-auto">
					 <label for="valorCorrecto">Opcion 3:</label> <input type="text"
						class="form-control" id="valorCorrecto"
						placeholder="Ingresar valor correcto" name="valorCorrecto">
						<label class="checkboxLabel" for="opcion3">Correcta</label>
						<input type="checkbox" id="opcion3"/>
				</div>
				<!-- <div class="col-auto">
					 <label for="valorCorrecto">Opcion 4: </label> <input type="text"
						class="form-control" id="valorCorrecto"
						placeholder="Ingresar valor correcto" name="valorCorrecto">
						<label for="opcion4">Correcta</label>
						<input type="checkbox" id="opcion4"/>
				</div> -->
			</div>


			<button type="submit" class="btn btn-default">Agregar</button>
		</form>
</body>
</html>