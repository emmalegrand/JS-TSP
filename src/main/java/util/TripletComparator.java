package util;

import java.util.Comparator;

public class TripletComparator implements Comparator<Triplet> {

    @Override
    public int compare(Triplet o1, Triplet o2) {
        if ((o1.h - o2.h)<0){
            return -1;
        } else if ((o1.h - o2.h)>0) {
            return 1;
        }
        return 0;
    }
}
