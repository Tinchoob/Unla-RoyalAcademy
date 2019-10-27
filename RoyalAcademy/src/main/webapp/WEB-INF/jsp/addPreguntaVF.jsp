<!DOCTYPE html>
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
  <link href="/css/form.css" rel="stylesheet">
<script href="/webjars/bootstrap/4.0.0/js/bootstrap.min.js">
	
</script>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
  <div class="container">
    <a class="navbar-brand js-scroll-trigger" href="/">Inicio</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" href="Login">Ingresar</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" href="#services">Alumnos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" href="#contact">Contact</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">
<h2>Agregar pregunta verdadero o falso</h2>
<form method="post">

<div class="form-group">
<label for="pregunta">Pregunta:</label> <input type="text"
	class="form-control" id="pregunta" placeholder="Ingresar pregunta"
	name="pregunta">
</div>

	<div class="form-group">
	<label for="valorCorrecto">Valor correcto:</label> <input type="text"
		class="form-control" id="valorCorrecto" placeholder="Ingresar valor correcto"
		name="valorCorrecto">
</div>


<button type="submit" class="btn btn-default">Agregar</button>
</form>
</body>
</html>