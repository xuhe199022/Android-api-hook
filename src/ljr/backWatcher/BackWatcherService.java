package ljr.backWatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class BackWatcherService extends IntentService {
	public BackWatcherService() {
		super("BackWatcherService");
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "Service Destroy");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		try {
			Runtime.getRuntime().exec(new String[] { "logcat", "-c" });
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Process mLogcatProc = null;
		BufferedReader reader = null;

		Log.i(TAG, "I'm running");
		try {
			mLogcatProc = Runtime.getRuntime().exec(
					new String[] { "logcat", "SendMessage:I *:S" });
			reader = new BufferedReader(new InputStreamReader(
					mLogcatProc.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				final String info = line;
				
				Handler toastHandler = new Handler(getMainLooper());
				toastHandler.post(new Runnable(){
					public void run(){
						Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();					
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String TAG = "BackWatcherService";
}
