package code;

import java.io.Serializable;

public class Ship implements Serializable {

    public int xPos;
    public int yPos;
    public int passengers;
    public int health;

    @Override
    public String toString() {
        return "Ship [xPos=" + xPos + ", yPos=" + yPos + ", passengers=" + passengers + ", health=" + health + "]";
    }

    public boolean isWreck() {
        if (passengers <= 0) {
            return true;
        } else
            return false;
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
        // return (this.toString().equals(obj.toString()));
    }

    public Ship(int xPos, int yPos, int passengers, int health) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.passengers = passengers;
        this.health = 20;
    }

    public void pickUp(int numberOfPassengers) {
        this.passengers = this.passengers - numberOfPassengers;
    }

    public int updateShip() {
        if (this.passengers > 0) {
            this.passengers = this.passengers - 1;
            return 1;
        }
        this.health = this.health - 1;
        return 0;
    }
}
