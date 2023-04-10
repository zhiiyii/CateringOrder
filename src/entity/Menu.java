package entity;

import java.io.Serializable;
import java.text.DecimalFormat;

/** Menu - An entity class of menu list.
  * @author Low Zhi Yi
  */

public class Menu implements Comparable<Menu>, Serializable {

    private DecimalFormat dp2 = new DecimalFormat("0.00");

    // attributes
    private int menuId;
    private String menuType;
    private double price;

    // constructors
    public Menu() {
    }

    public Menu(int id) {
        this.menuId = id;
    }

    public Menu(int id, String menu, double price) {
        this.menuId = id;
        this.menuType = menu;
        this.price = price;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getMenuType() {
        return menuType;
    }

    public double getPrice() {
        return price;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String indent = "";
        if (menuId < 10) {
            indent += " ";
        }
        return indent + "[" + menuId + "]\tRM" + dp2.format(price) + "\t\t" + menuType;
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

        final Menu other = (Menu) obj;

        if (this.menuId != other.menuId) {
            return false;
        }

        return true;
    }

    @Override
    public int compareTo(Menu compareId) {
        int compareMenuId = ((Menu) compareId).getMenuId();
        return this.menuId - compareMenuId;
    }
}
