package epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by dmorales on 26/12/2016.
 */
public class DeleteDuplicateArray {

    public static int deleteDuplicate(List<Integer> listNums) {
        if (listNums.isEmpty()) {
            return 0;
        }

        int writeIndex = 1;
        for (int i = 1; i < listNums.size()  ; i++) {
            if(listNums.get(i).equals(listNums.get(i - 1))) {
                listNums.set(writeIndex++, listNums.get(i));
            }
        }
        return writeIndex;
    }

    public static int removeDuplicate(List<Integer> listNums) {
        int index = 1;
        for (int i = 1; i < listNums.size() ; i++) {
            if (!listNums.get(index - 1).equals(listNums.get(i))) {
                Collections.swap(listNums, index++, i);
            }
        }
        return index;
    }

    public static void main(String[] args) {
        removeDuplicate(Arrays.asList(2, 2, 3, 3, 3, 4, 5, 5, 7));
        deleteDuplicate(Arrays.asList(2, 2, 3, 3, 3, 4, 5, 5, 7));
    }
}
