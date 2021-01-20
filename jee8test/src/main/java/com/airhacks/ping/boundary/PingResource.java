package com.airhacks.ping.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.airhacks.ping.test.ClientBean;


/**
*
* @author airhacks.com
*/
@Path("ping")
public class PingResource {

   String message = "Hey"; 
   
   @Inject
   ClientBean client;

   @GET
   public String ping() {
	   
	   client.callPostMethod();
	   client.callGetMethod();
	   
       return this.message + " Jakarta EE on Websphere 9!";
       
   }

}