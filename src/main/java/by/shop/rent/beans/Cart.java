package by.shop.rent.beans;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private String itemID;
	private List<String> cart = new ArrayList<>();

	public List<String> getCart() {
		return cart;
	}

	public void addItem(String itemID) {

		if (!this.cart.contains(itemID)) {
			this.cart.add(itemID);
		}
	}

	public void removeItem(String itemID) {
		this.cart.remove(itemID);
	}

}
