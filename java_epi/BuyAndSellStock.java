package java_epi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmorales on 20/09/2016.
 */
public class BuyAndSellStock {

    public static double computeMaxProfit(List<Double> prices) {
        double minPrice = Double.MAX_VALUE; // this stores the minimum price in the list
        double maxProfit = 0.0; // this stores the current max profit per iteration

        for (Double price : prices) {
                // get the max between the current maxProfit and the difference between the current price and the current min price
            double currentPriceAndMinPriceDifference = price - minPrice;
            maxProfit = Math.max(maxProfit, currentPriceAndMinPriceDifference);
            minPrice = Math.min(minPrice, price);
        }

        return maxProfit;

    }

    public static void main(String[] args) {
        List<Double> prices = new ArrayList<>();

        prices.add(100.0);
        prices.add(20.0);
        prices.add(1.0);
        prices.add(80.00);
        prices.add(30.0);
        prices.add(15.0);
        prices.add(101.00);

        System.out.println("max profit: " + computeMaxProfit(prices));

    }

}
