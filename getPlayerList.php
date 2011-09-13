<?php  
include "connectDB.php";
  

mysql_query("SET NAMES UTF8");  
  

$user_id=$_REQUEST['user_id'];  

if(!$user_id) $user_id="";  

mysql_select_db($database,$localhost);

$q=mysql_query("SELECT user_id,user_latitude,user_longitude,username FROM login_zone,user where login_zone.user_id != '$user_id' AND user.id != '$user_id' AND login_zone.user_id= user.id ")or die(mysql_error());
while($e=mysql_fetch_assoc($q))  
       $output[]=$e;
  echo '{"earthquakes":';
//แปลงข้อมูลให้อยู่ในรูปแบบของ JSON และพิมพ์ข้อมูลออกมา  
print(json_encode($output));
echo '}';  
mysql_close();  
?>  