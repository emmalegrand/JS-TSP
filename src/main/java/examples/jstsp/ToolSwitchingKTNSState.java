package examples.jstsp;

import abstraction.State;

import java.util.ArrayList;
import java.util.Objects;

public class ToolSwitchingKTNSState implements State {
    public ArrayList<Integer> seq;
    public int currentJobIdx;
    public long remaining;
    int hash;

    public ToolSwitchingKTNSState(ArrayList<Integer> seq, int currentJobIdx, long remaining) {
        this.seq = seq;
        this.currentJobIdx = currentJobIdx;
        this.remaining = remaining;
        this.hash = Objects.hash(this.currentJobIdx,  this.remaining);
        this.seq.add(this.currentJobIdx);

    }

    public String toString() {
        return "current_job: " + currentJobIdx +  " \n remaining: " + remaining + " \n seq: " + seq;
    }

    @Override
    public int hashCode() {
        return this.hash;
    }

    @Override
    public boolean equals(Object state) {
        ToolSwitchingState ts = (ToolSwitchingState) state;
        return currentJobIdx == ts.currentJobIdx && remaining == ts.remaining;
    }
}
