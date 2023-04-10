package entity;

import java.io.Serializable;
import java.text.DecimalFormat;

/** Pax - An entity class of event pax.
  * @author Low Zhi Yi
  */

public class Pax implements Comparable<Pax>, Serializable {

    private DecimalFormat dp2 = new DecimalFormat("0.00");

    // attributes
    private int paxId;
    private int pax;
    private int orderId;
    private int menuId;

    // constructors
    public Pax() {
    }

    public Pax(int id) {
        this.paxId = id;
    }

    public Pax(int orderId, int menuId) {
        this.orderId = orderId;
        this.menuId = menuId;
    }

    public Pax(int paxId, int pax, int orderId, int menuId) {
        this.paxId = paxId;
        this.pax = pax;
        this.orderId = orderId;
        this.menuId = menuId;
    }

    public int getPaxId() {
        return paxId;
    }

    public int getPax() {
        return pax;
    }

    @Override
    public String toString() {
        return "" + pax;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Pax other = (Pax) obj;

        if (this.paxId != other.paxId) {
            if (this.orderId == other.orderId && this.menuId == other.menuId) {
                return true;
            }
            return false;
        }

        return true;
    }

    @Override
    public int compareTo(Pax compareId) {
        int comparePaxId = ((Pax) compareId).getPaxId();
        return this.paxId - comparePaxId;
    }
}
