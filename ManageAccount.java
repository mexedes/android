package senior_project.main;


import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ManageAccount extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlocationmanual);
	}

	public JSONObject getLocationInfo(String address) {
		HttpGet httpGet = new HttpGet(address);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			int b;
			while ((b = stream.read()) != -1) {
				stringBuilder.append((char) b);
			}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {

		}
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject = new JSONObject(stringBuilder.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	public void getAddress_clicked(View view) {
		EditText latEdtTxt = (EditText) findViewById(R.id.lat);
		Double lat = Double.parseDouble(latEdtTxt.getText().toString());
		EditText lonEdtTxt = (EditText) findViewById(R.id.lon);
		Double lon = Double.parseDouble(lonEdtTxt.getText().toString());

		JSONObject jsonObject = getLocationInfo("http://maps.googleapis.com/maps/api/geocode/json?latlng="+ lat + "," + lon + "&sensor=true");
		
		// get address from lat, lon
		String addr = new String();
		try {
			addr = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
					.getString("formatted_address");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(ManageAccount.this, addr.toString(), Toast.LENGTH_LONG).show();

		Intent map = new Intent().setClass(this, Map.class);
		map.putExtra("lat", lat);
		map.putExtra("lon", lon);
		startActivity(map);
	}

	public void getLatLon_clicked(View view) {
		EditText address = (EditText) findViewById(R.id.address);
		// Replace all white space with %20
		String addr_removeSpace = address.getText().toString().replaceAll(" ", "%20");
		JSONObject jsonObject = getLocationInfo("http://maps.googleapis.com/maps/api/geocode/json?address=" + addr_removeSpace + "&sensor=true");
		
		// get lat, lon from address
		Double lon = new Double(0);
		Double lat = new Double(0);
		try {
			lon = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
					.getJSONObject("geometry").getJSONObject("location")
					.getDouble("lng");
			lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
					.getJSONObject("geometry").getJSONObject("location")
					.getDouble("lat");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Toast.makeText(ManageAccount.this, lat + ", " + lon, Toast.LENGTH_LONG).show();
		Intent map = new Intent().setClass(this, Map.class);
		map.putExtra("lat", lat);
		map.putExtra("lon", lon);
		startActivity(map);
	}
}