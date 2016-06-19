package starter.configs;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import starter.resources.UserResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(UserResource.class);
    }

}
