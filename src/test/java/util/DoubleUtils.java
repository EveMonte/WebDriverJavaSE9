package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DoubleUtils {
    private static DecimalFormatSymbols symbol;
    public static double roundDouble(double oldValue){
        symbol = DecimalFormatSymbols.getInstance();
        symbol.setDecimalSeparator('.');
        return Double.parseDouble(new DecimalFormat("#0.00000", symbol)
                .format(oldValue));
    }
}
