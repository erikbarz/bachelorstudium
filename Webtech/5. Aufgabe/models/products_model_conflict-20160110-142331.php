<?php

class Products_Model extends Model {


   public function __construct(){
      parent::__construct();
   }

   
   /**
   * Gibt die letzten 20 Einträge im Archiv zurück.
   * @return array Liste aus Produkten mit id, timestamp, name, url, image und price
   */
   public function load_All_Products() {
      return $this->_db->select('SELECT * FROM products ORDER BY id DESC LIMIT 0, 20');
   }

   
   public function find_element_by_text($search_text){
      return $this->_db->select("SELECT * FROM products WHERE name LIKE '%$search_text%' OR id like '%$search_text%' or price like '%$search_text%'");
   }
   
   
   public function delete_product_by_id($product_id) {
      $where = 'id = ' . $product_id;
	  return $this->_db->delete('products', $where, $limit = 1);
      //return $this->_db->delete('DELETE FROM products WHERE id = $product_id');
   }
   
   
    public function update_product($product_id, $product_name, $product_price, $product_location){
	 $data = array(
		'id' 	=> $product_id ,
		'name'  => $product_name ,                                 
		'price'     => $product_price  ,    
		'location'     => $product_location
		);
		
	 $where = "id='" . $product_id . "'";
	 
      return $this->_db->update('products', $data, $where);
   }
   
   
   public function load_Product_By_Id($product_id) {
      return $this->_db->select('SELECT * FROM products WHERE id = ' . $product_id);
   }
   
   
   public function insert_new_product($product_name, $product_price, $product_location){
	  // maximalen Index abfragen
	  $result = $this->_db->select('SELECT MAX(ID) as MAX FROM products');
 	  // erste Zeile aus result set holen
	  $first_Row = $result[0];
	  // neuen index setzen auf aktuelles Maximum + 1
	  $product_id = $first_Row['MAX'] + 1;
	  
	  // Tabelle mit Input Daten für insert Funktion schreiben
	  $insert_data = array(
		'id' => $product_id ,
		'name'  => $product_name ,                                 
		'price'     => $product_price ,
		'location'     => $product_location
		);
	  
	  return $this->_db->insert('products', $insert_data);
   }
   
   
   public function insert_new_user($firstname, $lastname, $mail, $password){
	    
	  // Tabelle mit Input Daten für insert Funktion schreiben
	  $insert_user_data = array(
		'mail' => $mail ,
		'password'  => $password ,                                 
		'firstname'     => $firstname ,        
		'lastname'     => $lastname  ,
		'activated'     => '0'
		);
	  
	  return $this->_db->insert('users', $insert_user_data);
   }
   
    
   
   public function login_user($mail, $password_from_user){
  
	  // pw für id holen aus db 
	  $login_result = $this->_db->select("SELECT password, activated FROM users WHERE mail LIKE '%$mail%'");
	   
	  $first_row = $login_result[0];
	  $password_from_db = $first_row['password'];
	  $is_user_activated = $first_row['activated'];
	  
	  // vergleich der passwörter und prüfen auf Freischaltung des Accounts
	  if($password_from_user == $password_from_db && $is_user_activated==true ){
		
		// cookie setzen, falls erfolgreich
		// http://www.php-einfach.de/experte/php/cookies/
		setcookie("logged_in_username",$mail,0); 
		
		//return des ergebnisses
		return 'true';
		
	  }else{
		//return des ergebnisses
		return 'false';
	  }	    
   }
   
   public function logout_user(){	 
	  // delete / invalidate cookie
	  // http://www.php-einfach.de/experte/php/cookies/
	  setcookie("logged_in_username","",time() - 3600);	  
   }
   
   public function is_logged_in(){	  
	  // versuchen user name aus cookie zu holen
	  // http://www.php-einfach.de/experte/php/cookies/
	  if($_COOKIE["logged_in_username"] == ''){
		return 'false';
	  }else{
		return 'true';
	  }	   	  
   }
   
   public function get_logged_in_user_name(){	  
	  // versuchen user name aus cookie zu holen
	  // http://www.php-einfach.de/experte/php/cookies/
	  return $_COOKIE["logged_in_username"]; 	  
   }
   
   
   public function activate_user($user_id){      
	  // Update des Datensatzes für das Feld activated
		$data = array(
		'activated' 	=> '1'                         
		);
		
		$where = "mail='" . $user_id . "'";
	 
		return $this->_db->update('users', $data, $where);
	}
	
	
   
}