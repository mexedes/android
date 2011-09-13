<?php

//==== variable declaration ====
$pop_user_id = $_REQUEST["user_id"];


//==== connect database ====
include "connectDB.php";

//==== SQL ====

mysql_select_db($database, $localhost);
$sql="SELECT user_id FROM login_zone WHERE user_id = '$pop_user_id' ";
$result = mysql_query($sql);
$row = mysql_num_rows($result);
 if($row>=1){
	 $delete_sql="DELETE FROM login_zone WHERE user_id = '$pop_user_id' ";
	 $delect_result = mysql_query($delete_sql);
	
 }
mysql_close($localhost);

?>