package AbstractFactory;

/**
 * Created by darwinmorales on 9/11/2016.
 */
public interface EPCFactory {
    Catalog createCatalog();
    Section createSection();
    Part createPart();
}
