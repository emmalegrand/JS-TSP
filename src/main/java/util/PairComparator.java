package util;

import java.util.Comparator;

public class PairComparator implements Comparator<Pair> {

    @Override
    public int compare(Pair o1, Pair o2) {
        if ((o1.f - o2.f)<0){
            return -1;
        } else if ((o1.f - o2.f)>0) {
            return 1;
        }
        return 0;
    }

}
