package com.areeb.invertase;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.app.Activity;

public class InformationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		
		TextView linktext = (TextView) findViewById(R.id.TextViewLink);

		linktext.setText(Html.fromHtml(getResources().getString(R.string.message)));
		linktext.setMovementMethod(LinkMovementMethod.getInstance());
		
	}

	

}
