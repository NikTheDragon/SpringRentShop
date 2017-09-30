package by.shop.rent.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class Cart {
	private List<String> cart = new ArrayList<>();

	public List<String> getCart() {
		return cart;
	}

	public void addEquipment(String equipmentID) {

		if (!this.cart.contains(equipmentID)) {
			this.cart.add(equipmentID);
		}
	}

	public void removeEquipment(String equipmentID) {
		this.cart.remove(equipmentID);
	}

}
