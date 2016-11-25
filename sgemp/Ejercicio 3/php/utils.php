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

/**
Return an array with fibonacci serie until the number passed
*/
function getFibonacci($num){
	$before = 0;
	$after = 1;
	echo "1";
	if ($num != 1)
		echo ", ";
	
	for ($i=0; $i < ($num - 1); $i++) { 
		$after = $before + $after;
		$before = $after - $before;
		//If the number reachs the float limit, it will stop and print an error message
		if (is_nan($after) || is_infinite($after)){
			echo "<br><br>Se ha llegado al límite. Se ha quedado por el número ".$i;
			break;
		}
		echo $after;
		//Print a comma unless it is the last number
		if ($i < ($num - 2))
			echo ", ";
	}
}

?>