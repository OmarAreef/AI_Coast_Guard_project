import java.util.ArrayList;

class Ship {

    public int xPos;
    public int yPos;
    public int passengers;
    public int health;

    public Ship(int xPos, int yPos, int passengers, int health) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.passengers = passengers;
        this.health = health;
    }

    public void pickUp(int numberOfPassengers) {
        this.passengers = this.passengers - numberOfPassengers;
    }

    public void updateShip() {
        if (this.passengers > 0) {
            this.passengers = this.passengers - 1;
            return;
        }
        this.health = this.health - 1;
    }
}

class Agent {

    public int xPos;
    public int yPos;
    public int totalCapacity;
    public int remainingCapacity;

    public Agent(int xPos, int yPos, int totalCapacity) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.totalCapacity = totalCapacity;
        this.remainingCapacity = totalCapacity;
    }

    public void drop() {
        this.remainingCapacity = 0;
    }

    public void pickUp(int passengers) {
        this.remainingCapacity = this.remainingCapacity - passengers;
    }

}

public class State {

    public ArrayList<Ship> ships;
    public Agent agent;
    public int deaths;
    public int retrievedBlackBoxes;

    public State(ArrayList<Ship> ships, Agent agent, int deaths, int retrieved) {
        this.ships = ships;
        this.agent = agent;
        this.deaths = deaths;
        this.retrievedBlackBoxes = retrieved;
    }

}
