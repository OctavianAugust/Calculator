package mironyuk.roma.calculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import logic.CorrectInput;
import logic.ParserArithmetic;
import logic.ParserException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener,
		AnimationListener {

	private static ViewPager pager;
	private static PagerAdapter pagerAdapter;
	static final String LOG_TAG = "Logs";
	private static String result_mrc = "";
	private static boolean mrc_logic = false;
	private static TextView calculateField;
	private static Button btn_clean;
	private static Button btn_m_plus;
	private static Button btn_m_minus;
	private static Button btn_mrc;
	private static Animation animationTextStart;
	private static Animation animationTextEnd;
	private static DecimalFormat formatDec;
	public static ParserArithmetic parserArith = new ParserArithmetic();

	public static Button getBtn_clean() {
		return btn_clean;
	}

	public static TextView getText_output() {
		return calculateField;
	}

	public static ViewPager getPager() {
		return pager;
	}

	public static Animation getAnimationTextStart() {
		return animationTextStart;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(LOG_TAG, "MainActivity onCreate");
		calculateField = (TextView) findViewById(R.id.calculateField);

		animationTextStart = AnimationUtils.loadAnimation(this,
				R.anim.textanimstart);
		animationTextStart.setAnimationListener(this);
		animationTextEnd = AnimationUtils.loadAnimation(this,
				R.anim.textanimend);

		btn_clean = (Button) findViewById(R.id.btn_clean);
		btn_m_plus = (Button) findViewById(R.id.btn_m_plus);
		btn_m_minus = (Button) findViewById(R.id.btn_m_minus);
		btn_mrc = (Button) findViewById(R.id.btn_mrc);
		btn_clean.setOnClickListener(this);
		btn_m_plus.setOnClickListener(this);
		btn_m_minus.setOnClickListener(this);
		btn_mrc.setOnClickListener(this);

		pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);

		DecimalFormatSymbols s = new DecimalFormatSymbols();
		s.setDecimalSeparator('.');
		formatDec = new DecimalFormat(".########", s);

		Intent intent = null;
		try {
			intent = new Intent("com.android.os.ServiceActivate");
			startService(intent);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (intent != null) {
				stopService(intent);
			}
		}
	}

	protected void onStart() {
		super.onStart();
		Log.d(LOG_TAG, "MainActivity onStart");
	}

	protected void onResume() {
		super.onResume();
		Log.d(LOG_TAG, "MainActivity onResume");
	}

	protected void onPause() {
		super.onPause();
		Log.d(LOG_TAG, "MainActivity onPause");
	}

	protected void onStop() {
		super.onStop();
		Log.d(LOG_TAG, "MainActivity onStop");
	}

	protected void onDestroy() {
		super.onDestroy();
		Log.d(LOG_TAG, "MainActivity onDestroy");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_clean:
			Log.d(LOG_TAG, "onClick");
			if (btn_clean.getText().equals("C")) {
				CorrectInput.cleanAll();
				CorrectInput.setClean_logic(false);
			} else {
				CorrectInput.cleanOne(1);
			}
			calculateField.setText(CorrectInput.getText());
			btn_clean.setText("<");
			break;
		case R.id.btn_m_plus:

			String add_mrc = (String) calculateField.getText();
			boolean add_logic = false;
			Log.d(LOG_TAG, "btn_m_plus - " + add_mrc);
			for (int i = 0; i < add_mrc.length(); i++) {
				if (add_mrc.charAt(i) >= '0' && add_mrc.charAt(i) <= '9'
						|| add_mrc.charAt(i) == '.') {
					add_logic = true;
				} else {
					add_logic = false;
					break;
				}
			}

			if (add_logic) {
				if (result_mrc.equals("")) {
					result_mrc += add_mrc;
				} else {
					result_mrc += "+" + add_mrc;
				}
			}
			Log.d(LOG_TAG, "result_mrc - " + result_mrc);
			break;
		case R.id.btn_m_minus:
			result_mrc = "";
			break;
		case R.id.btn_mrc:

			if (!result_mrc.equals("")) {
				mrc_logic = true;
				calculateField.setText(" ");
				calculateField.startAnimation(animationTextStart);
			} else {
				CorrectInput.cleanAll();
				calculateField.setText(CorrectInput.getText());
			}
			break;
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Log.d(LOG_TAG, "onAnimationEnd");
		Log.e("Logs", CorrectInput.getText());
		String result_line = "";
		double result = 0;
		if (mrc_logic) {
			mrc_logic = false;
			result_line = result_mrc;
		} else {
			result_line = CorrectInput.getText();
		}
		try {
			result = parserArith.evaluate(parserArith.expression(result_line));
		} catch (ParserException e) {
			e.printStackTrace();
			Log.e(LOG_TAG, "ParserException catch" + e.getMessage());
			CorrectInput.setClean_logic(true);
			CorrectInput.cleanAll();
			btn_clean.setText("C");
			calculateField.setText("Syntax error");
			calculateField.startAnimation(animationTextEnd);
			return;
		} catch (StackOverflowError e) {
			e.printStackTrace();
			Log.e(LOG_TAG, "StackOverflowError catch" + e.getMessage());
			CorrectInput.setClean_logic(true);
			CorrectInput.cleanAll();
			btn_clean.setText("C");
			calculateField
					.setText("exceeded the maximum value of the factorial");
			calculateField.startAnimation(animationTextEnd);
			return;
		}

		CorrectInput.setClean_logic(true);
		CorrectInput.cleanAll();
		btn_clean.setText("C");
		CorrectInput.inputValidation(String.valueOf(result));
		Log.e("Logs", "Result - " + CorrectInput.getText());
		calculateField.setText(CorrectInput.getText());
		calculateField.startAnimation(animationTextEnd);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

	@Override
	public void onAnimationStart(Animation animation) {
	}

}
