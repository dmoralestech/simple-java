import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public static void main(String[] args) {
        List<String> Tickers = new ArrayList<>();
        Collector<StockInfo, ?, List<StockInfo>> c = Collectors.toList();
        final  List<StockInfo> stocks =  Tickers.stream()
                .map(ticker -> getPrice(ticker))
                .collect(c);

        final List<StockInfo> stocksPricedUnder500 = stocks.stream()
                .filter(stockInfo -> stockInfo.price.compareTo(BigDecimal.valueOf(500)) < 0)
                .collect(c);

    }




}
