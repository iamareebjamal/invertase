package areeb.invertase;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import areeb.invertase.R;

public class SharerLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.sharer_layout);

		final TextView fbtv = (TextView) findViewById(R.id.fbTV);
		fbtv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				fbtv.setBackgroundColor(Color.parseColor("#708bf0"));
				netHai("http://www.facebook.com/iamareebjamal");
			}
		});

		final TextView twtv = (TextView) findViewById(R.id.twTV);
		twtv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				twtv.setBackgroundColor(Color.parseColor("#6cdaff"));
				netHai("http://www.twitter.com/iamareebjamal");
			}
		});

		final TextView gptv = (TextView) findViewById(R.id.gpTV);
		gptv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				gptv.setBackgroundColor(Color.parseColor("#ff6c59"));
				netHai("http://plus.google.com/101187817179546867616");
			}
		});

		final TextView xdtv = (TextView) findViewById(R.id.xdTV);
		xdtv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				xdtv.setBackgroundColor(Color.parseColor("#876222"));

				netHai("http://forum.xda-developers.com/member.php?u=4782403");
			}
		});

	}

	public void marketRedirect(View view) {

		netHai("market://search?q=iamareebjamal");

	}

	public void tw(View view) {
		TextView twtv = (TextView) findViewById(R.id.twTV);
		twtv.setBackgroundColor(Color.parseColor("#6cdaff"));
		netHai("http://www.twitter.com/iamareebjamal");

	}

	public void gp(View view) {
		TextView gptv = (TextView) findViewById(R.id.gpTV);
		gptv.setBackgroundColor(Color.parseColor("#ff6c59"));

		netHai("http://plus.google.com/101187817179546867616");

	}

	public void xd(View view) {

		TextView xdtv = (TextView) findViewById(R.id.xdTV);
		xdtv.setBackgroundColor(Color.parseColor("#876222"));

		netHai("http://forum.xda-developers.com/member.php?u=4782403");

	}

	public void netHai(String string) {

		try {

			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(string)));

		} catch (ActivityNotFoundException e) {

			IcsToast.makeText(this, "No Browser Found", IcsToast.LENGTH_SHORT)
					.show();
		}

	}

}
