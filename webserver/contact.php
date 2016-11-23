<script type="text/javascript">
	var deviceInfo = Android.getFromAndroid();
</script>
<?php
if (isset($_POST["submit"])) {
	$name = $_POST['name'];
	$email = $_POST['email'];
	$message = $_POST['message'];
	$human = intval($_POST['human']);
	$from = 'Veldhoven viert Feest'; 
	$to = 'info@fressh.nl'; 
	$subject = 'Bericht van VvF App';
	$device = $_POST['deviceInfo'];
	
	$body ="Van: $name\n E-Mail: $email\n Bericht:\n $message\n Apparaat info:\n $device";

	// Check if name has been entered
	if (!$_POST['name']) {
		$errName = 'Vul alstublieft uw naam in';
	}
	
	// Check if email has been entered and is valid
	if (!$_POST['email'] || !filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) {
		$errEmail = 'Uw e-mail is ongeldig';
	}
	
	//Check if message has been entered
	if (!$_POST['message']) {
		$errMessage = 'Vul alstublieft een tekst in';
	}
		
// If there are no errors, send the email
if (!$errName && !$errEmail && !$errMessage && !$errHuman) {
	$headers = "From: $email"; 
	if (mail ($to, $subject, $body, $headers, "-f " . $email)) {
		$result='<div class="alert alert-success">Bedankt! Uw bericht is verstuurd</div>';
	} else {
		$result='<div class="alert alert-danger">Er ging iets fout, probeer het opnieuw.</div>';
	}
}
	}
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
  </head>
  <body>
  	<div class="container">
  		<div class="row">
  			<div class="col-md-6 col-md-offset-3">
	  			<div class="form-group">
					<div class="col-sm-10 col-sm-offset-2">
						<br>
						<center><?php echo $result; ?></center>
					</div>
				</div>
	  			<p>Neem via dit formulier contact met ons.</p>
				<form class="form-horizontal" role="form" method="post" action="contact.php">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Naam</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name" placeholder="Voor en achternaam" value="<?php echo htmlspecialchars($_POST['name']); ?>">
							<?php echo "<p class='text-danger'>$errName</p>";?>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">E-mail</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="email" name="email" placeholder="naam@domein.nl" value="<?php echo htmlspecialchars($_POST['email']); ?>">
							<?php echo "<p class='text-danger'>$errEmail</p>";?>
						</div>
					</div>
					<div class="form-group">
						<label for="message" class="col-sm-2 control-label">Bericht</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="4" name="message"><?php echo htmlspecialchars($_POST['message']);?></textarea>
							<?php echo "<p class='text-danger'>$errMessage</p>";?>
						</div>
					</div>
					<div class="form-group">
						<label for="device" class="col-sm-2 control-label">Apparaat informatie</label>
						<div class="col-sm-10">
							<p>Informatie die wordt meegestuurd: <script type="text/javascript">document.write(deviceInfo)</script></p>
							<input type="hidden" class="form-control" id="deviceInfo" name="deviceInfo">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10 col-sm-offset-2">
							<input id="submit" name="submit" type="submit" value="Verstuur" class="btn btn-primary">
						</div>
					</div>
				</form> 
			</div>
		</div>
	</div>   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript">
		document.getElementById("deviceInfo").value = deviceInfo;
	</script>
  </body>
</html>
