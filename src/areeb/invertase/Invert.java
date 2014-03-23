package areeb.invertase;


//import com.actionbarsherlock.view.Menu;
//import com.actionbarsherlock.view.MenuItem;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.text.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Spanned;
import areeb.invertase.R;

@SuppressWarnings({ "deprecation", "unused" })
@SuppressLint("DefaultLocale")
public class Invert extends Activity {

	public boolean firsttime = true;
	public SharedPreferences first;
	public SharedPreferences.Editor firsteditor;

	String string;
	String color;
	String stringInvert;
	TextView tv1;
	TextView tv2;
	EditText edt;
	int i;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		// abs.setSplitBackgroundDrawable(getResources().getDrawable(R.drawable.abs__ab_bottom_solid_inverse_holo));

		

		first = getSharedPreferences("InveraseStat", 0);
		firsttime = first.getBoolean("firsttime", true);

		i = first.getInt("Launches", 0);
		i++;

		if (firsttime == true) {

			final TextView tv = (TextView) findViewById(R.id.TV1);

			final Handler handler = new Handler();

			tv.setText("Invert + Ease =");

			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					tv.setText("Invertase");

				}
			}, 5000);

			firsttime = false;

		}
		
		LinearLayout oldCode = (LinearLayout) findViewById(R.id.OldCode);
		oldCode.setOnLongClickListener(new View.OnLongClickListener() {
			
			
			
			@SuppressLint("NewApi")
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				int sdk = android.os.Build.VERSION.SDK_INT;
				
				if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB){
				
				
				android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE);
				
				clipboard.setText("#" + string);
				} else {
					
					android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
					ClipData clip = ClipData.newPlainText("Invertase Old", "#" + string);
					
					clipboard.setPrimaryClip(clip);
					
				}
				
				IcsToast.makeText(getApplicationContext(), "Old Code Copied", IcsToast.LENGTH_SHORT).show();
				
				
				
				return false;
			}
		});
 
		LinearLayout newCode = (LinearLayout) findViewById(R.id.NewCode);
		newCode.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub

				edt.setText(stringInvert);
				btn(view);

			}
		});
		
		
		newCode.setOnLongClickListener(new View.OnLongClickListener() {
			
			
			@SuppressLint("NewApi")
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				
                int sdk = android.os.Build.VERSION.SDK_INT;
				
				if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB){
				
				
				android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE);
				
				clipboard.setText("#" + stringInvert);
				} else {
					
					android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
					ClipData clip = ClipData.newPlainText("Invertase Old", "#" + stringInvert);
					
					clipboard.setPrimaryClip(clip);
					
				}
				
				
				IcsToast.makeText(getApplicationContext(), "New Code Copied", IcsToast.LENGTH_SHORT).show();
				
				return false;
			}
		});

		InputFilter input = new InputFilter() {
			@Override
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				if (source instanceof SpannableStringBuilder) {
					SpannableStringBuilder sourceAsSpannableBuilder = (SpannableStringBuilder) source;
					for (int i = end - 1; i >= start; i--) {
						char currentChar = source.charAt(i);

						String allowed = Character.toString(currentChar);
						if (!allowed.matches("^[a-fA-F0-9]+$")) {

							sourceAsSpannableBuilder.delete(i, i + 1);
						}

						else if (!Character.isLetterOrDigit(currentChar)
								&& !Character.isSpaceChar(currentChar)) {

							sourceAsSpannableBuilder.delete(i, i + 1);
						}
					}
					return source;
				} else {
					StringBuilder filteredStringBuilder = new StringBuilder();
					for (int i = start; i < end; i++) {
						char currentChar = source.charAt(i);

						String allowed = Character.toString(currentChar);
						if (!allowed.matches("[a-fA-F0-9]+$")) {

							filteredStringBuilder.append(currentChar);
						}

						else if (Character.isLetterOrDigit(currentChar)
								|| Character.isSpaceChar(currentChar)) {

							filteredStringBuilder.append(currentChar);

						}
					}
					return filteredStringBuilder.toString();
				}
			}
		};

		edt = (EditText) findViewById(R.id.ET1);
		edt.setFilters(new InputFilter[] { input,
				new InputFilter.LengthFilter(8) });
		edt.setSingleLine();

		edt.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View view, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press

					btn(view);
					return true;
				}
				return false;
			}
		});
		
		
		
		ImageView img = (ImageView) findViewById(R.id.closeIc);
		img.setOnLongClickListener(new View.OnLongClickListener(){

				@Override
				public boolean onLongClick(View view)
				{

					/*TextView tv = new TextView(getApplicationContext());
					tv.setText("Close");
					tv.setTypeface(Typeface.DEFAULT, 0);
					tv.setTextColor(Color.WHITE);
					tv.setPadding(10, 10, 10, 10);
					tv.setBackgroundResource(R.drawable.abs__toast_frame);
					//tv.setShadowLayer(1f, 1, 1, Color.parseColor("#ff555555"));


					Toast toast = new Toast(getApplicationContext());
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(tv);
					toast.setGravity(Gravity.BOTTOM, 0, 50);
					toast.show();*/
					
					longClick(view, "Close", 0);


					return true;
				}



			});



		ImageView imc = (ImageView) findViewById(R.id.queIc);
		imc.setOnLongClickListener(new View.OnLongClickListener(){

				@Override
				public boolean onLongClick(View view)
				{

					/*TextView tv = new TextView(getApplicationContext());
					tv.setText("Info");
					tv.setTypeface(Typeface.DEFAULT, 0);
					tv.setTextColor(Color.WHITE);
					tv.setPadding(10, 10, 10, 10);
					tv.setBackgroundResource(R.drawable.abs__toast_frame);
					//tv.setShadowLayer(1f, 1, 1, Color.parstv.setBackgroundResource(R.drawable.toast);eColor("#ff555555"));




					Toast toast = new Toast(getApplicationContext());
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(tv);
					toast.setGravity(Gravity.BOTTOM, 100, 50);
					toast.show();*/
					
					longClick(view, "Info", 100);

					return true;
				}



			});




		ImageView ims = (ImageView) findViewById(R.id.queSh);
		ims.setOnLongClickListener(new View.OnLongClickListener(){

				@Override
				public boolean onLongClick(View v)
				{

					longClick(v, "About", -100);
					
					return true;
				}



			});
		
		

	}
	
	
	public void longClick(View view, String string, int i){
		
		

        
        final int height = view.getHeight();
        

        Toast cheatSheet = IcsToast.makeText(this, string, IcsToast.LENGTH_SHORT);
        //if (midy < displayFrame.height()) {
            // Show along the top; follow action buttons
            cheatSheet.setGravity(Gravity.BOTTOM,
                    i, height);
        //} else {
            // Show along the bottom center
            //cheatSheet.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
       // }
        cheatSheet.show();
		
		
		
		
	}

	/*
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { switch
	 * (item.getItemId()) { case (R.id.exit):
	 * 
	 * finish();
	 * 
	 * break;
	 * 
	 * } return (super.onOptionsItemSelected(item)); }
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * getSupportMenuInflater().inflate(R.menu.main, menu); return true; }
	 */

	@Override
	protected void onStop() {
		super.onStop();

		firsteditor = first.edit();
		firsteditor.putBoolean("firsttime", firsttime).commit();
		firsteditor.putInt("Launches", i).commit();
	}

	@SuppressLint("DefaultLocale")
	public void btn(View view) {

		tv1 = (TextView) findViewById(R.id.TV);
		tv2 = (TextView) findViewById(R.id.TVOld);

		string = edt.getText().toString();

		if (string.matches("^[a-fA-F0-9]+$")) {

			if (string.length() == 6) {

			} else if (string.length() == 8) {

			} else {

				if (string != "") {
					IcsToast.makeText(this, "Not a valid hex code!",
							IcsToast.LENGTH_SHORT).show();
					return;
				}

			}
			string = string.toUpperCase();
			color = "#" + string;
			tv2.setText(color);
			// tv2.setTextColor(Color.parseColor(color));

			LinearLayout ll = (LinearLayout) findViewById(R.id.LL1);
			ll.setBackgroundColor(Color.parseColor(color));

			ll.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					IcsToast.makeText(getApplicationContext(),
							color + " | #" + stringInvert, IcsToast.LENGTH_SHORT)
							.show();
				}
			});

			LinearLayout ll2 = (LinearLayout) findViewById(R.id.hiddenLayout);
			ll2.setVisibility(View.VISIBLE);

			invertHex();

		} else {

			if (string != "") {
				IcsToast.makeText(this, "Not a valid hex code", IcsToast.LENGTH_LONG)
						.show();

			}

		}

	}

	public void invertHex() {

		stringInvert = string.replace("F", "null").replace("E", "one")
				.replace("D", "two").replace("C", "three").replace("B", "four")
				.replace("A", "five").replace("9", "six").replace("8", "seven")
				.replace("7", "8").replace("6", "9").replace("5", "A")
				.replace("4", "B").replace("3", "C").replace("2", "D")
				.replace("1", "E").replace("0", "F").replace("null", "0")
				.replace("one", "1").replace("two", "2").replace("three", "3")
				.replace("four", "4").replace("five", "5").replace("six", "6")
				.replace("seven", "7");

		// tv1.setTextColor(Color.parseColor(color));
		tv1.setText("#" + stringInvert);
	}

	public void closeMenu(View view) {

		finish();

	}

	public void diagMenu(View view) {

		startActivity(new Intent(this, InformationActivity.class));

	}
	
	
	public void sharerMenu(View view){
		
		startActivity(new Intent(this, SharerLayoutActivity.class));
	}

	

	
	
	

	

}
