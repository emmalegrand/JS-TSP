package util;

import java.util.ArrayList;

public class Result {
    private ArrayList<double[]> ub_time;
    private int cost;
    private double nb_nodes;

    public Result(ArrayList<double[]> ub_time, int cost, double nb_nodes) {
        this.ub_time = ub_time;
        this.cost = cost;
        this.nb_nodes = nb_nodes;
    }

    public Result() {
        this.ub_time = new ArrayList<>();
        this.cost = -1;
        this.nb_nodes = -1;
    }

    public void addUbTime(double[] ub_time) {
        this.ub_time.add(ub_time);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setNb_nodes(int nb_nodes) {
        this.nb_nodes = nb_nodes;
    }

    public ArrayList<double[]> getUb_time() {
        return ub_time;
    }

    public int getCost() {
        return cost;
    }

    public double getNb_nodes() {
        return nb_nodes;
    }
}

