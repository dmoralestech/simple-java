import java.util.List;
import java.util.Optional;

/**
 * Created by dmorales on 10/12/2015.
 */
public class PorscheCatalogService implements CatalogService  {


    public Optional<List<Part>> getPartsWithPrices(Section section) {
        return Optional.empty();
    }

    public static void main(String[] args) {
        PorscheCatalogService porscheCatalogService = new PorscheCatalogService();

    }

    @Override
    public Optional<List<Catalog>> getCatalogs() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Section>> getSections(Catalog catalog) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Part>> getSectionParts(Section section) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Part>> searchPart(String partNumberToSearch) {
        return Optional.empty();
    }

}
