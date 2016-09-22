package java_epi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darwinmorales on 22/09/2016.
 */
public class BuyAndSellStockTwice {

    public static double buyAndSellStockTwice(List<Double> prices) {
        double maxTotalProfit = 0.0;
        List<Double> firstBuySellProfits = new ArrayList<>();
        double minPriceSoFar = Double.MAX_VALUE;

        for (int i = 0; i < prices.size(); ++i) {
            minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit, prices.get(i) - minPriceSoFar);
            firstBuySellProfits.add(maxTotalProfit);
        }

        double maxPriceSoFar = Double.MIN_VALUE;
        for (int i = prices.size() - 1; i > 0; --i) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            maxTotalProfit
                    = Math.max(maxTotalProfit, maxPriceSoFar - prices.get(i)
                    + firstBuySellProfits.get(i - 1));
        }
        return maxTotalProfit;
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

        System.out.println("max profit: " + buyAndSellStockTwice(prices));

    }

}
