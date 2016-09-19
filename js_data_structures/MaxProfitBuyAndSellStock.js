/**
 * Created by dmorales on 20/09/2016.
 */

function computeMaxProfit(prices) {
    var minPrice = Number.MAX_VALUE; // this stores the minimum price in the array
    var maxProfit = 0.0; // this stores the current max profit per iteration

    prices.forEach(function (price) {
        // get the max between the current maxProfit and the difference between the current price and the current min price
        var differnceBetweenCurrentPriceAndMinPrice = price - minPrice;
        maxProfit = Math.max(maxProfit, differnceBetweenCurrentPriceAndMinPrice);
        minPrice = Math.min(minPrice, price);
    });

    return maxProfit;

}

var stockPrices = [];

stockPrices.push(10);
stockPrices.push(20);
stockPrices.push(30);
stockPrices.push(10);
stockPrices.push(100);
stockPrices.push(200);
stockPrices.push(0.5);

console.log("Max profit: ", computeMaxProfit(stockPrices));
