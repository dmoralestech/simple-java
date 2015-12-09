import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dmorales on 9/12/2015.
 */
public class StockInfo {
    public final String ticker;
    public final BigDecimal price;

    public StockInfo(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public String toString() {
        return String.format("ticker: %s price: %g", ticker, price);
    }

    public static StockInfo getPrice(final String ticker) {
        return new StockInfo(ticker, new BigDecimal(3.0));
    }

    public static StockInfo pickHigh(final StockInfo stock1, final StockInfo stock2 ) {
        return stock1.price.compareTo(stock2.price) > 0 ? stock1 : stock2;
    }

    public static void main(String[] args) {
        List<String> Tickers = new ArrayList<>();
        Collector<StockInfo, ?, List<StockInfo>> c = Collectors.toList();
        final  List<StockInfo> stocks =  Tickers.stream()
                .map(ticker -> getPrice(ticker))
                .collect(c);

        final List<StockInfo> stocksPricedUnder500 = stocks.stream()
                .filter(isStockLessThan(BigDecimal.valueOf(500)))
                .collect(c);

    }

    private static Predicate<StockInfo> isStockLessThan(BigDecimal value) {
        return stockInfo -> stockInfo.price.compareTo(value) < 0;
    }

    public static void findHighPriced( final Stream<String> symbols) {
        final StockInfo highPriced = symbols.map(StockInfo::getPrice)
                .filter(StockInfo.isStockLessThan(BigDecimal.valueOf(500)))
                .reduce(StockInfo::pickHigh)
                .get();
    }




}
