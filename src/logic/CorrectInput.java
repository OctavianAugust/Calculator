package logic;

import android.util.Log;

public class CorrectInput {
	private static String text = "";
	private static boolean clean_logic = false;
	final static String LOG_TAG = "Logs";

	public static String getText() {
		return text;
	}

	public static void inputValidation(String inputText) {

		Log.d(LOG_TAG, "inputValidation");
		if (isSign(inputText)) {
			checkSign(inputText);
		} else if (inputText.charAt(inputText.length() - 1) >= '0'
				&& inputText.charAt(inputText.length() - 1) <= '9'
				|| inputText.charAt(inputText.length() - 1) == '.') {
			checkNumeric(inputText);
		} else if (inputText.length() >= 3) {
			checkTrig(inputText);
		} else if (inputText.charAt(inputText.length() - 1) == '!') {
			checkFactorial(inputText);
		} else {
			addText(inputText);
		}

	}

	private static void checkNumeric(String inputText) {

		boolean pointLogic = false;
		Log.d(LOG_TAG, "checkNumeric");
		if (inputText.charAt(inputText.length() - 1) == '.') {
			if (!text.equals("")) {
				char symbol = text.charAt(text.length() - 1);
				if (symbol >= '0' && symbol <= '9') {

					for (int i = text.length() - 1; i > -1; i--) {
						if (text.charAt(i) == '+' || text.charAt(i) == '-'
								|| text.charAt(i) == '*'
								|| text.charAt(i) == '/'
								|| text.charAt(i) == '^'
								|| text.charAt(i) == '%') {
							break;
						}
						if (text.charAt(i) == '.') {
							pointLogic = true;
							break;
						}

					}
					if (!pointLogic) {
						addText(inputText);
					}

				} else if (symbol == '+' || symbol == '-' || symbol == '*'
						|| symbol == '/' || symbol == '^' || symbol == '%') {
					addText("0");
					addText(inputText);
				}

			} else {
				addText("0");
				addText(inputText);
			}

		} else {
			addText(inputText);
		}
	}

	private static void checkSign(String inputText) {
		Log.d(LOG_TAG, "checkSign");
		if (isSign(text)) {
			cleanOne(1);
			addText(inputText);
		} else {
			if (text.equals("")) {
				if (inputText.charAt(inputText.length() - 1) == '-') {
					addText(inputText);
				}
			} else {
				addText(inputText);
			}
		}
	}

	private static void checkFactorial(String inputText) {

		if (!text.equals("") && text.charAt(text.length() - 1) >= '0'
				&& text.charAt(text.length() - 1) <= '9') {
			addText(inputText);
		}
	}

	private static void addText(String inputText) {
		text += inputText;
	}

	public static void cleanAll() {
		text = "";
	}

	public static void cleanLogic() {

		if (clean_logic && !(text.equals(""))) {
			char sign = text.charAt(text.length() - 1);
			if (!(sign == '+' || sign == '-' || sign == '*' || sign == '/'
					|| sign == '^' || sign == '%' || sign == '!' || sign == '.')) {
				text = "";
				Log.d(LOG_TAG, "cleanLogic");
			}
			clean_logic = false;
		}
	}

	public static boolean getClean_logic() {
		return clean_logic;

	}

	public static void setClean_logic(boolean clean_logic) {
		Log.d(LOG_TAG, "setClean_logic");
		CorrectInput.clean_logic = clean_logic;
	}

	public static void cleanOne(int index) {
		if (!(text.equals(""))) {
			Log.d(LOG_TAG, "cleanOne");
			text = text.substring(0, text.length() - index);
		}
	}

	private static void checkTrig(String inputText) {
		Log.d(LOG_TAG, "checkTrig");
		if (!text.equals("") && text.length() >= 3) {
			if (text.charAt(text.length() - 3) == 'l'
					&& text.charAt(text.length() - 2) == 'n') {
				text = text.substring(0, text.length() - 3);
				addText(inputText);

			} else if (text.charAt(text.length() - 2) == 'n'
					|| text.charAt(text.length() - 2) == 's'
					|| text.charAt(text.length() - 2) == 'g') {
				text = text.substring(0, text.length() - 4);
				addText(inputText);

			} else {
				addText(inputText);
			}
		} else {
			addText(inputText);
		}

	}

	private static boolean isSign(String s) {
		return isSign(s, 1);
	}

	private static boolean isSign(String s, int i) {

		if (!(s.equals(""))) {
			char sign = s.charAt(s.length() - i);
			if (sign == '+' || sign == '-' || sign == '*' || sign == '/'
					|| sign == '^' || sign == '%') {
				return true;
			}
		}
		return false;
	}

}
