<?php
/*
Este fichero va a contener funciones que se utilizarán en otros ficheros php.
*/
function head($title,$h1,$h2,$current_page){
	echo "
	<head>
		<meta charset='utf-8' />
		<title>".$title."</title>
		<meta name='viewport' content='width=device-width, initial-scale=1.0' />
		<link rel='stylesheet' type='text/css' href='../css/style.css' />
	</head>
	<body>
		<div class='bg-main'>
			<!-- Cabecera -->
			<header>
				<h1><a href='/''></a></h1>
				<h1>$h1</h1>
				<h2>".$h2."</h2>
			</header>
			<div id='content'>";
}

function foot(){
	echo "
			</div>
		</div>
  		<!-- Pie de página -->
		<footer>
  			<p>Propiedad de Phile S.L.</p>
  		</footer>
	</body>
</html>";
}

function getMenu(){
	$first = array(
		array("name" => "Cocido asturiano",	"price" => "5€"),
		array("name" => "Caldo gallego", "price" => "4.50€"),
		array("name" => "Habas en cazuela típica de Córdoba", "price" => "6.50€"),
		array("name" => "Arroz caldoso con bacalao típico de Málaga", "price" => "4.50€"),
		array("name" => "Lentejas rojas con brocoli", "price" => "5€"),
		array("name" => "Caldo quemao almeriense", "price" => "5€"),
		array("name" => "Sopa de Almería", "price" => "4€"),

		);

	$second = array(
		array("name" => "Cachopo asturiano", "price" => "6€"),
		array("name" => "Berberechos al vapor perfectos", "price" => "8.25€"),
		array("name" => "Cordero a la miel típico de Córdoba", "price" => "12€"),
		array("name" => "Flamenquines cordobeses", "price" => "6€"),
		array("name" => "Pavía de bacalao típica de Sevilla", "price" => "9€"),
		array("name" => "Huevos a la flamenca típicos de Sevilla", "price" => "5.25€"),
		array("name" => "Tortilla del sacromonte típica de Granada", "price" => "6€"),
		);
	$dessert = array(
		array("name" => "Poleá típica de Sevilla", "price" => "2.75€"),
		array("name" => "Perrunillas típicas de Huelva", "price" => "3€"),
		array("name" => "Torrijas", "price" => "3.5€"),
		array("name" => "Pestiño jerezano", "price" => "3€"),
		array("name" => "Arroz con leche", "price" => "2.50€"),
		array("name" => "Mostachones", "price" => "4€"),
		array("name" => "Pionono", "price" => "3.50€"),
		);
	shuffle($first);
	shuffle($second);
	shuffle($dessert);
	$menu = array($first,$second,$dessert);
	return $menu;
}
?>