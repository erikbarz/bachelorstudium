<div class="panel panel-default">

   <div class="panel-heading">
      <h3 class="panel-title"><?= $data['form_header'] ?></h3>
   </div>

   <div class="panel-body">

      <?php echo Message::show(); ?>
	  
      <?php
	  if (($data['is_initial']) == 'false'){
		echo '<br><font color=green>Produkt wurde erfolgreich in DB angelegt<br>';		
	  }
      ?>
      	  
	  <form role="form" action="<?= DIR ?>products/insert" method="POST">
         <input class="form-control" type="text" name="name" placeholder="Produkt-Name">
         <!-- <input class="form-control" type="url" name="url" placeholder="Produkt-URL"> -->
         <input class="form-control" type="text" name="location" placeholder="Koordinaten z.B. 52.4588048+13.5230717">
         <div class="row">
            <div class="col-xs-6 input-group">
               <input type="number" class="form-control" name="price" placeholder="Preis">
               <span class="input-group-addon">€</span>
            </div>
            <div class="col-xs-6">
               <button type="submit" class="btn btn-primary btn-block">Produkt anlegen</button>
            </div>
         </div>
      </form>

   </div> <!-- / .panel-body -->
</div> <!-- / .panel -->