package AbstractFactory;

/**
 * Created by darwinmorales on 9/11/2016.
 */
public class TeslaEPCFactory implements EPCFactory {

    @Override
    public Catalog createCatalog() {
        return new TeslaCatalog();
    }

    @Override
    public Section createSection() {
        return new TeslaSection();
    }

    @Override
    public Part createPart() {
        return new TeslaPart();
    }
}
