package help;

import entity.Entry;

import java.util.Comparator;
import java.util.LinkedList;

public class SortHash implements Comparator<LinkedList<Entry>> {

    public int compare(LinkedList<Entry> list1, LinkedList<Entry> list2) {
        if (list1 == null) return -1;
        if (list2 == null) return 1;
        if ( list1.size() < list2.size() ) return -1;
        else if ( list1.size() == list2.size() ) return 0;
        else return 1;
    }

}
