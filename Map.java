package senior_project.main;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class Map extends MapActivity {
	private MapView mapView;
	private MapController mapController;
	GeoPoint point;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		Double lat = getIntent().getExtras().getDouble("lat");
		Double lon = getIntent().getExtras().getDouble("lon");
		mapView = (MapView) findViewById(R.id.map_view);
		mapController = mapView.getController();
		lat = lat * 1E6;
		lon = lon * 1E6;
		point = new GeoPoint(lat.intValue(), lon.intValue());

		mapController.setCenter(point);
		mapController.setZoom(14);

		// ---Add a location marker---
		MapOverlay mapOverlay = new MapOverlay();
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.clear();
		listOfOverlays.add(mapOverlay);
		
		//mapView.invalidate();
	}

	class MapOverlay extends com.google.android.maps.Overlay {
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, shadow);

			// ---translate the GeoPoint to screen pixels---
			Point screenPts = new Point();
			mapView.getProjection().toPixels(point, screenPts);

			// ---add the marker---
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.pin);
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);
			return true;
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void back_clicked(View view) {
		finish();
	}
}
