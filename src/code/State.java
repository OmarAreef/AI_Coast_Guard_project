import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Ship implements Serializable {

    public int xPos;
    public int yPos;
    public int passengers;
    public int health;

    @Override
    public String toString() {
        return "Ship [xPos=" + xPos + ", yPos=" + yPos + ", passengers=" + passengers + ", health=" + health + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + xPos;
        result = prime * result + yPos;
        result = prime * result + passengers;
        result = prime * result + health;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ship other = (Ship) obj;
        if (xPos != other.xPos)
            return false;
        if (yPos != other.yPos)
            return false;
        if (passengers != other.passengers)
            return false;
        if (health != other.health)
            return false;
        return true;
    }

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

class Agent implements Serializable {

    public int xPos;
    public int yPos;
    public int totalCapacity;
    public int remainingCapacity;
    public int M;
    public int N;

    @Override
    public String toString() {
        return "Agent [xPos=" + xPos + ", yPos=" + yPos + ", totalCapacity=" + totalCapacity + ", remainingCapacity="
                + remainingCapacity + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + xPos;
        result = prime * result + yPos;
        result = prime * result + totalCapacity;
        result = prime * result + remainingCapacity;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Agent other = (Agent) obj;
        if (xPos != other.xPos)
            return false;
        if (yPos != other.yPos)
            return false;
        if (totalCapacity != other.totalCapacity)
            return false;
        if (remainingCapacity != other.remainingCapacity)
            return false;
        return true;
    }

    public Agent(int xPos, int yPos, int totalCapacity, int M, int N) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.totalCapacity = totalCapacity;
        this.remainingCapacity = totalCapacity;
        this.M = M;
        this.N = N;
    }

    public void drop() {
        this.remainingCapacity = totalCapacity;
    }

    public void pickUp(int passengers) {
        this.remainingCapacity = this.remainingCapacity - passengers;
    }

    public void moveY(int direction) {
        this.yPos = this.yPos + direction;
    }
    public void moveX(int direction) {
        this.xPos = this.xPos + direction;
    }

}

class Station implements Serializable {
    public int xPos;
    public int yPos;

    public Station(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

}

public class State implements Serializable {

    public ArrayList<Ship> ships;
    public ArrayList<Station> stations;
    public Agent agent;
    public int deaths;
    public int retrievedBlackBoxes;

    @Override
    public String toString() {
        return "State [ships=" + ships.toString() + ", agent=" + agent.toString() + ", deaths=" + deaths
                + ", retrievedBlackBoxes="
                + retrievedBlackBoxes + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ships == null) ? 0 : ships.hashCode());
        result = prime * result + ((stations == null) ? 0 : stations.hashCode());
        result = prime * result + ((agent == null) ? 0 : agent.hashCode());
        result = prime * result + deaths;
        result = prime * result + retrievedBlackBoxes;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        State other = (State) obj;
        if (ships == null) {
            if (other.ships != null)
                return false;
        } else if (!ships.equals(other.ships))
            return false;
       
        if (agent == null) {
            if (other.agent != null)
                return false;
        } else if (!agent.equals(other.agent))
            return false;
        if (deaths != other.deaths)
            return false;
        if (retrievedBlackBoxes != other.retrievedBlackBoxes)
            return false;
        return true;
    }

    public State deepCopy() {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(bo);
            o.writeObject(this);

            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream i = new ObjectInputStream(bi);

            return (State) i.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    public State(ArrayList<Ship> ships, Agent agent, int deaths, int retrieved) {
        this.ships = ships;
        this.agent = agent;
        this.deaths = deaths;
        this.retrievedBlackBoxes = retrieved;
    }

}