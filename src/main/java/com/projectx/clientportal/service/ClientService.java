package com.projectx.clientportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectx.clientportal.model.Client;
import com.projectx.clientportal.repository.ClientDao;

import lombok.extern.slf4j.Slf4j;

@Service("clientService")
@Slf4j
public class ClientService {
	ClientDao clientDao;
	
	@Autowired
	public ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	// Currently, for testing purposes to see the User data in Postman
	public List<Client> findAllClients() {
		log.info("clientService: findAllClients() call");
		return this.clientDao.findAll();
	}
	
	public Client findClientById(Integer clientId) {
		log.info("clientService: findClientById() call");
		return this.clientDao.findById(clientId).orElse(null);
	}
	
	public Client createClient(Client client) {
		Client temp = this.clientDao.findClientByCompanyName(client.getCompanyName());
		if(temp != null) {
			log.error("clientService: " + temp + " , already exist.");
			return null;
		} else {
			Client result = this.clientDao.save(client);
			log.info("clientService: " + result + " , successfully created.");
			return result;
		}
	}
	
	public boolean deleteClient(Integer clientId) {
		log.info("clientService: deleteClient() call");
		Client temp = this.clientDao.findById(clientId).orElse(null);
		if(temp == null) {
			log.error("clientService: Client with id " + clientId + " , doesn't exist.");
			return false;
		} else {
			this.clientDao.delete(temp);
			log.info("clientService: Client with id" + clientId +  " , successfully deleted.");
			return true;
		}
	}
}
