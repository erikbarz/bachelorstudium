
		<br>
		<br>
		<br>
		<!-- Beginn Jumbotron -->
		<div class="container">
			<div class="jumbotron">
				<h2>Benutzerprofil</h2>
			</div>
		</div><!-- Ende Jumbotron -->
		
		<!-- Beginn Benutzerprofil -->
		<div class="container">
			<div class="row"  style="background-color: #eee;">
				<br>
				<div class="col-sm-4" title="Nadia Mißner">
						<a href=product_001.html>
							<img src="images/bewerbungsfoto.png" height=300px>
						</a>
				</div>
				<div class="form-group" id="frmGroupChangeName">
					<label for="changeNameBtn" class="col-sm-2 control-label">Nadia Mißner
					</label>
					<div class="row col-sm-6">
						<button type="changeName" class="btn btn-default" id="changeNameBtn">Namen ändern
					</button> 
					</div>
				</div>
				<div class="form-group" id="frmGroupChangePassword">
					<label for="changePasswordBtn" class="col-sm-2 control-label">**********
					</label>
					<div class="row col-sm-6">
						<button type="changePassword" class="btn btn-default" id="changePasswordBtn">Passwort ändern
					</button> 
					</div>
				</div>
				<div class="form-group" id="frmGroupChangeEmail">
					<label for="changeEmailBtn" class="col-sm-2 control-label">s0543959@htw-berlin.de
					</label>
					<div class="row col-sm-6">
						<button type="changeEmail" class="btn btn-default" id="changeEmailBtn">E-Mail ändern
					</button> 
					</div>
				</div>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<div class="form-group " id="frmGroupUpload">
					<form action="upload.php" method="post" enctype="multipart/form-data">
						Select new profil picture:
						<input type="file" name="fileToUpload" id="fileToUpload">
						<input type="submit" value="Upload Image" name="submit">
					</form>
				</div>
			</div>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		</div><!-- Ende Benutzerprofil -->
		