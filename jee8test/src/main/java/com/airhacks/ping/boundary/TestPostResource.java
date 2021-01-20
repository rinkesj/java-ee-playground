package com.airhacks.ping.boundary;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
*
* @author airhacks.com
*/
@Path("testPost")
public class TestPostResource {

   @POST
   public String testPost() {
       return "Post method called";
   }

}
