<div class="panel panel-default">

   <div class="panel-heading">
      <h3 class="panel-title"><?= $data['form_header'] ?></h3>
   </div>

   <div class="panel-body">

      <?php echo Message::show(); ?>

      <?php
         $data_row = $data['product_data'];
		$product = $data_row[0];
      ?>

      <form role="form" action="<?= DIR ?>products/update" method="POST">
			<input type="hidden" name="id" value="<?= $product['id'] ?>">
         <input class="form-control" type="text" name="name" placeholder="Produkt-Name" value="<?= $product['name'] ?>">
         <input class="form-control" type="text" name="location" placeholder="Koordinaten z.B. 52.4588048+13.5230717">
         <div class="row">
            <div class="col-xs-6 input-group">
               <input type="number" class="form-control" name="price" placeholder="Preis" value="<?= $product['price'] ?>">
               <span class="input-group-addon">€</span>
            </div>
            <div class="col-xs-6">
               <button type="submit" class="btn btn-primary btn-block">Aktualisieren</button>
            </div>
         </div>
         <input type="hidden" name="id" value="<?= $product['id'] ?>">
      </form>

	  
   </div> <!-- / .panel-body -->
</div> <!-- / .panel -->