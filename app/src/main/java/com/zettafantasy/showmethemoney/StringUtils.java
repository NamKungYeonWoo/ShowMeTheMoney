package com.zettafantasy.showmethemoney;

import java.text.DecimalFormat;

/**
 * @author zettafantasy
 */

public class StringUtils {
    /**
     * 숫자를 3자리마다 comma를 찍어놓은 string으로 변환
     *
     * @param number
     * @return formatted number string (ex 0,000)
     */
    public final static String makeNumberComma(long number) {
        return (new DecimalFormat("#,##0")).format(number);
    }
}
