package senior_project.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class GetUSERLocation extends Main {
    /** Called when the activity is first created. */
	private LocationManager lm;
	private LocationListener locationListener;
	protected Intent intentUSERResult;
	protected boolean getGPSCount = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getuserlocation);
        
        intentUSERResult = new Intent().setClass(this, USERResult.class);
        
        if(!getGPSCount){
            	lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                locationListener = new MyLocationListener();
            	lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, locationListener);
        }
        
    }

    public class MyLocationListener implements LocationListener {

    	public void onLocationChanged(Location loc) {
    		if (loc != null){
    			
    			userLatitude = (float)loc.getLatitude();
    			userLongitude = (float)loc.getLongitude();
    			
    			
    			Toast.makeText(getBaseContext(), "Location changed : Lat: "+userLatitude+" Lng: "+userLongitude, Toast.LENGTH_SHORT).show();
    	        	startActivity(intentUSERResult);

    		}
    	}

    	public void onProviderDisabled(String provider) {
    		// TODO Auto-generated method stub

    	}

    	public void onProviderEnabled(String provider) {
    		// TODO Auto-generated method stub

    	}

    	public void onStatusChanged(String provider, int status, Bundle extras) {
    		// TODO Auto-generated method stub

    	}
    }

}