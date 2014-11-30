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

public class CustomFragment extends Fragment implements OnClickListener {

	final String LOG_TAG = "Logs";
	Button btn_sin;
	Button btn_cos;
	Button btn_tan;
	Button btn_ln;
	Button btn_log;
	Button btn_factorial;
	Button btn_pi;
	Button btn_exp;
	Button btn_invol;
	Button btn_left_b;
	Button btn_right_b;
	Button btn_sqrt;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(LOG_TAG, "CustomFragment onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(LOG_TAG, "CustomFragment onCreate");

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d(LOG_TAG, "CustomFragment onActivityCreated");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_custom_button,
				container, false);
		Log.d(LOG_TAG, "CustomFragment onCreateView");
		btn_sin = (Button) view.findViewById(R.id.btn_sin);
		btn_cos = (Button) view.findViewById(R.id.btn_cos);
		btn_tan = (Button) view.findViewById(R.id.btn_tan);
		btn_ln = (Button) view.findViewById(R.id.btn_ln);
		btn_log = (Button) view.findViewById(R.id.btn_log);
		btn_factorial = (Button) view.findViewById(R.id.btn_factorial);
		btn_pi = (Button) view.findViewById(R.id.btn_pi);
		btn_exp = (Button) view.findViewById(R.id.btn_exp);
		btn_invol = (Button) view.findViewById(R.id.btn_involution);
		btn_left_b = (Button) view.findViewById(R.id.btn_left_bracket);
		btn_right_b = (Button) view.findViewById(R.id.btn_right_bracket);
		btn_sqrt = (Button) view.findViewById(R.id.btn_sqrt);

		btn_sin.setOnClickListener(this);
		btn_cos.setOnClickListener(this);
		btn_tan.setOnClickListener(this);
		btn_ln.setOnClickListener(this);
		btn_log.setOnClickListener(this);
		btn_factorial.setOnClickListener(this);
		btn_pi.setOnClickListener(this);
		btn_exp.setOnClickListener(this);
		btn_invol.setOnClickListener(this);
		btn_left_b.setOnClickListener(this);
		btn_right_b.setOnClickListener(this);
		btn_sqrt.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sin:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("sin(");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_cos:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("cos(");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);

			break;
		case R.id.btn_tan:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("tan(");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_ln:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("ln(");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_log:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("log(");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_factorial:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("!");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_pi:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("π");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_exp:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("e");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_involution:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("^");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_left_bracket:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("(");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_right_bracket:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation(")");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		case R.id.btn_sqrt:
			CorrectInput.cleanLogic();
			MainActivity.getBtn_clean().setText("←");
			CorrectInput.inputValidation("√");
			MainActivity.getText_output().setText(CorrectInput.getText());
			MainActivity.getPager().setCurrentItem(0);
			break;
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(LOG_TAG, "CustomFragment onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(LOG_TAG, "CustomFragment onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(LOG_TAG, "CustomFragment onPause");
	}

	public void onStop() {
		super.onStop();
		Log.d(LOG_TAG, "CustomFragment onStop");
	}

	public void onDestroyView() {
		super.onDestroyView();
		Log.d(LOG_TAG, "CustomFragment onDestroyView");
	}

	public void onDestroy() {
		super.onDestroy();
		Log.d(LOG_TAG, "CustomFragment onDestroy");
	}

	public void onDetach() {
		super.onDetach();
		Log.d(LOG_TAG, "CustomFragment onDetach");
	}

}