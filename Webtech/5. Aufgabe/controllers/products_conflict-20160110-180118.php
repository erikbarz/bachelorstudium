<?php

class Products extends Controller {

   public function __construct() {
      parent::__construct();
   }

   // Übersicht Seite aller Produkte
   public function index() {
      $data['title'] = 'Übersicht';
	  // alle Produkte aus Model laden
      $product_table=$this->_model->load_All_Products();
		$data['products'] =$product_table;
		
		// lokale Variable der Methode übergeben und Karte zsm bauen 
		$map_url=$this->build_map_url($product_table);
		//  URL für Karte in data(wird der View übergeben) einspeichern
	   $data['map_url'] =$map_url;

	  // is_logged_in aufrufen, ggf Name in Header übergeben
	  $data['user_logged_in'] = $this->_model->is_logged_in();
	  $data['user_mail'] = $this->_model->get_logged_in_user_name();
	  	  
      $this->_view->render('header', $data);
      $this->_view->render('products/list', $data);
	  $this->_view->render('footer');
   }

   // Formular-Seite für das Hinzufügen neuer Produkte
    public function add() {
      $data['title'] = 'Neues Produkt';
      $data['form_header'] = 'Neues Produkt anlegen';
	  $data['is_initial'] = 'true';
	  
	  // is_logged_in aufrufen, ggf Name in Header übergeben
	  $data['user_logged_in'] = $this->_model->is_logged_in();
	  $data['user_mail'] = $this->_model->get_logged_in_user_name();
	  
      $this->_view->render('header', $data);
	  //add Seite wird hier initial gerufen (ohne vorherige insert Operation)
      $this->_view->render('products/add', $data);
      $this->_view->render('footer');
   }
   
   // Ergebniseite nach dem Hinzufügen neuer Produkte
   public function insert() {
	  //Variablen auslesen aus Request
	  $product_name = $_POST['name'];
	  $product_price = $_POST['price'];
	  $product_location = $_POST['location'];
	  
	  // Variablen an Model übergeben
	  $this->_model->insert_new_product($product_name, $product_price, $product_location);
	  
	  $data['is_initial'] = 'false';
	  
	  // is_logged_in aufrufen, ggf Name in Header übergeben
	  $data['user_logged_in'] = $this->_model->is_logged_in();
	  $data['user_mail'] = $this->_model->get_logged_in_user_name();
	  
      $this->_view->render('header', $data);
	  //add Seite wird hier NICHT initial gerufen (mit vorheriger insert Operation und Entsprechender Meldung)
      $this->_view->render('products/add', $data);
      $this->_view->render('footer');
   }
   

   public function search() {
      $data['title'] = 'Such-Ergebnisliste';
      
	  // Such Query aus Request ziehen
	  $search_query = $_GET['query'];

	  // model aufrufen um entsprechende ergebnisse aus db zu erhalten
	  $data['products'] = $this->_model->find_element_by_text($search_query);

	  // is_logged_in aufrufen, ggf Name in Header übergeben
	  $data['user_logged_in'] = $this->_model->is_logged_in();
	  $data['user_mail'] = $this->_model->get_logged_in_user_name();
	  
      $this->_view->render('header', $data);
	  $this->_view->render('products/search', $data);
      $this->_view->render('footer');
   }
   
   public function delete() {
      $data['title'] = 'Produkt gelöscht';
      
	  //ID aus Request ziehen (get)
	  $product_id = $_GET['id'];
	  
	  // id an model übergeben
	 $product_table=$this->_model->load_All_Products();
		$data['products'] =$product_table;
		
		// lokale Variable der Methode übergeben und Karte zsm bauen 
		$map_url=$this->build_map_url($product_table);
		//  URL für Karte in data(wird der View übergeben) einspeichern
	   $data['map_url'] =$map_url;
	  
	   // alle Produkte aus Model laden
      $data['products'] = $this->_model->load_All_Products();
	  $data['is_deleted'] = 'true'; //flag
	  $data['deleted_product_id'] = $product_id;
	  
	  // is_logged_in aufrufen, ggf Name in Header übergeben
	  $data['user_logged_in'] = $this->_model->is_logged_in();
	  $data['user_mail'] = $this->_model->get_logged_in_user_name();
	  
      $this->_view->render('header', $data);
      // Seite anpassen!
	  $this->_view->render('products/list', $data);
      $this->_view->render('footer');
   }
   
   public function update() {
      // in dieser Methoden werden beide Schritte behandelt: Formulardaten ziehen, geänderte Daten aufschreiben
	  // Step ermitteln: aus Post Product_id ziehen 
	  $is_step1 = isset($_GET['id']);
	  // Fallunterscheidung: wenn  keine product_id vorhanden, dann step 1 Formulardaten ziehen
		
	if ( $is_step1=='true') {
         //Vorfüllen des Formulars
		 
		 $data['title'] = 'Produkt anpassen';
		 $data['form_header'] = 'Produkt anpassen';
		 		  
		   //ID aus Request ziehen (get)
		  $product_id = $_GET['id'];
		  
		  // id an model übergeben --> Prduktdetails ziehen und der view übergeben
		   $data['product_data'] = $this->_model->load_Product_By_Id($product_id);
		  
		  // is_logged_in aufrufen, ggf Name in Header übergeben
			$data['user_logged_in'] = $this->_model->is_logged_in();
			$data['user_mail'] = $this->_model->get_logged_in_user_name();
	  
		  $this->_view->render('header', $data);
		  $this->_view->render('products/update', $data);
		  $this->_view->render('footer');		 
     }else {
	 // else product_id vorhanden ist, dann step 2 geänderte Daten übertagen
		  
		  
		  $data['title'] = 'Produkt angepasst';
		  
		  // ID, Produktname und Preis aus Request ziehen (post)
		  $product_id = $_POST['id'];
		  $product_name = $_POST['name'];
		  $product_price = $_POST['price'];
		  $product_location = $_POST['location'];
		  
		  // id, produktname und preis an model übergeben
		  $this->_model->update_product($product_id, $product_name, $product_price, $product_location);
		  
		   // alle Produkte aus Model laden
			$product_table=$this->_model->load_All_Products();
		$data['products'] =$product_table;
		
		// lokale Variable der Methode übergeben und Karte zsm bauen 
		$map_url=$this->build_map_url($product_table);
		//  URL für Karte in data(wird der View übergeben) einspeichern
	   $data['map_url'] =$map_url;
		  
		  $data['is_updated'] = 'true'; //flag
		  $data['updated_product_id'] = $product_id;
		  
		  // is_logged_in aufrufen, ggf Name in Header übergeben
			$data['user_logged_in'] = $this->_model->is_logged_in();
			$data['user_mail'] = $this->_model->get_logged_in_user_name();
	  
		  $this->_view->render('header', $data);
		  $this->_view->render('products/list', $data);
		  $this->_view->render('footer');
	 }
	}
   
   
    public function show_login_page() {
      $data['title'] = 'Login -  Registrierung';
      
	  // is_logged_in aufrufen, ggf Name in Header übergeben
	  $data['user_logged_in'] = $this->_model->is_logged_in();
	  $data['user_mail'] = $this->_model->get_logged_in_user_name();
	  
      $this->_view->render('header', $data);
	  $this->_view->render('products/login_registration', $data);
      $this->_view->render('footer');
   }
   
   public function login_user() {
      
      //Variablen auslesen aus Request
	  $mail = $_POST['mail'];
	  $password = $_POST['password'];
	  
	  // Variablen an Model übergeben und Prüfergebnis zurück bekommen
	  $login_successful = false;
		$login_successful = $this->_model->login_user($mail, $password);
	  
	  //Fallunterscheidung je nachdem, ob LOgin erfolgreich der nicht
	  if ( $login_successful=='true') {
        $data['title'] = 'Übersicht';
				
		// falls erfolgreich, Name in Header übergeben
		$data['user_mail'] = $mail;
		$data['user_logged_in'] = 'true';
	  
		// alle Produkte aus Model laden
		$product_table=$this->_model->load_All_Products();
		$data['products'] =$product_table;
		
		// lokale Variable der Methode übergeben und Karte zsm bauen 
		$map_url=$this->build_map_url($product_table);
		//  URL für Karte in data(wird der View übergeben) einspeichern
	   $data['map_url'] =$map_url;
	  
		// Übersicht Seite aufbauen
		$this->_view->render('header', $data);
		$this->_view->render('products/list', $data);
		$this->_view->render('footer');
		
     }else {
		$data['title'] = 'Login - Registrierung';
				
		// falls nicht erfolgreich, Nachricht auf login_registrtion erscheinen lassen
      	$data['is_login_failed'] = 'true'; //flag
		
		// Login Seite aufbauen
		$this->_view->render('header', $data);
		$this->_view->render('products/login_registration', $data);
		$this->_view->render('footer');

	 } 	 
	 
   }
   
   public function logout_user() {
      $data['title'] = 'Login -  Registrierung';
      	  
	  // logout_user im Model aufrufen
	  $this->_model->logout_user();
	  
	  // alle Produkte aus Model laden
		$product_table=$this->_model->load_All_Products();
		$data['products'] =$product_table;
		
		// lokale Variable der Methode übergeben und Karte zsm bauen 
		$map_url=$this->build_map_url($product_table);
		//  URL für Karte in data(wird der View übergeben) einspeichern
	   $data['map_url'] =$map_url;
	   
      $this->_view->render('header', $data);
	  //TODO Bild für Karte anzeigen
	  $this->_view->render('products/list', $data);
      $this->_view->render('footer');
   }
   
	public function build_map_url($product_table) {
		// http://staticmapmaker.com/google/
		$map_url="http://maps.googleapis.com/maps/api/staticmap?autoscale=false&size=600x300&maptype=roadmap&format=png&visual_refresh=true" ;
		
		foreach ($product_table as $product) {
			// falls location vorhanden, location auslesen
			if (! $product['location']=='') {
				// für jedes Produkt einene Marker zsm bauen
				$map_url .= "&markers=size:mid%7Ccolor:0xff0000%7Clabel:". $product['id'] ."%7C" . $product['location'];
             }
         }
		// die fertige url per return übergeben 
		return $map_url;
   }
   
   
   public function add_new_user() {
      $data['title'] = 'Login -  Registrierung';
      // Variablen auslesen aus Request
	  $firstname = $_POST['firstname'];
	  $lastname = $_POST['lastname'];
	  $mail = $_POST['mail'];
	  $password = $_POST['password'];
	  
	  //insert_new_user im Model aufrufen
	  $this->_model->insert_new_user($firstname, $lastname, $mail, $password);
	  
	  // Mail für Freischaltung schreiben
	  // http://php.net/manual/de/function.mail.php
		$subject = "! TEST ! Freischaltung - Splendr Produkt Board";
		$from = "From: Splendr <noreply@splendr.de>\n";
		$from .= "Content-Type: text/html\n";
		$text = "Hallo " . $firstname . ",<br>Bitte klicke <a href=http://localhost/aufgabe_5_mvc_mit_php/products/activate_user?userid=" . $mail . ">hier</a>, um deinen Account freizuschalten. <br>Viele Grüße,<br>Dein Splendr Support Team";
		mail($mail, $subject, $text, $from);
	  
	  
	  $data['is_registred'] = 'true';
	  $data['firstname'] = $firstname;
	  
	  // is_logged_in aufrufen, ggf Name in Header übergeben
	  $data['user_logged_in'] = $this->_model->is_logged_in();
	  $data['user_mail'] = $this->_model->get_logged_in_user_name();
	  	  
      $this->_view->render('header', $data);
	  // Nachricht: erfolgreich registriert, bitte jetzt einloggen
	  $this->_view->render('products/login_registration', $data);
      $this->_view->render('footer');
   }
   
   
	public function activate_user() {
     $user_id = $_GET['userid'];
		  
	  // logout_user im Model aufrufen
	  $this->_model->activate_user($user_id);
	  
	  // Nachricht: erfolgreich aktiviert, bitte jetzt einloggen
	  $data['is_activated'] = 'true';
	  
	  $this->_view->render('header', $data);	  
	  $this->_view->render('products/login_registration', $data);
      $this->_view->render('footer');
   }
   
   public function show_user_profile() {
      $data['title'] = 'Benutzerprofil';
	  // alle Produkte aus Model laden
      //TODO $product_table=$this->_model->load_All_Products();
// TODO $data['user_details'] =$product_table;
		
		// lokale Variable der Methode übergeben und Karte zsm bauen 
		// TODO $map_url=$this->build_map_url($product_table);
		//  URL für Karte in data(wird der View übergeben) einspeichern
	   //TODOOO $data['map_url'] =$map_url;

	  // is_logged_in aufrufen, ggf Name in Header übergeben
	  // $data['user_logged_in'] = $this->_model->is_logged_in();
	  //$data['user_mail'] = $this->_model->get_logged_in_user_name();
	  	  
      $this->_view->render('header', $data);
      $this->_view->render('products/user_profile', $data);
	  $this->_view->render('footer');
   }
   
}