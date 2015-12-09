import java.util.List;

/**
 * Created by dmorales on 10/12/2015.
 */
public interface CatalogService {

    List<Catalog> getCatalogs();

    List<Section> getSections(Catalog catalog);

    List<Part> getSectionParts(Section section);

}
