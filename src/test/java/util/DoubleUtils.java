package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DoubleUtils {
    private static DecimalFormatSymbols symbol;
    public static String roundDouble(double oldValue){
        symbol = DecimalFormatSymbols.getInstance();
        symbol.setDecimalSeparator('.');
        return new DecimalFormat("#0.000000", symbol)
                .format(oldValue);
    }

    public static double generateRandomDoubleNumberLessThan(double maxValue){
        return Math.random() * maxValue;
    }
}
