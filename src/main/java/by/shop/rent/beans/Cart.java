package by.shop.rent.beans;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component ("cart")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class Cart {
	private Set<String> idCart = new HashSet<>();
	private Set<Equipment> equipmentCart = new HashSet<>();

	public Set<String> getIdCart() {
		return idCart;
	}
	
	public Set<Equipment> getEquipmentCart() {
		return equipmentCart;
	}

/*	public void addEquipment(String equipmentID) {
		boolean isExist = false;
		for (Equipment equipment : cart) {
			if (equipment.getId().equals(equipmentID)) {
				isExist = true;
			}
		}
		if (!isExist) {
			Equipment equipment = new Equipment();
			equipment.setId(equipmentID);
			cart.add(equipment);
		}
	}*/
	
	public void addEquipment (Equipment equipment) {
		equipmentCart.add(Objects.requireNonNull(equipment));
	}

	public void addID (String id) {
		idCart.add(id);
	}
	
	public void removeEquipment(String equipmentID) {
		idCart.remove(equipmentID);
		
		for (Equipment equipment : equipmentCart) {
			if (equipment.getId().equals(equipmentID)) {
				equipmentCart.remove(equipment);
				break;
			}
		}
	}

}
