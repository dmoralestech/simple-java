import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by darwinmorales on 1/12/2015.
 */
public class Part {

    private String partNumber;
    private String pnc;
    private String description;
    private String applicableMarkets;
    private String modelCodes;
    private String section;
    private String catalog;
    private double price;
    private boolean isApplicable;

    public boolean isApplicable() {
        return isApplicable;
    }

    public void setApplicable(boolean isApplicable) {
        this.isApplicable = isApplicable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPnc() {
        return pnc;
    }

    public void setPnc(String pnc) {
        this.pnc = pnc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplicableMarkets() {
        return applicableMarkets;
    }

    public void setApplicableMarkets(String applicableMarkets) {
        this.applicableMarkets = applicableMarkets;
    }

    public String getModelCodes() {
        return modelCodes;
    }

    public void setModelCodes(String modelCodes) {
        this.modelCodes = modelCodes;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public static void main(String[] args) {

        List<Part> parts = new ArrayList<>();

        parts.stream()
                .filter(part -> part.getApplicableMarkets().indexOf("AU") > 0)
                .forEach(part -> part.setApplicable(true));

        CatalogService catalogService = new PorscheCatalogService();

        Function<String, Optional<List<Part>>> searchForParts = catalogService::searchPart;

        Function<Optional<List<Part>>, Optional<List<Part>>> partsWithPricing = catalogService::addPricingToParts;
        Function<Optional<List<Part>>, Optional<List<Part>>> partsPipeLine = partsWithPricing.andThen(catalogService::addSupersessionFlags);

        Optional<List<Part>> finalResult = searchForParts
                                                .andThen(catalogService::addPricingToParts)
                                                .andThen(catalogService::addSupersessionFlags)
                                                .apply("123");

        Function<String, Optional<List<Part>>>  pipeLinePartSearch = searchForParts
                .andThen(catalogService::addPricingToParts)
                .andThen(catalogService::addSupersessionFlags);

        pipeLinePartSearch.apply("P1213");

        //finalResult = x.apply("123");

    }

}
