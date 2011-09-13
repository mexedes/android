<?php

$hostname ="localhost";
$database ="senior_project";
$username ="root";
$password ="1234";

$localhost = mysql_connect($hostname,$username,$password)
or
trigger_error(mysql_error(),E_USER_ERROR);
?>