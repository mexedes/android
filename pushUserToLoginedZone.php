<?php

//==== variable declaration ====
$push_user_id = $_REQUEST['user_id'];
$push_latitude = $_REQUEST['user_latitude']; 
$push_longitude = $_REQUEST['user_longitude']; 


//==== connect database ====
include "connectDB.php";

//==== SQL ====

mysql_select_db($database, $localhost);
$sql="SELECT user_id FROM login_zone WHERE user_id = '$push_user_id' ";
$result = mysql_query($sql);
$row = mysql_num_rows($result);
 if($row<=0){
	$input_sql="INSERT INTO login_zone (user_id, user_status,user_latitude,user_longitude) VALUES ('$push_user_id','stanby', '$push_latitude','$push_longitude')";
	$input_result = mysql_query($input_sql);
 }
?>