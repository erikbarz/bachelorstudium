
		
<div class="row list-group products">
	<br><br>
	<h3 class="panel-title">Such-Ergebnisse:</h3><br>

   <?php echo Message::show(); ?>

   <?php
      if (!sizeof($data['products'])) {
         echo '<div class="alert alert-info">Es wurden keine Produkte gefunden. Bitte suche mit anderen Kriterien</div>';
      }
      else {
         foreach ($data['products'] as $product) {
            echo
            '<div class="item col-xs-4">
               <div class="thumbnail">
                  <a href="' . $product['url'] . '" title="' . $product['name'] . '"><img src="' . $product['image'] . '" alt="Art.No:' . $product['id'] . '"></a>
                  <div class="buttons-edit">
                     <a class="btn btn-default btn-sm" href="' . DIR . 'products/edit?id=' . $product['id'] . '">Edit</a>
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