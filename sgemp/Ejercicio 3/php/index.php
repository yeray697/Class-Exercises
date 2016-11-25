<?php
include 'utils.php';
head("Fibonacci","Fibonacci","","home");

echo "<h2>Escribe el número para calcular la serie de Fibonacci hasta ese número</h2>";
echo "
<form method='post' action='index.php' id='form'>
  Número:
  <input type='number' name='number' min='1'>
  <button type='submit' form='form' value='Calcular'>Calcular</button>
</form>";
if (isset($_POST['number'])) {
	$num = $_POST['number'];
	echo "<hr>
	<h2>Resultado</h2>";
	getFibonacci($num);
}
foot();
?>
