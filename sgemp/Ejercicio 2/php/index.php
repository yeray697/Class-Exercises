<?php
include 'utils.php';
head("Adivinalo","¿Conoces a tus futbolistas favoritos?","","home");
if (isset($_GET['player']) && isset($_GET['error']) && $_GET['error'] == "true") {
	$playerId = $_GET["player"];
	$player = getPlayer($playerId);	//If $playerId is not a valid Id, $player will be empty
	if(isset($player)) {
		$playerName = $player["name"];	
		echo "<span class='error'>TE EQUIVOCASTE</span>";
	} else {
		getFullPlayer();
	}
} else {
	getFullPlayer();
}
echo "<h2>¿Cuál es la posición del jugador ".$playerName."?</h2>";
echo "
<form method='post' action='check.php' id='form'>
	<input type='radio' name='position' value='delantero' checked> Delantero<br>
	<input type='radio' name='position' value='centrocampista'> Centrocampista<br>
	<input type='radio' name='position' value='defensa'> Defensa <br>
	<input type='radio' name='position' value='portero'> Portero
	<input type='hidden' name='id' value='".$playerId."' /> <br>
	<button type='submit' form='form' value='Comprobar'>Comprobar</button>
</form>";
foot();

/**
	Method, that uses global variables, that get a random player
*/
function getFullPlayer(){
	global $player, $playerName, $playerId;
	$player = getRandomPlayer();
	$playerName = $player[0]["name"];
	$playerId = $player[0]["id"];
}
?>
