package senior_project.main;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends Main {

	private Button logout;
	private Button worldmap;
	private Button testPlay;
	private Button playerList;
	private Button friendList;
	private Button manageAccount;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userresult);
		
		
		final Intent intentLogoutPage;
		final Intent intentPlayerListPage;
		final Intent intentWorldmap;
		final Intent intentFriendList;
		final Intent intentManageAccount;
		final Intent intentPlayGame;
		getServerData();
		pushUserToServerData();
		updateServerData();
		
		
		TextView username = new TextView(this);
		username = (TextView) findViewById(R.id.username);

		TextView characterclassname = new TextView(this);
		characterclassname = (TextView) findViewById(R.id.characterclass);
		
		TextView attack = new TextView(this);
		attack = (TextView) findViewById(R.id.Attack);

		TextView defense = new TextView(this);
		defense = (TextView) findViewById(R.id.Defense);

		TextView agility = new TextView(this);
		agility = (TextView) findViewById(R.id.Agility);

		TextView intelligent = new TextView(this);
		intelligent = (TextView) findViewById(R.id.Intelligent);

		TextView latitude = new TextView(this);
		latitude = (TextView) findViewById(R.id.Latitude);

		TextView longitude = new TextView(this);
		longitude = (TextView) findViewById(R.id.Longitude);
		
		username.setText(userlogined_name);
		characterclassname.setText(character_class_name);
		attack.setText(String.valueOf(usercharacter_status_attack+character_class_status_attack));
		defense.setText(String.valueOf(usercharacter_status_defence+character_class_status_defence));
		agility.setText(String.valueOf(usercharacter_status_agility+character_class_status_agility));
		intelligent.setText(String.valueOf(usercharacter_status_intelligent+character_class_status_intelligent));
		latitude.setText(String.valueOf(userLatitude));
		longitude.setText(String.valueOf(userLongitude));
		
	
		intentWorldmap = new Intent().setClass(this, Worldmap.class);
		worldmap = (Button) findViewById(R.id.button1);
		worldmap.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
					
					startActivity(intentWorldmap);
				}
		});
		
		intentPlayGame = new Intent().setClass(this, PlayGame.class);
		testPlay = (Button) findViewById(R.id.TestPlay);
		testPlay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
					
					startActivity(intentPlayGame);
				}
		});
		
		intentManageAccount = new Intent().setClass(this, ManageAccount.class);
		manageAccount = (Button) findViewById(R.id.button4);
		manageAccount.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
					
					startActivity(intentManageAccount);
				}
		});
		
		
		intentFriendList = new Intent().setClass(this, FriendList.class);
		friendList = (Button) findViewById(R.id.button3); 
		friendList.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
					
					startActivity(intentFriendList);
				}
		});
		
		intentLogoutPage = new Intent().setClass(this, LogoutPage.class);
		logout = (Button) findViewById(R.id.Logout);
		logout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
					popUserOutServerData();
					startActivity(intentLogoutPage);
				}
		});
		
		
		intentPlayerListPage = new Intent().setClass(this, PlayerListPage.class);
		playerList = (Button) findViewById(R.id.PlayerList);
		playerList.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(intentPlayerListPage);
				}
		});
		
	}

	
	private void getServerData() {
		
		InputStream is = null;
		String result = "";
		// ส่วนของการกำหนดตัวแปรเพื่อส่งให้กับ php
		// ส่วนนี้สามารถประยุกต์ไปใช้ในการเพิ่มข้อมูลให้กับ Server ได้
		// จากตัวอย่างส่งค่า moreYear ที่มีค่า 1990
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username", userlogined_name));

		// ส่วนของการเชื่อมต่อกับ http เพื่อดึงข้อมูล
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://" + serverHost
					+ "/senior_project/API/user_result.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

		// ส่วนของการแปลงผลลัพธ์ให้อยู่ในรูปแบบของ String
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-11"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		// ส่วนของการแปลงข้อมูล JSON ออกมาในรูปแบบของข้อมูลทั่วไปเพื่อนำไปใช้
		try {
			// แสดงผลออกมาในรูปแบบของ JSON

			JSONArray jArray = new JSONArray(result);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json_data = jArray.getJSONObject(i);
				
				userlogined_id = Integer.valueOf(json_data.getString("id"));
				userCharacter_id = Integer.valueOf(json_data.getString("character_id"));
				usercharacter_class_id = Integer.valueOf(json_data
						.getString("character_class_id"));
				usercharacter_point = Integer.valueOf(json_data.getString("character_point"));
				usercharacter_status_attack = Integer.valueOf(json_data
						.getString("status_attack"));
				usercharacter_status_agility = Integer.valueOf(json_data
						.getString("status_agility"));
				usercharacter_status_defence = Integer.valueOf(json_data
						.getString("status_defence"));
				usercharacter_status_intelligent = Integer.valueOf(json_data
						.getString("status_intelligent"));
				character_class_name = json_data.getString("class_name");
				character_class_status_attack = Integer.valueOf(json_data.getString("attack"));
				character_class_status_agility = Integer.valueOf(json_data.getString("agility"));
				character_class_status_defence = Integer.valueOf(json_data.getString("defence"));
				character_class_status_intelligent = Integer.valueOf(json_data
						.getString("intelligent"));
				// นำข้อมูลใส่ตัวแปรเพื่อไปแสดงต่อ

			}
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		// ส่งผลลัพธ์ไปแสดงใน txtResult
		return;
	}
	
	private void pushUserToServerData(){
		InputStream is = null;
		// ส่วนของการกำหนดตัวแปรเพื่อส่งให้กับ php
		// ส่วนนี้สามารถประยุกต์ไปใช้ในการเพิ่มข้อมูลให้กับ Server ได้
		// จากตัวอย่างส่งค่า moreYear ที่มีค่า 1990
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("user_id", String.valueOf(userlogined_id)));
		nameValuePairs.add(new BasicNameValuePair("user_latitude", String.valueOf(userLatitude)));
		nameValuePairs.add(new BasicNameValuePair("user_longitude", String.valueOf(userLongitude)));

		// ส่วนของการเชื่อมต่อกับ http เพื่อดึงข้อมูล
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://" + serverHost
					+ "/senior_project/API/pushUserToLoginedZone.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
		return;
	}
	
	private void updateServerData(){
		return;
	}
	
	private void popUserOutServerData(){
		InputStream is = null;
		// ส่วนของการกำหนดตัวแปรเพื่อส่งให้กับ php
		// ส่วนนี้สามารถประยุกต์ไปใช้ในการเพิ่มข้อมูลให้กับ Server ได้
		// จากตัวอย่างส่งค่า moreYear ที่มีค่า 1990
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("user_id", String.valueOf(userlogined_id)));

		// ส่วนของการเชื่อมต่อกับ http เพื่อดึงข้อมูล
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://" + serverHost
					+ "/senior_project/API/popUserOutLoginedZone.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
		return;
	}
}