package java_scratchpad;

/**
 * Created by darwinmorales on 25/07/2016.
 */
public class MenuContainer {
    private String id;
    private String name;

    public MenuContainer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
