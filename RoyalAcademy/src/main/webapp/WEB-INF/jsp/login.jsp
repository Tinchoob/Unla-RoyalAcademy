<head>
  <!-- Bootstrap core CSS -->
 <link href="/webjars/bootstrap/4.0.0/css/bootstrap.min.css"
        rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="/css/login.css" rel="stylesheet">
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
<br></br>
  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Ingresar al sitio</h5>
            <form action="/Login/menu" method="get">
              <div class="form-label-group">
                <input type="nombreUsuario" id="nombreUsuario" class="form-control" placeholder="nombreUsuario" required autofocus>
                <label for="nombreUsuario">Usuario</label>
              </div>

              <div class="form-label-group">
                <input type="password" id="contraseña" class="form-control" placeholder="contraseña" required>
                <label for="contraseña">Contraseña</label>
              </div>

              <div class="custom-control custom-checkbox mb-3">
                <input type="checkbox" class="custom-control-input" id="customCheck1">
                <label class="custom-control-label" for="customCheck1">Recordar contraseña</label>
              </div>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Ingresar</button>
              <hr class="my-4">
            </form>
          
         
            <form>
            <input type="button" class="btn btn-lg btn-primary btn-block" value="Volver a inicio" onclick="history.back()">
          </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>