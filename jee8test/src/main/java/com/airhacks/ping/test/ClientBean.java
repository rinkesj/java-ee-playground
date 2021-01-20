package com.airhacks.ping.test;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class ClientBean {

	private Client client;
	
	@PostConstruct
	public void init() {
		ClientBuilder clientBuilder = ClientBuilder.newBuilder();
		client = clientBuilder.build();
		client = client.register(Filter.class);
	}

	public void callPostMethod() {
		WebTarget target = client.target("http://localhost:9080/resources/testPost");
		Form form = new Form();
		form.param("test", "maybe");
		Entity<Form> entity = Entity.form(form);
		Response response = target.request().post(entity);
		
		System.out.println("callPostMethod Response");
		System.out.println("callPostMethod response has entity" + response.hasEntity());
		System.out.println("callPostMethod response length" + response.getLength());
		System.out.println("callPostMethod response data" + response.readEntity(String.class));
	}

	public void callGetMethod() {
		WebTarget target = client.target("http://localhost:9080/resources/testGet");
		Response response = target.request().get();
		
		System.out.println("callGetMethod Response");
		System.out.println("callGetMethod response has entity" + response.hasEntity());
		System.out.println("callGetMethod response length" + response.getLength());
		System.out.println("callGetMethod response data" + response.readEntity(String.class));
	}

}
