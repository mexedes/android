package senior_project.main;


import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class FirstActivity extends MapActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_first);
		// get reference to the MapView
		MapView myMapView = (MapView) findViewById(R.id.myMapView);
		myMapView.setBuiltInZoomControls(true);

		List<Overlay> mapOverlays = myMapView.getOverlays();
		Drawable marker = this.getResources().getDrawable(R.drawable.cat);
		// create new Overlay
		HelloItemizedOverlay myOverlay = new HelloItemizedOverlay(marker, this);
		// create a new marker
		// Micro Degree = lat * 1M
		GeoPoint mexicoPoint = new GeoPoint(19240000, -99120000);
		OverlayItem mexicoOLItem = new OverlayItem(mexicoPoint,
				"Hola, Mexico!", "I'm in Mexico");
		
		GeoPoint bangkokPoint = new GeoPoint(13712388,100537702);
		OverlayItem bangkokOLItem = new OverlayItem(bangkokPoint,
				"Sawasdee, Thailand!", "I'm in UTK");
		
		// add the overlay item to our overlay
		myOverlay.addOverlayItem(mexicoOLItem);
		myOverlay.addOverlayItem(bangkokOLItem);
		// add the overlay to the list of all overlays
		mapOverlays.add(myOverlay);
		
		MapController myMapController = myMapView.getController();
		myMapController.setCenter(bangkokPoint);
		myMapController.setZoom(18); // Level 1 - 21 less then Zoom out 
		
		myMapView.setSatellite(true);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}