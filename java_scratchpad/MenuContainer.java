package java_scratchpad;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        List<MenuContainer> containerList = new ArrayList<>();

        containerList.add(new MenuContainer("1", "a"));
        containerList.add(new MenuContainer("1", "b"));
        containerList.add(new MenuContainer("1", "c"));
        containerList.add(new MenuContainer("1", "d"));
        containerList.add(new MenuContainer("1", "e"));
        containerList.add(new MenuContainer("2", "a"));
        containerList.add(new MenuContainer("2", "ba"));
        containerList.add(new MenuContainer("2", "ca"));
        containerList.add(new MenuContainer("2", "da"));
        containerList.add(new MenuContainer("2", "ea"));
        containerList.add(new MenuContainer("10", "aaa"));
        containerList.add(new MenuContainer("10", "aba"));
        containerList.add(new MenuContainer("10", "aca"));
        containerList.add(new MenuContainer("10", "ada"));
        containerList.add(new MenuContainer("10", "aea"));
    }
}
