package senior_project.main;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.OverlayItem;

public class HelloItemizedOverlay extends
		com.google.android.maps.ItemizedOverlay<OverlayItem> {
	private Context myContext;
	private ArrayList<OverlayItem> overlayItems = new ArrayList<OverlayItem>();

	public HelloItemizedOverlay(Drawable defaultMarker, Context _context) {
		super(boundCenterBottom(defaultMarker));
		myContext = _context;
	}

	public void addOverlayItem(OverlayItem item) {
		overlayItems.add(item);
		populate();
	}

	@Override
	protected OverlayItem createItem(int index) {
		return overlayItems.get(index);
	}

	@Override
	public int size() {
		return overlayItems.size();
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = overlayItems.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(myContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}

}
