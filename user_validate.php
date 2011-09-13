<?php 

include "connectDB.php";

$username = $_POST['User'];
$password = $_POST['Password'];

mysql_select_db($database,$localhost);

 $sql_user = "select * from user where username = '".$username."' AND password = '".$password. "'";
 $query_user = mysql_query($sql_user) or die(mysql_error());
 $rows = mysql_num_rows($query_user);
 if($rows --> 0) { echo "Y"; }
else  {echo "N0"; }
?>