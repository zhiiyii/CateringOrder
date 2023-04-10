Author 		 : Low Zhi Yi
Student ID 	 : 21WMR02962
Group 		 : RDS2(S1)G2
Title 		 : Catering (Meal) Service
Module-in-Charge : Ordering Module

-------------------------------------------------------------------------------------

ADT implementation	: SortedArrayList.java
ADT interface 		: SortedListInterface.java

Entity classes 		: Order.java
			  Menu.java
			  Pax.java

Clients			: OrderManager.java
			  OrderDriver.java

-------------------------------------------------------------------------------------

Responsibilities : (Add/edit/search/retrieve/delete) (orders/menus)


// Note that this is a ordering module, not restricted yet for employees or customers
// Note that you must press the 'enter' key on your keyboard after inserting inputs


How-to's : 

  a. Add an order
	1. press '1'
	2. key in all the event details
		a. order id (integers)
		b. event date (dd MM yyyy), eg: 11 9 2022 for 11 September 2022
		c. event time (hh mm)	  , eg: 13 00 for 1:00 p.m.
		d. event address (in one line without line separating)
	3. press 'Enter' to continue
	4. key in all the menu details
		a. menu id (integers based on the menu list displayed)
		b. pax for the menu chosen
	5. enter 'y' to continue adding more menu into the order,
	   enter 'n' to finish the order and go back to main menu

  b. Edit an order
	1. press '2'
	2. press '1' to edit event date, or
		 '2' to edit event time, or
		 '3' to edit event address, or
		 '4' to edit pax, or
		 '5' to add menu to order, or
		 '6' to remove menu to order
	3. key in the order id of the order you want to edit
	4. key in the new order detail

  c. Search for an order by the order id / Find out the subtotal of menus ordered
	1. press '3'
	2. key in the order id of the order you want to search for

  d. See all the orders
	1. press '4'

  e. Remove an order
	1. press '5'
	2. key in the order id of the order you want to delete

  f. Add a menu
	1. press '6'
	2. key in all the new menu details
		a. a new menu id in integer
		b. the new menu name
		c. the price of menu without including currency unit

  g. Edit a menu
	1. press '7'
	2. press '1' to edit menu name
		 '2' to edit menu price
	3. key in the menu id of the menu you want to edit
	4. key in the new menu detail you chose to edit

  h. Search for a menu detail by its menu id
	1. press '8'
	2. key in the menu id of the menu you want to search for

  i. See the full menu list
	1. press '9'

  j. Remove a menu from the full menu list
	1. press '10'
	2. key in the menu id of the menu you want to remove

  k. Exit the ordering system
	1. press '0'