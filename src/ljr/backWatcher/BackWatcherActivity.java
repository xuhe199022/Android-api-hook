package ljr.backWatcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BackWatcherActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button start = (Button)findViewById(R.id.start);
        Button stop = (Button)findViewById(R.id.stop);
        
        start.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {		
				Intent serIntent = new Intent(BackWatcherActivity.this, BackWatcherService.class);
				startService(serIntent);
			} 	
        });
        
        stop.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				stopService(new Intent(BackWatcherActivity.this, BackWatcherService.class));
			}       	
        });
    }
}