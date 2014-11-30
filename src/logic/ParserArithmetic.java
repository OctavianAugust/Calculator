package logic;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class ParserArithmetic {

	final String REGEX = "sin|cos|tan|ln|log|!|π|e|√";
	private static String expression = null;
	final static String LOG_TAG = "Logs";
	// Объявление лексем
	final int NONE = 0; // FAIL
	final int DELIMITER = 1; // Разделитель(+-*/^=, ")", "(" )
	final int VARIABLE = 2; // Переменная
	final int NUMBER = 3; // Число

	// Объявление констант синтаксических ошибок
	final int SYNTAXERROR = 0; // Синтаксическая ошибка (10 + 5 6 / 1)
	final int UNBALPARENS = 1; // Несовпадение количества открытых и закрытых
								// скобок
	final int NOEXP = 2; // Отсутствует выражение при запуске анализатора
	final int DIVBYZERO = 3; // Ошибка деления на ноль

	// Лексема, определяющая конец выражения
	final String EOF = "\0";

	private String exp; // Ссылка на строку с выражением
	private int index; // Текущий индекс в выражении
	private String token; // Сохранение текущей лексемы
	private int tokType; // Сохранение типа лексемы

	public String toString() {
		return String.format(
				"Exp = {0}\nexplds = {1}\nToken = {2}\nTokType = {3}",
				exp.toString(), index, token.toString(), tokType);
	}

	// Получить следующую лексему
	private void getToken() {
		tokType = NONE;
		token = "";

		// Проверка на окончание выражения
		if (index == exp.length()) {
			token = EOF;
			return;
		}
		// Проверка на пробелы, если есть пробел - игнорируем его.
		while (index < exp.length()
				&& Character.isWhitespace(exp.charAt(index)))
			++index;
		// Проверка на окончание выражения
		if (index == exp.length()) {
			token = EOF;
			return;
		}
		if (isDelim(exp.charAt(index))) {
			token += exp.charAt(index);
			index++;
			tokType = DELIMITER;
		} else if (Character.isLetter(exp.charAt(index))) {
			while (!isDelim(exp.charAt(index))) {
				token += exp.charAt(index);
				index++;
				if (index >= exp.length())
					break;
			}
			tokType = VARIABLE;
		} else if (Character.isDigit(exp.charAt(index))) {
			while (!isDelim(exp.charAt(index))) {
				token += exp.charAt(index);
				index++;
				if (index >= exp.length())
					break;
			}
			tokType = NUMBER;
		} else {
			token = EOF;
			return;
		}
	}

	private boolean isDelim(char charAt) {
		if ((" +-/*%^=()".indexOf(charAt)) != -1)
			return true;
		return false;
	}

	// Точка входа анализатора
	public double evaluate(String expstr) throws ParserException {

		double result;

		exp = expstr;
		index = 0;
		getToken();

		if (token.equals(EOF))
			handleErr(NOEXP); // Нет выражения

		// Анализ и вычисление выражения
		result = evalExp2();

		if (!token.equals(EOF))
			handleErr(SYNTAXERROR);

		return result;
	}

	// Сложить или вычислить два терма
	private double evalExp2() throws ParserException {

		char op;
		double result;
		double partialResult;
		result = evalExp3();
		while ((op = token.charAt(0)) == '+' || op == '-') {
			getToken();
			partialResult = evalExp3();
			switch (op) {
			case '-':
				result -= partialResult;
				break;
			case '+':
				result += partialResult;
				break;
			}
		}
		return result;
	}

	// Умножить или разделить два фактора
	private double evalExp3() throws ParserException {

		char op;
		double result;
		double partialResult;

		result = evalExp4();
		while ((op = token.charAt(0)) == '*' || op == '/' | op == '%') {
			getToken();
			partialResult = evalExp4();
			switch (op) {
			case '*':
				result *= partialResult;
				break;
			case '/':
				if (partialResult == 0.0)
					handleErr(DIVBYZERO);
				result /= partialResult;
				break;
			case '%':
				if (partialResult == 0.0)
					handleErr(DIVBYZERO);
				result %= partialResult;
				break;
			}
		}
		return result;
	}

	// Выполнить возведение в степень
	private double evalExp4() throws ParserException {

		double result;
		double partialResult;
		double ex;
		int t;
		result = evalExp5();
		if (token.equals("^")) {
			getToken();
			partialResult = evalExp4();
			ex = result;
			if (partialResult == 0.0) {
				result = 1.0;
			} else
				for (t = (int) partialResult - 1; t > 0; t--)
					result *= ex;
		}
		return result;
	}

	// Определить унарные + или -
	private double evalExp5() throws ParserException {
		double result;

		String op;
		op = " ";

		if ((tokType == DELIMITER) && token.equals("+") || token.equals("-")) {
			op = token;
			getToken();
		}
		result = evalExp6();
		if (op.equals("-"))
			result = -result;
		return result;
	}

	// Обработать выражение в скобках
	private double evalExp6() throws ParserException {
		double result;

		if (token.equals("(")) {
			getToken();
			result = evalExp2();
			if (!token.equals(")"))
				handleErr(UNBALPARENS);
			getToken();
		} else
			result = atom();
		return result;
	}

	// Получить значение числа
	private double atom() throws ParserException {

		double result = 0.0;
		switch (tokType) {
		case NUMBER:
			try {
				result = Double.parseDouble(token);
			} catch (NumberFormatException exc) {
				handleErr(SYNTAXERROR);
			}
			getToken();

			break;
		default:
			handleErr(SYNTAXERROR);
			break;
		}
		return result;
	}

	// Кинуть ошибку
	private void handleErr(int nOEXP2) throws ParserException {

		String[] err = { "Syntax error", "Unbalanced Parentheses",
				"No Expression Present", "Division by zero" };
		Log.e(LOG_TAG, "handleErr   ---   " + nOEXP2);
		throw new ParserException(err[nOEXP2]);
	}

	public String expression(String str) throws ParserException {

		expression = str;
		Matcher m = Pattern.compile(REGEX).matcher(str);
		while (m.find()) {
			fragmentation(m.group(), m.group().length());

		}

		return expression;

	}

	private void fragmentation(String sign, int index) throws ParserException,
			StackOverflowError {

		String exp_fragment = "";
		String exp = "";
		int i = expression.indexOf(sign) + index;
		int bracket = 0;
		BigDecimal singleDecimal = BigDecimal.ZERO;
		BigDecimal resultDecimal = BigDecimal.ZERO;
		double result2 = 0;
		int f = 0;
		char c;
		boolean flag = false;
		for (; i < expression.length(); i++) {
			c = expression.charAt(i);
			if (c == '(' || (c >= '0' && c <= '9')) {
				bracket++;
			}
			if (bracket == 0) {
				break;
			}
			exp_fragment += c;
			if (c == ')' || (c >= '0' && c <= '9')) {
				bracket--;
			}
		}

		if (bracket != 0) {
			exp_fragment += ")";
		}
		if (sign.equals("sin")) {
			resultDecimal = singleDecimal.add(BigDecimal.valueOf(Math
					.sin(evaluate(exp_fragment))));
		} else if (sign.equals("cos")) {
			resultDecimal = singleDecimal.add(BigDecimal.valueOf(Math
					.cos(evaluate(exp_fragment))));
		} else if (sign.equals("tan")) {
			resultDecimal = singleDecimal.add(BigDecimal.valueOf(Math
					.tan(evaluate(exp_fragment))));
		} else if (sign.equals("ln")) {
			resultDecimal = singleDecimal.add(BigDecimal.valueOf(Math
					.log(evaluate(exp_fragment))));
		} else if (sign.equals("log")) {
			resultDecimal = singleDecimal.add(BigDecimal.valueOf(Math
					.log10(evaluate(exp_fragment))));
		} else if (sign.equals("!")) {
			for (int j = i - 2; j >= 0; j--) {
				c = expression.charAt(j);
				if (c >= '0' && c <= '9') {

					exp_fragment += c;
				} else {
					break;
				}

			}
			f++;
			double fact = Double.parseDouble((new StringBuffer(exp_fragment)
					.reverse().toString()));
			if(fact > 170){
				fact = 171;
			}
			Factorial.setFact(BigDecimal.valueOf(fact));
			resultDecimal = singleDecimal.add(Factorial.getFact());
		} else if (sign.equals("π")) {
			resultDecimal = singleDecimal.add(BigDecimal.valueOf(Math.PI));
		} else if (sign.equals("e")) {
			resultDecimal = singleDecimal.add(BigDecimal.valueOf(Math.E));
		} else if (sign.equals("√")) {
			resultDecimal = singleDecimal.add(BigDecimal.valueOf(Math
					.sqrt(evaluate(exp_fragment))));
		}

		exp += expression.substring(0, expression.indexOf(sign) - f);
		exp += String.valueOf(resultDecimal);
		exp += expression.substring(i, expression.length());
		expression = exp;
	}

} // "sin|cos|tan|ln|log|!|π|e|√";