package by.shop.rent.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class Cart {
	private List<Equipment> cart = new ArrayList<>();

	public List<Equipment> getCart() {
		return cart;
	}

	public void addEquipment(String equipmentID) {
		boolean isExist = false;
		for (Equipment equipment : cart) {
			if (equipment.getId().equals(equipmentID)) {
				isExist = false;
			}
		}
		if (!isExist) {
			Equipment equipment = new Equipment();
			equipment.setId(equipmentID);
			cart.add(equipment);
		}
	}

	public void removeEquipment(String equipmentID) {

		for (Equipment equipment : cart) {
			System.out.println("cartID=" + equipment.getId());
			if (equipment.getId().equals(equipmentID)) {
				System.out.println("deleting...");
				this.cart.remove(equipment);
				break;
			}
		}
	}

}
