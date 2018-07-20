package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateEditoTest{

    public static void main(String[] args){
        String FULL_TIME_REG           = "\\d{4}(-\\d{2}){2} \\d{2}(:\\d{2}){2}";
        String DATE_WITH_CONNECTOR_REG = "\\d{4}(-\\d{2}){2}";
        String DATE_WITH_SLASH_REG     = "\\d{4}(/\\d{2}){2}";

        Matcher matcher = Pattern.compile(DATE_WITH_SLASH_REG).matcher("2018/08");
        System.out.println(matcher.matches());
    }
}
