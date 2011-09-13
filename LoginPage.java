package senior_project.main;

import android.app.Activity;

import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends Main {
	
	/** Called when the activity is first created. */
	private Button login;
	String name = "", pass = "";
	EditText username, password;
	TextView tv, usernametext,passwordtext;
	byte[] data;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	InputStream inputStream;
	SharedPreferences app_preferences;
	List<NameValuePair> nameValuePairs;
	CheckBox check;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginpage);

		final Intent intentGetUSERLocation;
		
		app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		usernametext = (TextView)findViewById(R.id.usernametext);
		passwordtext = (TextView)findViewById(R.id.passwordtext);
		login = (Button) findViewById(R.id.login);
		check = (CheckBox) findViewById(R.id.check);
		String Str_user = app_preferences.getString("username", "0");
		String Str_pass = app_preferences.getString("password", "0");
		String Str_check = app_preferences.getString("checked", "no");
		if (Str_check.equals("yes")) {
			username.setText(Str_user);
			password.setText(Str_pass);
			check.setChecked(true);
		}
		
		intentGetUSERLocation = new Intent().setClass(this, GetUSERLocation.class);
		
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				name = username.getText().toString();
				pass = password.getText().toString();
				String Str_check2 = app_preferences.getString("checked", "no");
				if (Str_check2.equals("yes")) {
					SharedPreferences.Editor editor = app_preferences.edit();
					editor.putString("username", name);
					editor.putString("password", pass);
					editor.commit();
				}
				if (name.equals("") || pass.equals("")) {
					Toast.makeText(LoginPage.this, "Blank Field..Please Enter",
							Toast.LENGTH_LONG).show();
				} else {
					try {
						httpclient = new DefaultHttpClient();
						httppost = new HttpPost(
								"http://"+serverHost+"/senior_project/API/user_validate.php");
						// Add your data
						nameValuePairs = new ArrayList<NameValuePair>(1);
						nameValuePairs.add(new BasicNameValuePair("User",
								name.trim()));
						nameValuePairs.add(new BasicNameValuePair("Password",
								pass.trim()));
						httppost.setEntity(new UrlEncodedFormEntity(
								nameValuePairs));
						// Execute HTTP Post Request
						response = httpclient.execute(httppost);
						inputStream = response.getEntity().getContent();
						data = new byte[256];
						buffer = new StringBuffer();
						int len = 0;
						while (-1 != (len = inputStream.read(data))) {
							buffer.append(new String(data, 0, len));
						}
						inputStream.close();
					} catch (Exception e) {

						Toast.makeText(LoginPage.this, "error" + e.toString(),
								Toast.LENGTH_LONG).show();
					}
					
					if (buffer.charAt(0) == 'Y') {
						userlogined_name = name;
						Toast.makeText(LoginPage.this, "login successfull",
								Toast.LENGTH_LONG).show();
							startActivity(intentGetUSERLocation);
					} else {
						Toast.makeText(LoginPage.this,
								"Invalid Username or password",
								Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		check.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on clicks, depending on whether it's now
				// checked
				SharedPreferences.Editor editor = app_preferences.edit();
				if (((CheckBox) v).isChecked()) {
					editor.putString("checked", "yes");
					editor.commit();
				} else {
					editor.putString("checked", "no");
					editor.commit();
				}
			}
		});
	}
}
