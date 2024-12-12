package util;

import abstraction.State;

import java.util.Objects;

public class Triplet <S extends State> {
    public S state;
    public double h;
    public double val;
    public Triplet(S state, double h, double val) {
        this.state = state;
        this.val = val;
        this.h = h;
    }
    @Override
    public int hashCode() {
        int res =  Objects.hash(state,h,val);
        return res;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Triplet<?>) {
            return ((Triplet<?>) o).state.equals(this.state) && ((Triplet<?>) o).val == this.val && ((Triplet<?>) o).h == this.h;
        }

        return false;

    }
}