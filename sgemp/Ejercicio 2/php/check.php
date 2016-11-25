<?php
include 'utils.php';
$playerId = $_POST["id"];
$playerPosition = $_POST["position"];
if (checkPlayer($playerId,$playerPosition)) {
	header("Location: correct.php");
} else {
	header("Location: index.php?player=".$playerId."&error=true");
}
?>