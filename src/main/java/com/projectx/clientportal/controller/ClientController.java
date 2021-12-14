package com.projectx.clientportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.clientportal.model.Client;
import com.projectx.clientportal.model.JsonResponse;
import com.projectx.clientportal.service.ClientService;
import com.projectx.clientportal.utility.CrossOriginUtil;

@RestController("clientController")
@RequestMapping(value = "api")
@CrossOrigin(value = CrossOriginUtil.CROSS_ORIGIN_VALUE, allowCredentials = "true")
public class ClientController {
	private ClientService clientServ;
	
	@Autowired
	public ClientController(ClientService clientServ) {
		this.clientServ = clientServ;
	}
	
	// Currently, for testing purposes to see the User data in Postman
	@GetMapping("client")
	public JsonResponse getAllClients() {
		return new JsonResponse(true, "Clients :", this.clientServ.findAllClients());
	}
	
	//POST req for creating Client
	@PostMapping("client")
	public JsonResponse createClient(@RequestBody Client client) {
		JsonResponse jsonResponse;
		Client newClient = this.clientServ.createClient(client);
		
		if(newClient != null) {
			jsonResponse = new JsonResponse(true, "Client successfully created", newClient);
		} else {
			jsonResponse = new JsonResponse(false, "Client '" + client.getCompanyName() + "' already exist", null);
		}
		
		return jsonResponse;
	}
}
