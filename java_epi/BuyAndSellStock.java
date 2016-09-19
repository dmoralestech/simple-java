package java_epi;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by dmorales on 20/09/2016.
 */
public class BuyAndSellStock {

    public static double computeMaxProfit(List<Double> prices) {
        double minPrice = Double.MAX_VALUE;
        double maxProfit = 0.0;

        for (Double price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }

        return maxProfit;

    }

    public static void main(String[] args) {
        List<Double> prices = new ArrayList<>();

        prices.add(100.0);
        prices.add(20.0);
        prices.add(80.00);
        prices.add(30.0);
        prices.add(15.0);
        prices.add(101.00);
        System.out.println("max profit: " + computeMaxProfit(prices));

    }
}
