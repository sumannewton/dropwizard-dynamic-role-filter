package bng.sumannewton.java.dropwizard.rest;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("")
public class TestResource {

    public TestResource() {

    }

    @GET
    @Path("pathparamendpoint/{user}")
    @RolesAllowed("user:{user}:write")
    public Response endpoint1() {
        return Response.ok().build();
    }

    @GET
    @Path("pathparamendpoint2/{user}")
    @RolesAllowed("user:*:read")
    public Response endpoint2() {
        return Response.ok().build();
    }

    @GET
    @Path("pathparamendpoint3/{user}")
    @RolesAllowed("user:{usar}:read")
    public Response endpoint3() {
        return Response.ok().build();
    }

    @GET
    @Path("pathparamendpoint4/{user}")
    @PermitAll
    public Response endpoint4() {
        return Response.ok().build();
    }

    @GET
    @Path("pathparamendpoint5/{user}")
    @DenyAll
    public Response endpoint5() {
        return Response.ok().build();
    }

    @GET
    @Path("headerparamendpoint/user")
    @HeaderParam("user")
    @RolesAllowed("user:{user}:write")
    public Response endpoint6() {
        return Response.ok().build();
    }

    @GET
    @Path("headerparamendpoint2/user")
    @HeaderParam("user")
    @RolesAllowed("user:*:read")
    public Response endpoint7() {
        return Response.ok().build();
    }

    @GET
    @Path("headerparamendpoint3/user")
    @HeaderParam("user")
    @RolesAllowed("user:{usar}:read")
    public Response endpoint8() {
        return Response.ok().build();
    }

    @GET
    @Path("headerparamendpoint4/user")
    @HeaderParam("user")
    @PermitAll
    public Response endpoint9() {
        return Response.ok().build();
    }

    @GET
    @Path("headerparamendpoint5/user")
    @HeaderParam("user")
    @DenyAll
    public Response endpoint10() {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint/user")
    @QueryParam("user")
    @RolesAllowed("user:{user}:write")
    public Response endpoint11() {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint2/user")
    @QueryParam("user")
    @RolesAllowed("user:*:read")
    public Response endpoint12() {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint3/user")
    @QueryParam("user")
    @RolesAllowed("user:{usar}:read")
    public Response endpoint13() {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint4/user")
    @QueryParam("user")
    @PermitAll
    public Response endpoint14() {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint5/user")
    @QueryParam("user")
    @DenyAll
    public Response endpoint15() {
        return Response.ok().build();
    }

}
