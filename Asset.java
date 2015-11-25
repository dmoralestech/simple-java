import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by dmorales on 23/11/2015.
 */
public class Asset {
    public enum AssetType { BOND, STOCK};

    private final AssetType type;

    private final int value;

    public Asset(AssetType type, int value) {
        this.type = type;
        this.value = value;
    }

    public AssetType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public static int totalAssetValues( final List<Asset> assets) {
        return assets.stream().mapToInt(Asset::getValue).sum();
    }

    public static int totalBondValues ( final List<Asset> assets) {
        //this didn't commit properly
        return assets.stream().mapToInt( asset -> asset.getType()==AssetType.BOND ? asset.getValue() : 0).sum();
        //alternate solution ??
        //assets.stream().filter(asset -> asset.getType() == AssetType.BOND).mapToInt( asset -> asset.getValue()).sum();
    }

    public static int totalAssetValues(final List<Asset> assets, final Predicate<Asset> assetAllocator) {
        return assets.stream().filter(assetAllocator).mapToInt(Asset::getValue).sum();
    }

    public static void main(String[] args) {
        final List<Asset> assets = Arrays.asList(
          new Asset(AssetType.BOND, 1000),
          new Asset(AssetType.BOND, 2000),
          new Asset(AssetType.STOCK, 3000),
          new Asset(AssetType.STOCK, 5000),
          new Asset(AssetType.STOCK, 4000)
        );

        System.out.println("Total: " + totalAssetValues(assets));
        System.out.println("Total Stock: " + totalAssetValues(assets, asset -> asset.getType() == AssetType.STOCK ));
        System.out.println("Total Stock: " + totalAssetValues(assets, asset -> asset.getType() == AssetType.BOND ));

        Predicate<Asset> testAssetPredicate = asset -> asset.getType() == AssetType.BOND;
        System.out.println(testAssetPredicate.test(new Asset(AssetType.STOCK, 233)));

    }
}
