import java.util.List;
import java.util.Optional;

/**
 * Created by dmorales on 10/12/2015.
 */
public interface CatalogService {

    default Optional<List<Catalog>> getCatalogs() {
        return Optional.empty();
    }

    default Optional<List<Section>> getSections(Catalog catalog) {
        return Optional.empty();
    }

    default Optional<List<Part>> getSectionParts(Section section){
        return Optional.empty();
    }

    default Optional<List<Part>> addPricingToParts(Optional<List<Part>> parts){
        return parts;
    }

    default Optional<List<Part>> addSupersessionFlags(Optional<List<Part>> parts){
        return parts;
    }


    default Optional<List<Part>> searchPart(String partNumberToSearch){
        return Optional.empty();
    }

}
