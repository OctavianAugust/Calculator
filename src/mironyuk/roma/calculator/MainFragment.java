package mironyuk.roma.calculator;

import logic.CorrectInput;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements OnClickListener {

	Button button_1;
	Button button_2;
	Button button_3;
	Button button_4;
	Button button_5;
	Button button_6;
	Button button_7;
	Button button_8;
	Button button_9;
	Button button_ziro;
	Button button_multiply;
	Button button_divide;
	Button button_minus;
	Button button_plus;
	Button button_point;
	Button button_equally;

	static final String LOG_TAG = "Logs";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(LOG_TAG, "MainFragment onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(LOG_TAG, "MainFragment onCreate");

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d(LOG_TAG, "MainFragment onActivityCreated");

	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(LOG_TAG, "MainFragment onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(LOG_TAG, "MainFragment onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(LOG_TAG, "MainFragment onPause");
	}

	public void onStop() {
		super.onStop();
		Log.d(LOG_TAG, "MainFragment onStop");
	}

	public void onDestroyView() {
		super.onDestroyView();
		Log.d(LOG_TAG, "MainFragment onDestroyView");
	}

	public void onDestroy() {
		super.onDestroy();
		Log.d(LOG_TAG, "MainFragment onDestroy");
	}

	public void onDetach() {
		super.onDetach();
		Log.d(LOG_TAG, "MainFragment onDetach");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main_button, null);
		Log.d(LOG_TAG, "MainFragment onCreateView");
		button_1 = (Button) view.findViewById(R.id.btn_1);
		button_2 = (Button) view.findViewById(R.id.btn_2);
		button_3 = (Button) view.findViewById(R.id.btn_3);
		button_4 = (Button) view.findViewById(R.id.btn_4);
		button_5 = (Button) view.findViewById(R.id.btn_5);
		button_6 = (Button) view.findViewById(R.id.btn_6);
		button_7 = (Button) view.findViewById(R.id.btn_7);
		button_8 = (Button) view.findViewById(R.id.btn_8);
		button_9 = (Button) view.findViewById(R.id.btn_9);
		button_ziro = (Button) view.findViewById(R.id.btn_ziro);
		button_multiply = (Button) view.findViewById(R.id.btn_multiply);
		button_divide = (Button) view.findViewById(R.id.btn_divide);
		button_minus = (Button) view.findViewById(R.id.btn_minus);
		button_plus = (Button) view.findViewById(R.id.btn_plus);
		button_point = (Button) view.findViewById(R.id.btn_point);
		button_equally = (Button) view.findViewById(R.id.btn_equally);

		button_1.setOnClickListener(this);
		button_2.setOnClickListener(this);
		button_3.setOnClickListener(this);
		button_4.setOnClickListener(this);
		button_5.setOnClickListener(this);
		button_6.setOnClickListener(this);
		button_7.setOnClickListener(this);
		button_8.setOnClickListener(this);
		button_9.setOnClickListener(this);
		button_ziro.setOnClickListener(this);
		button_multiply.setOnClickListener(this);
		button_divide.setOnClickListener(this);
		button_minus.setOnClickListener(this);
		button_plus.setOnClickListener(this);
		button_point.setOnClickListener(this);
		button_equally.setOnClickListener(this);
		button_minus.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_1:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("1");
			MainActivity.getText_output().setText(CorrectInput.getText());
			break;
		case R.id.btn_2:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("2");
			MainActivity.getText_output().setText(CorrectInput.getText());

			break;
		case R.id.btn_3:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("3");
			MainActivity.getText_output().setText(CorrectInput.getText());

			break;
		case R.id.btn_4:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("4");
			MainActivity.getText_output().setText(CorrectInput.getText());

			break;
		case R.id.btn_5:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("5");
			MainActivity.getText_output().setText(CorrectInput.getText());

			break;
		case R.id.btn_6:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("6");
			MainActivity.getText_output().setText(CorrectInput.getText());

			break;
		case R.id.btn_7:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("7");
			MainActivity.getText_output().setText(CorrectInput.getText());

			break;
		case R.id.btn_8:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("8");
			MainActivity.getText_output().setText(CorrectInput.getText());

			break;
		case R.id.btn_9:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("9");
			MainActivity.getText_output().setText(CorrectInput.getText());
			break;
		case R.id.btn_ziro:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("0");
			MainActivity.getText_output().setText(CorrectInput.getText());

			break;
		case R.id.btn_point:
			CorrectInput.inputValidation(".");
			MainActivity.getBtn_clean().setText("←");
			MainActivity.getText_output().setText(CorrectInput.getText());
			break;
		case R.id.btn_plus:
			CorrectInput.inputValidation("+");
			MainActivity.getBtn_clean().setText("←");
			MainActivity.getText_output().setText(CorrectInput.getText());

			break;
		case R.id.btn_minus:
			CorrectInput.inputValidation("-");
			MainActivity.getBtn_clean().setText("←");
			MainActivity.getText_output().setText(CorrectInput.getText());
			break;
		case R.id.btn_multiply:
			CorrectInput.inputValidation("*");
			MainActivity.getBtn_clean().setText("←");
			MainActivity.getText_output().setText(CorrectInput.getText());
			break;
		case R.id.btn_divide:
			CorrectInput.inputValidation("/");
			MainActivity.getBtn_clean().setText("←");
			MainActivity.getText_output().setText(CorrectInput.getText());
			break;
		case R.id.btn_equally:
			MainActivity.getText_output().startAnimation(
					MainActivity.getAnimationTextStart());
			break;
		}
	}
}