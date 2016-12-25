package epi;

import java.util.List;

/**
 * Created by dmorales on 26/12/2016.
 */
public class DeleteDuplicateArray {

    public int deleteDuplicate(List<Integer> listNums) {
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
    public static void main(String[] args) {

    }
}
