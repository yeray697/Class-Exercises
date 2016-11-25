<?php
/**
Function that prints the header
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

/**
Function that prints the footer
*/
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

$players = array(
		array("id" => "1", "name" => "Iker casillas",	"position" => "portero"),
		array("id" => "2", "name" => "Cristiano Ronaldo",	"position" => "delantero"),
		array("id" => "3", "name" => "Messi",	"position" => "delantero"),
		array("id" => "4", "name" => "Neymar",	"position" => "delantero"),
		array("id" => "5", "name" => "Iniesta",	"position" => "centrocampista"),
		array("id" => "6", "name" => "Piqué",	"position" => "defensa"),
		array("id" => "7", "name" => "Koke",	"position" => "centrocampista"),
		array("id" => "8", "name" => "Sergio Ramos",	"position" => "defensa"),
		array("id" => "9", "name" => "Isco",	"position" => "centrocampista"),
		array("id" => "10", "name" => "Sergio Busquets",	"position" => "centrocampista"),
		);

/**
Get a random player from the array $players
*/
function getRandomPlayer(){
	global $players;
	shuffle($players);
	$player = array($players[0]);
	return $player;
}

/**
Returns the player with the id passed
*/
function getPlayer($id){
	global $players;
	for ($i=0; $i < 10; $i++) {
		if ($players[$i]["id"] == $id) {
			$player = $players[$i];
			break;
		}
	}
	if (!isset($player)) {
		return -1;
	}
	return $player;
}

/**
Check if the player choosed is correct
*/
function checkPlayer($id, $position){
	global $players;
	$correct = false;
	for ($i=0; $i < 10; $i++) { 
		if ($players[$i]["id"] == $id) {
			if ($players[$i]["position"] == $position) {
				$correct = true;
			}
			break;
		}
	}
	return $correct;
}

?>