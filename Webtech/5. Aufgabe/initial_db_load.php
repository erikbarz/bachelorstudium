<html>
<head>
<title> </title>
</head>

<body>
<h1>Viele Datensatze erstellen, in die DB insert</h1>
		
		
<?php			

// Datenbank
$user="root";
$password="root";
$host="localhost";
$db="products";

//Verbindung erstellen
$connection = mysql_connect($host, $user, $password);

echo " <br> Verbindung zu DB $db hergestellt ";

mysql_select_db($db);

//Tabellenname=products:
//products=$products
//insert
	
	
	mysql_query("DELETE FROM products");
	//TRUNCATE TABLE 'products';
	
	$insert_table = [
            ['Schuhe',     '8',   '01', '52.4588048+13.5230717'],
            ['Jacke', '20', '02', '50.9854275+10.9452961'],
            ['Socken', '6',    '03', '48.1375634+11.5733682'],
            ['T-Shirt',    '10', '04', '48.8106768+9.0911429'],
            ['Pullover', '12', '05', '50.9373302+6.9428015'],
            ['Hose', '12', '06', '53.5586941+9.7877401']
         ];
 
 echo "<br><br> Test 3<br>";
         foreach ($insert_table as list($name, $price, $id, $location)) {
			echo "Folgende Daten werden geschrieben: $name, $price, $id, $location <br>";
			$insert="INSERT INTO products (name, price, id, location) 
		VALUES (\"$name\", \"$price\", \"$id\", \"$location\")";
			mysql_query($insert);
         }

?>

</body>
</html>