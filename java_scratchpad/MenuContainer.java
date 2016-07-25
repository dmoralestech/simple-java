package java_scratchpad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by darwinmorales on 25/07/2016.
 */
public class MenuContainer {
    private String id;
    private String name;
    private boolean exitThisGroup = false;

    public MenuContainer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public MenuContainer(String id, String name, boolean exitThisGroup) {
        this.id = id;
        this.name = name;
        this.exitThisGroup = exitThisGroup;
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

    public boolean isExitThisGroup() {
        return exitThisGroup;
    }

    public void setExitThisGroup(boolean exitThisGroup) {
        this.exitThisGroup = exitThisGroup;
    }

    public static void main(String[] args) {
        List<MenuContainer> containerList = new ArrayList<>();

        containerList.add(new MenuContainer("1", "a"));
        containerList.add(new MenuContainer("1", "b"));
        containerList.add(new MenuContainer("1", "c"));
        containerList.add(new MenuContainer("1", "d", true));
        containerList.add(new MenuContainer("1", "e"));
        containerList.add(new MenuContainer("2", "a"));
        containerList.add(new MenuContainer("2", "ba"));
        containerList.add(new MenuContainer("2", "ca", true));
        containerList.add(new MenuContainer("2", "da"));
        containerList.add(new MenuContainer("2", "ea"));
        containerList.add(new MenuContainer("10", "aaa", true));
        containerList.add(new MenuContainer("10", "aba"));
        containerList.add(new MenuContainer("10", "aca"));
        containerList.add(new MenuContainer("10", "ada"));
        containerList.add(new MenuContainer("10", "aea"));
        containerList.add(new MenuContainer("100", "aeab", true ));
        containerList.add(new MenuContainer("101", "aeab"));
        containerList.add(new MenuContainer("102", "aeab"));

        String previousId = "";
        boolean isNewId = false;
        Iterator<MenuContainer> iter =  containerList.iterator();

        while(iter.hasNext()) {
            MenuContainer menu = iter.next();
            String currentId = menu.getId();
            if (!currentId.equalsIgnoreCase(previousId)) {
                isNewId = true;
                System.out.println("new id: " + currentId);
                previousId = currentId;
            } else {
                isNewId = false;
            }
            if (menu.isExitThisGroup()) {
                System.out.printf("need to exit.: " + menu.getName());
            }
        }


    }
}
