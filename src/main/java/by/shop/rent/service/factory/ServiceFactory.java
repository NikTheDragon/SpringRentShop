package by.shop.rent.service.factory;

import by.shop.rent.service.ClientService;
import by.shop.rent.service.EquipmentService;
import by.shop.rent.service.impl.ClientServiceImpl;
import by.shop.rent.service.impl.EquipmentServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final ClientService clientService = new ClientServiceImpl();
	private final EquipmentService equipmentService = null;
	//private final EquipmentService equipmentService = new EquipmentServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public ClientService getClientServiceImpl() {
		return clientService;
	}
	
	public EquipmentService getEquipmentServiceImpl() {
		return equipmentService;
	}
	
}
