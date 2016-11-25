<?php
include 'utils.php';
head("El cocinitas","El cocinitas","Tu restaurante de confianza","home");
echo "
<h1> Disfruta de nuestros menús variados que cambian cada día!</h1>";
/*<table style="width:100%">
  <tr>
    <th>Firstname</th>
    <th>Lastname</th> 
    <th>Age</th>
  </tr>*/
  echo "<table class='foodmenu'>
  <tr>
    <th></th>
    <th>Lunes</th>
    <th>Martes</th> 
    <th>Miércoles</th>
    <th>Jueves</th>
    <th>Viernes</th>
    <th>Sábado</th>
    <th>Domingo</th>
  </tr>";
$menu = getMenu();
for ($i=0; $i < 3; $i++) { 
	echo "<tr>";
	if ($i == 0) {
		echo "<td class=\"fixed\">Primer plato</td>";
	} else if ($i == 1) {
		echo "<td class='fixed'>Segundo plato</td>";
	} else {
		echo "<td class='fixed'>Postre plato</td>";
	}
	for ($j=0; $j < 7; $j++) {
		echo "<td>";
		print_r($menu[$i][$j]["name"]);
		echo "<br><br>Precio: ";
		print_r($menu[$i][$j]["price"]);
		echo "</td>";
	}
		echo "</tr>";
}
echo "</table>";
echo "<br>";
$month=date("F");
$year=date("Y");

if ($month=="January") $month="Enero";
if ($month=="February") $month="Febrero";
if ($month=="March") $month="Marzo";
if ($month=="April") $month="Abril";
if ($month=="May") $month="Mayo";
if ($month=="June") $month="Junio";
if ($month=="July") $month="Julio";
if ($month=="August") $month="Agosto";
if ($month=="September") $month="Setiembre";
if ($month=="October") $month="Octubre";
if ($month=="November") $month="Noviembre";
if ($month=="December") $month="Diciembre";
echo "<h3>$month, $year</h3>";
foot();
?>