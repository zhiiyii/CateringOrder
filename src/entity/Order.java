package entity;

import adt.SortedArrayList;
import adt.SortedListInterface;

import java.io.Serializable;

/** Order - An entity class which includes sorted array lists of Menu and Pax.
  * @author Low Zhi Yi
  */

public class Order implements Comparable<Order>, Serializable {

    // attributes
    private int orderId;
    private String eventDate;
    private String eventStartTime;
    private String eventEndTime;
    private String eventAddress;

    private SortedListInterface<Menu> orderMenu;
    private SortedListInterface<Pax> paxForOrderedMenu;

    // constructors
    public Order() {
        this.orderMenu = new SortedArrayList<>();
        this.paxForOrderedMenu = new SortedArrayList<>();
    }

    public Order(int id) {
        this.orderId = id;
        this.orderMenu = new SortedArrayList<>();
        this.paxForOrderedMenu = new SortedArrayList<>();
    }

    // getter
    public int getOrderId() {
        return orderId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public SortedListInterface<Menu> getAllMenu() {
        return orderMenu;
    }

    public SortedListInterface<Pax> getAllPax() {
        return paxForOrderedMenu;
    }

    // Setter
    public void setEventDate(int day, int month, int year) {
        String dateSeparator1 = "/", dateSeparator2 = "/";
        if (month < 10) {
            dateSeparator1 += "0";
        }
        if (day < 10) {
            dateSeparator2 += "0";
        }
        this.eventDate = year + dateSeparator1 + month + dateSeparator2 + day;
    }

    public void setEventStartTime(int hour, int min) {
        String hourFormat = "", minFormat = ":";
        if (hour < 10) {
            hourFormat += "0";
        }
        if (min < 10) {
            minFormat += "0";
        }
        this.eventStartTime = hourFormat + hour + minFormat + min;
    }

    public void setEventEndTime(int hour, int min) {
        String hourFormat = "", minFormat = ":";
        if (hour < 10) {
            hourFormat += "0";
        }
        if (min < 10) {
            minFormat += "0";
        }
        this.eventEndTime = hourFormat + hour + minFormat + min;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public void addMenuIntoOrder(Menu menu) {
        orderMenu.add(menu);
    }

    public void removeMenuFromOrder(Menu menu) {
        orderMenu.remove(menu);
    }

    public void addPaxToMenu(Pax pax) {
        paxForOrderedMenu.add(pax);
    }

    public void removePaxFromMenu(Pax pax) {
        paxForOrderedMenu.remove(pax);
    }

    @Override
    public String toString() {
        return "Order ID\t: " + orderId
                + "\n----------\t-------------------------------------------------"
                + "\nEvent Date\t: " + eventDate
                + "\nEvent Time\t: " + eventStartTime + " - " + eventEndTime
                + "\nEvent Address\t: " + eventAddress;
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

        final Order other = (Order) obj;

        if (this.orderId != other.orderId) {
            return false;
        }

        return true;
    }

    @Override
    public int compareTo(Order compareId) {
        int compareOrderId = ((Order) compareId).getOrderId();
        return this.orderId - compareOrderId;
    }
}
