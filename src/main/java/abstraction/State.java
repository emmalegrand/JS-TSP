package abstraction;

public interface State {

    /**
     * Computes a hash value that uniquely identifies a state of the dynamic programming model
     * Equivalent states should thus have equal hash values
     *
     * @return a hash of the state
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns true if both states are equal, needed in case of collisions in the hash table
     *
     * @return true if both states are equal
     */
    @Override
    public abstract boolean equals(Object o);

}
