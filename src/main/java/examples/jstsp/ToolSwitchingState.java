package examples.jstsp;

import abstraction.State;

import java.util.Objects;

public class ToolSwitchingState implements State {

    public int currentJobIdx;
    FreeTools f;
    public long remaining;
    int hash;

    public ToolSwitchingState(int currentJobIdx, FreeTools f, long remaining) {
        this.currentJobIdx = currentJobIdx;
        this.f = f;
        this.remaining = remaining;
        this.hash = Objects.hash(this.currentJobIdx, this.f, this.remaining);
    }

    public String toString() {
        return "current_job: " + currentJobIdx + " \n f: " + f + " \n remaining: " + remaining;
    }

    @Override
    public int hashCode() {
        return this.hash;
    }

    @Override
    public boolean equals(Object state) {
        ToolSwitchingState ts = (ToolSwitchingState) state;
        return currentJobIdx == ts.currentJobIdx && remaining == ts.remaining && f.equals(ts.f);
    }
}
