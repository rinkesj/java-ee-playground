package com.airhacks.ping.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
public class PingResource {

    String message = "Hey";    

    @GET
    public String ping() {
        return this.message + " Jakarta EE on Websphere 9!";
    }

}
