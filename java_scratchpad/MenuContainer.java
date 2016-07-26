package java_scratchpad;

import java.util.*;

/**
 * Created by darwinmorales on 25/07/2016.
 */
public class MenuContainer {
    private String id;
    private String name;
    private boolean exitThisGroup = false;

    private static class Range {
        int start;
        int end;

        private Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

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

        containerList.add(new MenuContainer("0", "a"));
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
        containerList.add(new MenuContainer("100", "aeab", true));
        containerList.add(new MenuContainer("101", "aeab"));
        containerList.add(new MenuContainer("102", "aeab"));

        Map<String, Range> mapRange = new HashMap<>();

        int startIndex = 0;
        String previousKey = "";
        for (int i = 0; i < containerList.size(); i++) {
            String currentKey = containerList.get(i).getId();
            if (!currentKey.equalsIgnoreCase(previousKey)) {
                if (previousKey.length() > 0) {
                    mapRange.put(previousKey, new Range(startIndex, i - 1));
                }
                previousKey = currentKey;
                startIndex = i;
            }
        }
        mapRange.put(previousKey, new Range(startIndex, containerList.size() - 1));

        for (Map.Entry<String, Range> entry : mapRange.entrySet()) {
            String id = entry.getKey();
            Range range = entry.getValue();
            System.out.println("processing group: " + id);
            for (int i = range.start; i <= range.end; i++) {
                System.out.println("menu: " + containerList.get(i).getName());
            }
        }

        String previousId = "";
        Iterator<MenuContainer> iter = containerList.iterator();
        boolean skipToNewGroup = false;

        while (iter.hasNext()) {
            MenuContainer menu = iter.next();
            String currentId = menu.getId();
            if (!currentId.equalsIgnoreCase(previousId)) {
                skipToNewGroup = false;
                System.out.println("new id: " + currentId);
                previousId = currentId;
            }
            if (!skipToNewGroup) {
                System.out.println(menu.getId() + " " + menu.getName());
                if (menu.isExitThisGroup()) {
                    System.out.println("   need to exit.: " + menu.getName());
                    skipToNewGroup = true;
                }
            }
        }


    }
}
