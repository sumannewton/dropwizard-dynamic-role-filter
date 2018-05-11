package com.sumannewton.dropwizard.rest;

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
    @RolesAllowed("user:{user}:write")
    public Response endpoint6(@HeaderParam("user") final String user) {
        return Response.ok().build();
    }

    @GET
    @Path("headerparamendpoint2/user")
    @RolesAllowed("user:*:read")
    public Response endpoint7(@HeaderParam("user") final String user) {
        return Response.ok().build();
    }

    @GET
    @Path("headerparamendpoint3/user")
    @RolesAllowed("user:{usar}:read")
    public Response endpoint8(@HeaderParam("user") final String user) {
        return Response.ok().build();
    }

    @GET
    @Path("headerparamendpoint4/user")
    @PermitAll
    public Response endpoint9(@HeaderParam("user") final String user) {
        return Response.ok().build();
    }

    @GET
    @Path("headerparamendpoint5/user")
    @DenyAll
    public Response endpoint10(@HeaderParam("user") final String user) {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint/user")
    @RolesAllowed("user:{user}:write")
    public Response endpoint11(@QueryParam("user") final String user) {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint2/user")
    @RolesAllowed("user:*:read")
    public Response endpoint12(@QueryParam("user") final String user) {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint3/user")
    @RolesAllowed("user:{usar}:read")
    public Response endpoint13(@QueryParam("user") final String user) {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint4/user")
    @PermitAll
    public Response endpoint14(@QueryParam("user") final String user) {
        return Response.ok().build();
    }

    @GET
    @Path("queryparamendpoint5/user")
    @DenyAll
    public Response endpoint15(@QueryParam("user") final String user) {
        return Response.ok().build();
    }

}
