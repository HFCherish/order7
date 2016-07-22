package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("users")
public class UsersApi {
//    @Context
//    UserRepository userRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Map info,
                             @Context Routes routes) {
        return Response.created(routes.userUrl(798l)).build();
    }

//    @Path("{userId}")
//    public UserApi getUser(@PathParam("userId") String userId,
//                           @Context UserRepository userRepository) {
//        return userRepository.ofId(new UserId(userId))
//                .map(UserApi::new)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }
}
