
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//
//
//				Beginn Initialisierungs-Skript
//
//
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


$(function() {
   
   console.log("Initialisierung beginnt");
   
   // Global zugängliches Variable
   window.app = {};

   // url angepast 
   app.url = 'http://localhost/aufgabe_7_ajax_und_json/api/index.php/';
   
   // Template der App
   app.template = $('.products-template').val();
   // Objekt zur Kontrolle des Formulars
   app.form = new Form('.products-form');
   // Objekt zur Kontrolle der Produktliste
   app.productList = new Productlist('.products-list');
   
   // Initiales Laden aller vorhandenen Produkte
   app.productList.getAllProducts();
});




// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//
//
//				Beginn Form-Skript
//
//
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



/**
 * Kümmert sich um die Kontrolle des Formulars, also:
 *    - Anlegen neuer Produkte
 *    - Aktualisieren vorhandener Produkte
 * @param {Element} container HTML-Element der Produktliste
 */
var Form = function(container) {
	console.log("Form - Form Objekt erstellt");

	this.container = container;
	
	// Variable wird später gefüllt, sobald Button geklickt wurde
	this.transientProduct;
	   
};


Form.prototype.createNewProduct = function() {
   console.log("Form - Button erstellen geklickt");
   
   // Temp JSON Objekt aus Formular-Daten bauen
   this.createTransientProduct();
   
   // Aufruf der Produktliste
   app.productList.createNewProduct(this.transientProduct);  
};


Form.prototype.prepareUpdateProductStep1 = function(productId) {
   console.log("Form - Update Step1 (Daten von Produktliste laden)");
   
   // von Productlist Details des einen Produkts laden
   app.productList.getProduct(productId);   
};


Form.prototype.prepareUpdateProductStep2 = function() {
	console.log("Form - Update Step2 (Daten in Formular laden und Button Sichtbarkeit ändern)");

   // Produkt-Daten in Input-Felder schreiben
	$("#inputName").val(this.transientProduct.name);
	$("#inputUrl").val(this.transientProduct.url);
	$("#inputImage").val(this.transientProduct.image);
	$("#inputPrice").val(this.transientProduct.price);
	$("#inputId").val(this.transientProduct.id);
   
   // Botton anlegen ausblenden
	$(".btn-create").hide();
	// Button ändern einblenden
	$(".btn-update").show();   
};


Form.prototype.updateProduct = function() {
   console.log("Form - Button aktualisieren geklickt - Button Sichtbarkeit ändern");
   
   // Temp JSON Objekt aus Formular-Daten bauen
   this.createTransientProduct();
   
   // Aufruf der Produktliste
   app.productList.updateProduct(this.transientProduct); 

   // Botton anlegen einblenden
	$(".btn-create").show();
	// Button ändern ausblenden 
	$(".btn-update").hide();  	
};


Form.prototype.createTransientProduct = function() {
   console.log("Form - beginne parsen des JSON Objekts");
   
   // JSON Objekt aus Formular-Daten bauen und in Variable schreiben
   // mit jQuery die Inhalte aus Input-Fields ziehen
   // JQuery(#ID).WerteAuslesen
   var data = {
	   "name" : $("#inputName").val(),
	   "url" : $("#inputUrl").val(),
	   "image" : $("#inputImage").val(),
	   "price" : $("#inputPrice").val(),
	   "id" : $("#inputId").val()
   };
   
   this.transientProduct = data;
  
};



// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//
//
//				Beginn ProductList-Skripte
//
//
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



/**
 * Kümmert sich um die Verwaltung aller Produkte, also:
 *    - das Anlegen neuer Produkte
 *    - das Entfernen der Daten gelöschter Produkte
 *    - die Kommunikation mit dem Server
 * @param {Element} container HTML-Element der Produktliste
 */
var Productlist = function(container) {

	console.log("Productlist - Productlist Objekt erstellt");
	
	this.container = container;	

	// interne Variable für die Liste der intern verwalteten Produkte
	this.productListData = [];
	
};


Productlist.prototype.createNewProduct = function(product) {
   console.log("Productlist - erstelle Produkt ... sende Anfrage an PHP API");
   
   // Aufruf der PHP API
   // Wichtig: Json Objekt muss vorher in String umgewandelt werden --> stringify !
   $.ajax({
         url: app.url,
         dataType: "json",
		 method: "POST",
		 data: JSON.stringify(product),
         timeout: 3000
      })
		 // im Erfolgsfall --> data sind Daten von PHP Anwendung - nicht die mitgegebenen!
         .success(function(data) {
            console.log("Productlist - erstelle Produkt ... Anfrage erfolgreich :-)");

			// wenn erfolgreich, neues Produkt anlegen (inkl. Darstellung) und in Liste schreiben 
			var productToCreate = new Product(data, "null", false);
			productToCreate.createProductHtmlCode();
			app.productList.productListData.push(productToCreate);
			 
         })
		 .error(function() {
			 console.log("Productlist - erstelle Produkt ... Anfrage fehlgeschlagen :-(");
		 });
	  
};


Productlist.prototype.updateProduct = function() {
   
   console.log("Productlist - aktualisiere Produkt ID=" + app.form.transientProduct.id);
   
	// Aufruf der PHP API
	$.ajax({
		 url: app.url + ":" + app.form.transientProduct.id,
		 dataType: "json",
		 method: "PUT",
		 data: JSON.stringify(app.form.transientProduct),
		 timeout: 3000
	  })
		 // im Erfolgsfall --> data sind Daten von PHP Anwendung - nicht die mitgegebenen!
		 .success(function(data) {
			console.log("Productlist - aktualisiere Produkt ... Anfrage erfolgreich :-)");

			// wenn erfolgreich, Produkt in Liste aktualisieren (inkl. Darstellung)
			// über Liste von Produkten iterieren und zu aktualisierendes Objekt suchen
			for (var i = 0; i < app.productList.productListData.length; i++){
				if(app.productList.productListData[i] !=null && 
					app.productList.productListData[i].productData.id == app.form.transientProduct.id){
					//Objekt in der internen Liste aktualisieren
					app.productList.productListData[i].productData = data;
					//HTML Code aktualisieren
					app.productList.productListData[i].updateProductHtmlCode();	
					
					// For Schleife nicht weiter durchgehen
					break;
				}
			}
			 
		 })
		 .error(function() {
			 console.log("Productlist - aktualisiere Produkt ... Anfrage fehlgeschlagen :-(");
		 });
};


Productlist.prototype.deleteProduct = function(productId) {
   console.log("Productlist - lösche Produkt ID=" + productId);
   
   // Aufruf der PHP API --> hier werden keine JSON Daten benötigt. Lediglich die ID muss in der URL mitgeschickt werden
   $.ajax({
         url: app.url + ":" + productId,
         dataType: "json",
		 method: "DELETE",
         timeout: 3000
      })
		 // im Erfolgsfall 
         .success(function(data) {
            console.log("Productlist - lösche Produkt ... Anfrage erfolgreich :-)");
						
			// wenn erfolgreich, Produkt aus Produkt-Liste löschen und HTML Code für Darstellung			
			// über Liste von Produkten iterieren und zu löschendes Objekt suchen
			for (var i = 0; i < app.productList.productListData.length; i++){
				if(app.productList.productListData[i] !=null &&
					app.productList.productListData[i].productData.id == productId){
					app.productList.productListData[i].deleteProductHtmlCode();	
					delete app.productList.productListData[i];
					
					// For Schleife nicht weiter durchgehen
					break;
				}
			}
			 
         })
		 .error(function() {
			 console.log("Productlist - lösche Produkt ... Anfrage fehlgeschlagen :-(");
		 });
		 
};


Productlist.prototype.getProduct = function(productId) {
   console.log("Productlist - lese Produkt ID=" + productId);
   
   // Aufruf der PHP API, wenn erfolgreich Produkt-Daten in Formular schreiben
	  $.ajax({
         url: app.url + ":" + productId,
         dataType: "json",
		 method: "GET",
         timeout: 3000
      })
		 // im Erfolgsfall
         .success(function(data) {
            console.log("Productlist - lese Produkt ... Anfrage erfolgreich :-)");
			
			// Daten der Form übergeben
			app.form.transientProduct = data;
			
			app.form.prepareUpdateProductStep2();
			
         })
		 .error(function() {
			 console.log("Productlist - lese Produkt ... Anfrage fehlgeschlagen :-(");
		 });
};


Productlist.prototype.getAllProducts = function() {
   console.log("Productlist - lese alle Produkte");
   
   // Aufruf der PHP API, wenn erfolgreich Liste mit Produkten abspeichern und Oberfläche generieren
       $.ajax({
         url: app.url,
         dataType: "json",
		 method: "GET",
         timeout: 3000
      })
		 // im Erfolgsfall
         .success(function(data) {
            console.log("Productlist - lese alle Produkte ... Anfrage erfolgreich :-)");
			
			// über Liste von Produkten iterieren und alle erstellen
			for (var i = 0; i < data.length; i++){
				// neues Produkt anlegen (inkl. Darstellung) und in Liste schreiben 
				var productToCreate = new Product(data[i], "null", false);
				productToCreate.createProductHtmlCode();
				app.productList.productListData.push(productToCreate);
			}
         })
		 .error(function() {
			 console.log("Productlist - erstelle Produkt ... Anfrage fehlgeschlagen :-(");
		 });
};



// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//
//
//				Beginn Product-Skript
//
//
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



/**
 * Repräsentiert ein einzelnes Produkt und kümmert sich um dessen:
 *    - Darstellung
 *    - Aktualisierung
 *    - Entfernung
 * @param {Object}  data      Daten-Objekt mit den Daten des Produkts
 * @param {Element} container HTML-Element der Produktliste
 */
var Product = function(data, container, isTransient) {
	console.log("Product - neues Produkt Objekt erstellt: ");
	
   // interne Variable für die Daten
	this.productData = data;   
	
	// flag für temporäre Produkte
	this.isTransient = isTransient;
   
   // interne Variable für container
	this.container = container;
    
};


Product.prototype.createProductHtmlCode = function() {
   console.log("Product - erstelle HTML-Code für neues Produkt ID=" + this.productData.id);
  
	// Erstellung des HTML Objekts mit dem Template --> http://robdodson.me/html5-template-tag-introduction/
	
	// Template in HTML Code suchen, damit gleich angepasst werden kann
	 var template = document.querySelector('#productTemplate');
	// URLs anpassen
	if(this.productData.url != ""){
		template.content.querySelector('.templateURL').href = app.url + this.productData.url;
	}
	template.content.querySelector('img').src = this.productData.image;
	// Buttons anpassen
	template.content.querySelector('.btn-edit').href = "javascript:app.form.prepareUpdateProductStep1(" + this.productData.id + ")";
	template.content.querySelector('.btn-delete').href = "javascript:app.productList.deleteProduct(" + this.productData.id + ")";
	// Name anpassen
	template.content.querySelector('.templateName').textContent  = this.productData.name;
	// Preis anpassen
	template.content.querySelector('.lead').textContent  = this.productData.price;
	// ID für technischen Zugriff anpassen
	template.content.querySelector('.col-xs-4').id = "product" + this.productData.id;
	// Template in Dokument einfügen
	document.getElementById("products-list").appendChild(document.importNode(template.content, true));
	
	// setzen der Variable für den HTML Container
	var query = "#product" + this.productData.id;
	this.container = $(query);	 
	
};


Product.prototype.updateProductHtmlCode = function() {
   console.log("Product - aktualisiere Produkt (löschen und wieder anlegen)");
   
  this.deleteProductHtmlCode();
  this.createProductHtmlCode();
  
};


Product.prototype.deleteProductHtmlCode = function() {
   console.log("Product - lösche Produkt ID=" + this.productData.id);
   
   // löscht das HTML Objekt
   var query = "#product" + this.productData.id;
	$(query).remove();
  
};