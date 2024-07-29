package org.restore.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class Utils {

    public static List<Integer> getNumberOfColor(String color) {
        Pattern pattern = Pattern.compile("\\d+");
        List<Integer> list = new ArrayList<Integer>();
        Matcher m = pattern.matcher(color);
        while (m.find()) {
            list.add(Integer.parseInt(m.group()));
        }
        return list;
    }

    public static int comparePrices(String priceOne, String priceTwo) {
        BigDecimal firstPrice = new BigDecimal(priceOne.replaceAll("[^0-9.]", "")).
                setScale(2, RoundingMode.HALF_UP);
        BigDecimal secondPrice = new BigDecimal(priceTwo.replaceAll("[^0-9.]", "")).
                setScale(2, RoundingMode.HALF_UP);

        return firstPrice.compareTo(secondPrice);
    }

    public static int compareFonts(String priceOneFont, String priceTwoFont) {
        //Using comparePrices since no difference for the case. We just need numbers to compare
        return comparePrices(priceOneFont, priceTwoFont);
    }

    public static String emailGenerator() {
        String[] forMail = UUID.randomUUID().toString().split("-");
        String topDomain = RandomStringUtils.randomAlphabetic(2, 8);
        return String.format("user%s@mail%s.%s", forMail[0] + forMail[1], forMail[2] + forMail[3], topDomain);
    }

    public static String nameGenerator() {
        return StringUtils.capitalize(RandomStringUtils.randomAlphabetic(1, 20).toLowerCase());
    }

    public static String addressGenerator() {
        return wordsGenerator();
    }

    public static String wordsGenerator() {
        return String.format("%s %s %s %s",
                RandomStringUtils.randomAlphanumeric(1, 15),
                RandomStringUtils.randomAlphanumeric(1, 15),
                RandomStringUtils.randomAlphanumeric(1, 15),
                RandomStringUtils.randomAlphanumeric(1, 15));
    }

    public static String phoneGenerator() {
        return String.format("+%s", RandomStringUtils.randomNumeric(8));
    }


    public static ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    public static String getLinkAsString (WebElement href) {
        return href.getAttribute("href");
    }
}