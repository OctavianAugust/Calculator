package logic;

import java.math.BigDecimal;

public class Factorial {
	private static BigDecimal result;

	public static BigDecimal getFact() {
		return result;
	}

	public static void setFact(BigDecimal result) throws StackOverflowError {

		Factorial.result = getFactorial(result);
	}

	public static BigDecimal getFactorial(BigDecimal num) throws StackOverflowError{
		if (num.intValue() == 0 || num.intValue() == 1) {
			return BigDecimal.valueOf(1);
		}

		return num.multiply(getFactorial(num.subtract(BigDecimal.valueOf(1))));
	}

}
