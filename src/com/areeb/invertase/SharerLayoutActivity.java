package com.areeb.invertase;



import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class SharerLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.sharer_layout);
	}

	// This will return the Activity to the Main Screen when the app is in a Paused (not used) state
		@Override
		  public void onPause(){
			  super.onPause();
			  finish();
		  }

}
