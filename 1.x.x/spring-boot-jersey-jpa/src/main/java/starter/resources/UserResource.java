package starter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import starter.dao.UserDao;
import starter.models.User;
import starter.resources.response.UserResponseWrapper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/user")
public class UserResource {
    
    private UserDao userDao;

    private User user;
    
    @Autowired
    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @POST
    @Path("add/{name}/{age}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(@PathParam("name") String aName,
                       @PathParam("age") int aAge){

        user = new User();
        user.setName(aName);
        user.setAge(aAge);

        userDao.create(user);

        return Response.status(201).entity(new UserResponseWrapper(user)).build();
    }
}
