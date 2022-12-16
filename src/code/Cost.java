package code;

import java.io.Serializable;

public class Cost implements Comparable<Cost>, Serializable {

    public int passengersAlive;
    public int retrievedBlackBoxes;

    public Cost(int passengersAlive, int retrievedBlackBoxes) {
        this.passengersAlive = passengersAlive;
        this.retrievedBlackBoxes = retrievedBlackBoxes;
    }

    public int compareTo(Cost o) {
        if (-this.passengersAlive + o.passengersAlive == 0) {
            return -o.retrievedBlackBoxes + this.retrievedBlackBoxes;
        }

        return -this.passengersAlive + o.passengersAlive;
    }

    public void addCost(Cost cost) {
        this.passengersAlive += cost.passengersAlive;
        this.retrievedBlackBoxes += cost.retrievedBlackBoxes;
    }

    @Override
    public String toString() {
        return "Cost [passengersAlive=" + passengersAlive + ", retrievedBlackBoxes=" + retrievedBlackBoxes + "]";
    }

}