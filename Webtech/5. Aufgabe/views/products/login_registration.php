		<br>
		<br>
		<?php
		if ($data['is_registred']=='true') {         		 
		 echo '<br><font color=green> Der Benutzer mit dem Namen ' . $data['firstname'] . ' wurde erfolgreich gespeichert!</font><br>';
		}
	  
		if ($data['is_login_failed']=='true') {         		 
		 echo '<br><font color=red> Ihre E-Mail oder das Passwort ist nicht korrekt. Oder Ihr User ist noch nicht freigeschaltet.</font><br>';
		}
		
		if ($data['is_activated']=='true') {         		 
		 echo '<br><font color=green> Ihr Account wurde erfolgreich freigeschaltet. Sie können sich nun anmelden :-)</font><br>';
		}
	  
	  
	  ?>
	  
		<div class="container">
			<h2> Login </h2>
			<form role="form" action="<?= DIR ?>products/login_user" method="POST" id="frmLogin">
			<!-- Formular Anmeldung Beginn -->	
				<div class="form-group" id="frmGroupUsername">
					<label for="eingabefeldUsername" class="col-sm-2 control-label">E-Mail
					</label>
					<div class="col-sm-6">
						<input type="username" class="form-control" id="eingabefeldUsername" name="mail" placeholder="E-Mail">
					</div>
				</div>
				<br>
				<br>
				<br>
				<div class="form-group" id="frmGroupPassword">
					<label for="eingabefeldPassword" class="col-sm-2 control-label">Passwort
					</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="eingabefeldPassword" name="password" placeholder="Passwort">
					</div>
				</div>
				<br>
				<br>
				<div class="form-group" id="frmGroupLoginBtn">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="loginBtn" class="btn btn-default" id="loginBtn">Login<!-- class "btn btn-default" legerer Button-->
						</button> 
					</div>
				</div>
			</form> <!-- Formular Anmeldung Ende -->
		</div>
		<hr>
		<br>
		<div class="container">
			<h2> Registrierung </h2>
			<form role="form" action="<?= DIR ?>products/add_new_user" method="POST" id="frmRegistration"><!-- Formular Registrierung Beginn -->				
				<div class="form-group" id="frmGroupFirstname">
					<label for="eingabefeldFirstname" class="col-sm-2 control-label">Vorname *
					</label>
					<div class="col-sm-6">
						<input type="firstname" class="form-control" id="eingabefeldFirstname" name="firstname" placeholder="Vorname">
					</div>
				</div>
				<br>
				<br>
				<br>
				<div class="form-group" id="frmGroupLastname">
					<label for="eingabefeldLastname" class="col-sm-2 control-label">Nachname *
					</label>
					<div class="col-sm-6">
						<input type="lastname" class="form-control" id="eingabefeldLastname" name="lastname" placeholder="Nachname">
					</div>
				</div>
				<br>
				<br>
				<div class="form-group" id="frmGroupEmail">
					<label for="eingabefeldEmail" class="col-sm-2 control-label">Email *
					</label>
					<div class="col-sm-6">
						<input type="email" class="form-control" id="eingabefeldEmail" name="mail" placeholder="E-Mail">
					</div>
				</div>
				<br>
				<br>
				<input class="form-control" type="text" name="address" placeholder="Wohnort">
				<br>
				<br>
				
				<div class="form-group" id="frmGroupPassword">
					<label for="eingabefeldPassword" class="col-sm-2 control-label">Passwort *
					</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="eingabefeldPassword" name="password" placeholder="Passwort">
					</div>
				</div>
				<br>
				<br>
				<br>
				<div class="form-group" id="frmGroupRegistrationBtn">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="registration" class="btn btn-primary btn-lg" id="registrationBtn">Registrieren
						</button> 
					</div>
				</div>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				
			</form> <!-- Formular Registrierung Ende -->
		</div>
		
