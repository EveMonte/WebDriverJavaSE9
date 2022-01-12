package util;

import java.util.Random;

public class StringUtils
{
    private static final String ALFANUMERICAL_ALL_CAPS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random random = new Random();

    public static String getRandomString(int stringLength)
    {
        StringBuilder stringBuilder = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++)
        {
            stringBuilder.append(ALFANUMERICAL_ALL_CAPS.charAt(random.nextInt(ALFANUMERICAL_ALL_CAPS.length())));
        }
        return stringBuilder.toString();
    }

    public static String generateRandomNetworkNameWithPostfixLength(int postfixLength){
        return "testNetwork_".concat(getRandomString(postfixLength));
    }
    public static String generateRandomTokenSymbolWithPostfixLength(int postfixLength){
        return getRandomString(postfixLength);
    }

    public static String generateRandomContactNameWithPostfixLength(int postfixLength){
        return "testContact_".concat(getRandomString(postfixLength));
    }

}
