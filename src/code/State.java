package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Agent implements Serializable {

    public int column;
    public int row;
    public int totalCapacity;
    public int remainingCapacity;
    public int rows;
    public int columns;

    public Agent(int column, int row, int totalCapacity, int remainingCapacity, int rows, int columns) {
        this.column = column;
        this.row = row;
        this.totalCapacity = totalCapacity;
        this.remainingCapacity = remainingCapacity;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Agent [xPos=" + column + ", yPos=" + row + ", remainingCapacity="
                + remainingCapacity + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
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
        if (column != other.column)
            return false;
        if (row != other.row)
            return false;
        if (remainingCapacity != other.remainingCapacity)
            return false;
        return true;
    }

    public void drop() {
        this.remainingCapacity = totalCapacity;
    }

    public void pickUp(int passengers) {
        this.remainingCapacity = this.remainingCapacity - passengers;
    }

    public void moveY(int direction) {
        this.row = this.row + direction;
    }

    public void moveX(int direction) {
        this.column = this.column + direction;
    }

}

class Station implements Serializable {
    public int xPos;
    public int yPos;

    public Station(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + xPos;
        result = prime * result + yPos;
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
        Station other = (Station) obj;
        if (xPos != other.xPos)
            return false;
        if (yPos != other.yPos)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Station [xPos=" + xPos + ", yPos=" + yPos + "]";
    }

}

public class State implements Serializable {

    public ArrayList<Ship> ships;
    public ArrayList<Station> stations;
    public Agent agent;
    public int deaths;
    public int retrievedBlackBoxes;
    public int expandedStates;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ships == null) ? 0 : ships.hashCode());
        result = prime * result + ((agent == null) ? 0 : agent.hashCode());
        result = prime * result + deaths;
        result = prime * result + retrievedBlackBoxes;
        return result;
        // return Objects.hash(this.toString());
    }

    @Override
    public boolean equals(Object obj) {
        return (this.toString().equals(obj.toString()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ships.size(); i++) {
            sb.append(ships.get(i).toString());
            sb.append(", ");
        }
        String shipsString = sb.toString();
        return "State [ships=" + shipsString
                + ", agent=" + agent.toString()
                + ", deaths=" + deaths
                + ", retrievedBlackBoxes="
                + retrievedBlackBoxes + "]";
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

    public State(ArrayList<Ship> ships, ArrayList<Station> stations, Agent agent, int deaths, int retrieved) {
        this.ships = ships;
        this.stations = stations;
        this.agent = agent;
        this.deaths = deaths;
        this.retrievedBlackBoxes = retrieved;

    }

    public void update() {
        ArrayList<Ship> shipsToRemove = new ArrayList<Ship>();
        for (Ship ship : this.ships) {
            int deaths = ship.updateShip();
            if (ship.health == 0 && ship.passengers == 0) {
                shipsToRemove.add(ship);
            }
            this.deaths += deaths;
        }
        this.ships.removeAll(shipsToRemove);

    }
}
