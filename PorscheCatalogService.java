import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by dmorales on 10/12/2015.
 */
public class PorscheCatalogService implements CatalogService  {


    public PorscheCatalogService() {
    }

    public Optional<List<Part>> getPartsWithPrices(Section section) {
        return Optional.empty();
    }

    public static void main(String[] args) {
        Supplier<PorscheCatalogService> p = PorscheCatalogService::new;
        Supplier<PorscheCatalogService> p2 = () -> new PorscheCatalogService();


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
