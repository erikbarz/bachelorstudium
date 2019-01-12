<!-- Beginn Jumbotron -->
	<br>
	<br>
		<div class="container">
			<div class="jumbotron">
				<h3>Willkommen im Splendr-Produktboard
				</h3> 
				Folgende Features bietet die Seite:
				<li>Produktverwaltung
				</li>
				<li>Suchfunktion
				</li>
				<li>Benutzerverwaltung
				</li>
				<li>Bestätigungsmail mit Registrierungslink
				</li>
				<li>Maps
				</li>
			</div>
		</div><!-- Ende Jumbotron -->
		
		<div>
			<h3>Hier finden Sie unsere Produkte: </h3>
			<a href="https://www.google.com/maps/dir//berlin+deutschland/">
				<?php
					echo '<img src='. $data['map_url'] .' alt="Google Map für Produktstandorte">' ;
				?>
			</a>
		</div>
		<br>
		<br>
		
<div class="row list-group products">
	<h3>Produktübersicht</h3>

   <?php echo Message::show(); ?>

   <?php
      if ($data['is_deleted']=='true') {
         		 
		 echo '<br><font color=green>Produkt mit der ID' . $data['deleted_product_id'] . ' wurde erfolgreich gelöscht!</font><br>';
      }
	  
	  if ($data['is_updated']=='true') {
         		 
		 echo '<br><font color=green>Produkt mit der ID ' . $data['updated_product_id'] . ' wurde erfolgreich geändert!</font><br>';
      }
	  
	  if (!sizeof($data['products'])) {
         echo '<div class="alert alert-info">Derzeit gibt es keine Produkte. <a href="' . DIR . 'products/add">Leg gleich welche an</a>!</div>';
      }
      else {
         foreach ($data['products'] as $product) {
            echo
            '<div class="item col-xs-4">
               <div class="thumbnail">
                  <a href="' . $product['url'] . '" title="' . $product['name'] . '"><img src="' . $product['image'] . '" alt="Art.No:' . $product['id'] . '"></a>
                  <div class="buttons-edit">
                     <a class="btn btn-default btn-sm" href="' . DIR . 'products/update?id=' . $product['id'] . '">Edit</a>
                     <a class="btn btn-default btn-sm" href="' . DIR . 'products/delete?id=' . $product['id'] . '">Delete</a>
                  </div>
                  <div class="caption">
                     <h4><a href="' . $product['url'] . '" title="' . $product['name'] . '">' . $product['name'] . '</a></h4>
                     <span class="lead">' . $product['price'] . '€</span>
                  </div>
               </div>
            </div>';
         }
      }
   ?>

</div> <!-- / .products -->

<br>
<br>
<br>