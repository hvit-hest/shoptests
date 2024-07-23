package org.restore.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class GoodsDetailsComparisonUtils {

    public static List<Integer> getNumberOfColor(String color) {
        Pattern pattern = Pattern.compile("\\d+");
        List<Integer> list = new ArrayList<Integer>();
        Matcher m = pattern.matcher(color);
        while (m.find()) {
            list.add(Integer.parseInt(m.group()));
        }
        return  list;
    }

    public static int  comparePrices (String priceOne, String priceTwo) {
        BigDecimal firstPrice = new BigDecimal(priceOne.replaceAll("[^0-9.]", "")).
                setScale(2, ROUND_HALF_UP );
        BigDecimal secondPrice = new BigDecimal(priceTwo.replaceAll("[^0-9.]", "")).
                setScale(2, ROUND_HALF_UP );

        return firstPrice.compareTo(secondPrice);
    }

    public static int  compareFonts (String priceOneFont, String priceTwoFont) {
        //Using comparePrices since no difference for the case. We just need numbers to compare
        return comparePrices (priceOneFont, priceTwoFont);
    }
}