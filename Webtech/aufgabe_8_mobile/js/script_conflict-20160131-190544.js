$(function() {

   // Variablen
   var api_url = 'http://192.168.178.25/aufgabe_8_mobile/api/data.json';
   var fav_currencies = ['EUR', 'USD', 'GBP', 'CHF', 'ILS', 'CNY', 'JPY', 'SGD'];
   var currencies = window.currencies;
   var rates = {};
   // verschiedene Wege, an die gewünschten DOM-Elemente zu kommen
   //var overview = document.querySelector('.overview');
   var select_from = document.querySelector('select[name="from-currency"]');
   var select_to = $('select[name="to-currency"]')[0];
   // verschiedene Wege, auf Eigenschaften von Objekten zuzugreifen
   var input_from = document.exchange['from-value'];
   var input_to = document['exchange']['to-value'];

   // Event Handler

   $('input')
      .on('input', function(event) {
         var amount = event.target.value;
         var currency = (event.target === input_from ? select_from : select_to).value;

         updateInputFields(event.target);
         //updateOverview(amount, currency);
      })
      .on('keydown', function(event) {
         // Zuordnung von Tasten zu Wertveränderung
         // 'hoch' und 'runter' verändern um 10, '+' und '-' um 100
         var mapping = {
            38: 10,
            40: -10,
            187: 100,
            189: -100
         };

         // Prüfe, ob es einen Wert für diese Taste gibt
         if (event.keyCode in mapping) {
            // verhindere, dass beispielsweise ein '+' eingefügt wird
            event.preventDefault();
            // Verrechne den aktuellen Wert mit der entsprechenden Veränderung und ersetze ihn
            // '+this.value' sichert, dass 'value' - sofern möglich - als Zahl behandelt wird
            this.value = +this.value + mapping[event.keyCode];
         }

         // 'input'-Event triggern, um dazugehöriges Verhalten auszulösen (Aktualisieren der Werte)
         $(this).trigger('input');
      });

   // Bei Wechseln der Währung, ändere die entsprechenden Beträge
   $('select').on('change', function(event) {
      updateInputFields(event.target === select_from ? input_to : input_from);
   });

   // Funktionen (als Ausdrücke, nicht Deklarationen)

   var initialize = function() {
      // hole gleich zu Beginn frische Daten vom Server und führe danach die folgende Funktion aus
      fetchData(function() {
         input_from.value = '100';
         updateInputFields(input_from);
         //updateOverview(input_from.value, select_from.value);
      });

      updateSelections();

      // setze die Anfangs-Auswahl auf Euro zu US-Dollar
	  $('#fromCurrency').val("EUR").selectmenu('refresh');
	  $('#toCurrency').val("USD").selectmenu('refresh');	  
   };

   
   // Ladefunktion für Wechselkurs-Daten
   var fetchData = function(callback) {
      $.ajax({
         url: api_url,
         dataType: 'jsonp',
         timeout: 3000
      })
		 // im Erfolgsfall 
         .success(function(data) {
            rates = data.rates;

			// wenn Daten im Local Storage verfügbar, speichern
			if (window.localStorage.exchangeRates) {
				//Variable rates in localStorage einspeichern
				window.localStorage.setItem("exchangeRates", JSON.stringify(rates)); 
			}else {
				// wenn nicht, Fehlermeldung 
				//$('.error-msg').fadeIn('slow');
			}	
			 
		
            if (callback) callback();
         })
		 // im Fehlerfall 
         .error(function() {
			if(typeof(Storage) !== "undefined") {
				// Code for localStorage/sessionStorage.
				// wenn Daten im Local Storage verfügbar, laden
				if (window.localStorage.getItem("exchangeRates")) {
					rates = JSON.parse(window.localStorage.getItem("exchangeRates")); 
					// der folgende Part würde sonst nur im success durchlaufen werden --> kopiert von oben
					input_from.value = '100';
					updateInputFields(input_from);
					updateSelections();
					  $('#fromCurrency').val("EUR").selectmenu('refresh');
					  $('#toCurrency').val("USD").selectmenu('refresh');	
				}else {
					// wenn nicht, Fehlermeldung 
					//$('.error-msg').fadeIn('slow');
				}	
			} else {
				// Sorry! No Web Storage support..
				//$('.error-msg').fadeIn('slow');
			}			
            
         });
   };

   var updateInputFields = function(origin_input_element) {
      if (origin_input_element === input_from) {
         input_to.value = getChangedValue(origin_input_element.value, select_from.value, select_to.value);
      }
      else {
         input_from.value = getChangedValue(origin_input_element.value, select_to.value, select_from.value);
      }
   };

   // aktualisiere die Auswahllisten der Währungen
   var updateSelections = function() {
      // wir benutzen aus Performance-Gründen Document-Fragments, sodass final nur einmal auf den DOM zugegriffen werden muss
      var fragment = document.createDocumentFragment();
      var element;

      // Generiere für jede Währung eine entsprechende Option für die Drop-Downs
      $.each(currencies, function(index, value) {
         element = document.createElement('option');
         //element.text = index + ' - ' + value;
		 element.text = index ;
         element.value = index;
         fragment.appendChild(element);
      });

      // klone die Liste und füge in beiden Select-Elementen jeweils eine ein
      select_from.appendChild(fragment.cloneNode(true));
      select_to.appendChild(fragment);
   };


   // Berechne für eine Geldmenge einer Währung die entsprechende Geldmenge in einer Fremdwährung
   var getChangedValue = function(amount, from_currency, to_currency) {
		// Zwischenvariablen zur Fehleranalyse eingeführt --> teilweise keine Daten für Währungen
      var fromCurRate = rates[from_currency] ;
	  var toCurRate= rates[to_currency];
	  var value = amount / fromCurRate * toCurRate;
      return Math.round(value * 100) / 100;
   };

   // Initialisere den Ausgangszustand
   initialize();

});
