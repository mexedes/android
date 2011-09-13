package senior_project.main;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class Worldmap extends MapActivity {
    /** Called when the activity is first created. */
	private LocationManager lm;
	private LocationListener locationListener;
	
	private MapView mapView;
	private MapController mc;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worldmap);
        
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, locationListener);
        mapView = (MapView) findViewById(R.id.mapview1);
        mc = mapView.getController();
    }

    public class MyLocationListener implements LocationListener {

    	public void onLocationChanged(Location loc) {
    		if (loc != null){
    			Toast.makeText(getBaseContext(), "Location changed : Lat: "+loc.getLatitude()+" Lng: "+loc.getLongitude(), Toast.LENGTH_SHORT).show();
    		}
    		
    		GeoPoint p = new GeoPoint((int)(loc.getLatitude()*1E6),(int)(loc.getLongitude()*1E6));
    		mc.animateTo(p);
    		mc.setZoom(16);
    		mapView.invalidate();
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
    
    protected boolean isRouteDisplayed(){
    	return false;
    }

}