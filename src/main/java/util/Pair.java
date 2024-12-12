package util;

import abstraction.State;

import java.util.Objects;

public class Pair<S extends State> {
    public S state;
    public double f;
    public Pair(S state, double f) {
        this.state = state;
        this.f = f;
    }
    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
    @Override
    public boolean equals(Object o) {
       if (o instanceof Pair) {
           return ((Pair<?>) o).state.equals(this.state);
       }

        return false;

    }
}
