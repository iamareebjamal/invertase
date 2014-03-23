package areeb.invertase;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import areeb.invertase.R;

public class InformationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		
		TextView linktext = (TextView) findViewById(R.id.TextViewLink);

		linktext.setText(Html.fromHtml(getResources().getString(R.string.message)));
		linktext.setMovementMethod(LinkMovementMethod.getInstance());
		
		final LinearLayout hiddenLayout = (LinearLayout) findViewById(R.id.hiddenCreditLayout);
		hiddenLayout.setVisibility(View.GONE);
		
		LinearLayout clickLayout =(LinearLayout) findViewById(R.id.creditLayout);
		clickLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (hiddenLayout.getVisibility() == View.GONE){
					
				     hiddenLayout.setVisibility(View.VISIBLE);
				     
				} else {
					
					hiddenLayout.setVisibility(View.GONE);
					
				}
			}
		});
		
		
	}

	

}
