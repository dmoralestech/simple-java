import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created by darwinmorales on 26/11/2015.
 */
public class CalculateNAV {

    private Function<String, BigDecimal> priceFinder;

    public BigDecimal computeStockWorth(final String ticker, final int shares) {
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }

    public CalculateNAV(final Function<String, BigDecimal> aPriceFinder) {
        priceFinder = aPriceFinder;
    }

}
