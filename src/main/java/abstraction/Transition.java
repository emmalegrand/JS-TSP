package abstraction;

public class Transition<S extends State> {

    private S successor;
    private int decision;
    private int cost;

    public Transition(S successor, int decision, int cost) {
        this.successor = successor;
        this.decision = decision;
        this.cost = cost;
    }

    public S getSuccessor() {
        return successor;
    }

    public int getDecision() {
        return decision;
    }

    public int getValue() {
        return cost;
    }
}

