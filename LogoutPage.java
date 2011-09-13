package senior_project.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogoutPage extends Main{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logoutpage);
		
		userlogined_name = null;
		userlogined_id = 0;
		userCharacter_id = 0;
		
		usercharacter_class_id = 0;
		usercharacter_point = 0;
		usercharacter_status_attack = 0;
		usercharacter_status_agility = 0;
		usercharacter_status_defence = 0;
		usercharacter_status_intelligent = 0;
		
		character_class_name = null;
		character_class_status_attack = 0;
		character_class_status_agility = 0;
		character_class_status_defence = 0;
		character_class_status_intelligent = 0;
		
		userKeylogin = null;
		userLatitude = 0;
		userLongitude = 0;
		
		
		final Intent intentLoginPage;
		intentLoginPage = new Intent().setClass(this, LoginPage.class);
		 startActivity(intentLoginPage);
	}
}
