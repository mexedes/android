<?php  
include "connectDB.php";
  

mysql_query("SET NAMES UTF8");  
  

$username=$_REQUEST['username'];  

if(!$username) $username="";  

mysql_select_db($database,$localhost);

$q=mysql_query("SELECT * FROM user,user_character,character_class where user.username = '$username' AND user_character.character_id = user.character_id AND user_character.character_class_id=character_class.class_id")or die(mysql_error());
while($e=mysql_fetch_assoc($q))  
       $output[]=$e;
  
//แปลงข้อมูลให้อยู่ในรูปแบบของ JSON และพิมพ์ข้อมูลออกมา  
print(json_encode($output));  
mysql_close();  
?>  