<!doctype html>
<html>
<head>
   <meta charset="utf-8">
   <title><?= $data['title'] . ' - ' . SITETITLE ?></title>
   <link rel="stylesheet" href="<?= URL::STYLES('bootstrap.min') ?>">
   <link rel="stylesheet" href="<?= URL::STYLES('style') ?>">
   <meta name="author" content="Nadia Mißner">
   
   		<!-- 
			Kopiert aus: http://stackoverflow.com/questions/31119495/unable-to-connect-the-bootstrap-stylesheet 
			Bootstrap Importe
		-->
		<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
		<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css" />
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
   
</head>
<body>

   <div class="container">
		<!-- Beginn Header Navigation -->
      <nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active">
							<a href="index.html"><span class="glyphicon glyphicon-home"></span> Übersicht
							</a>
						</li>
					</ul>
					<!-- Beginn Suchfeld -->
					<form class="navbar-form navbar-left" role="search" method="get" action="<?= DIR ?>products/search">
						<div class="form-group">
							<input type="search" class="form-control" name="query" placeholder="Wonach suchen Sie?">
						</div>
						<button type="submit" class="btn btn-default">
							<span class="glyphicon glyphicon-search">
							</span>
						</button>
					</form><!-- Ende Suchfeld -->
					
					<!-- Beginn Drop-Down -->
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
								<span class="glyphicon glyphicon-user">
								
								<?php
								
								if ($data['user_logged_in'] == 'true'){
									echo '</span>  Aktionen für ' . $data['user_mail'];
								}else{
									echo '</span>  Aktionen';
								
								}
								
								
								?>
								
								
								
								
								<span class="caret">
								</span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<?= DIR ?>products/show_login_page">Login / Registrierung
									</a>
								</li>
								<li>
									<a href="<?= DIR ?>products/logout_user">Logout
									</a>
								</li>
								<li>
									<a href="<?= DIR ?>products/show_user_profile">Benutzerprofil
									</a>
								</li>
								
								<li role="separator" class="divider">
								</li>
								<li>
									<a href="<?= DIR ?>products/add">Produkt hinzufügen
									</a>
								</li>		
							</ul>
						</li>
					</ul><!-- Ende Drop-Down -->
				</div>
			</div>
		</nav><!-- Ende Navigation-Bereich -->
		<br><br>
		<!-- Beginn Content -->