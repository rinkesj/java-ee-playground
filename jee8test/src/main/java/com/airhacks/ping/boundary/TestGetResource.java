package com.airhacks.ping.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
*
* @author airhacks.com
*/
@Path("testGet")
public class TestGetResource {

   @GET
   public String testGet() {
       return "Get method called";
   }

}
