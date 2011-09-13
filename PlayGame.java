package senior_project.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class PlayGame extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playgame);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, FirstActivity.class);
        spec = tabHost.newTabSpec("Map").setIndicator("Map")
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SecondActivity.class);
        spec = tabHost.newTabSpec("Status").setIndicator("Status")
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, ThirdActivity.class);
        spec = tabHost.newTabSpec("FriendList").setIndicator("FriendList")
                      .setContent(intent);
        tabHost.addTab(spec);

      
    }
}