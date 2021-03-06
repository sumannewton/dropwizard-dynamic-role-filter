# Dropwizard-Dynamic-Role-Filter

## Description
A DynamicFeature and filter to allow the @RolesAllowed annotation to accept substitutions from PathParam, QueryParam and HeaderParam (similar to how you can get path params from the @Path annotation).

## Build instructions
Clone the source:
```
git clone https://github.com/sumannewton/dropwizard-dynamic-role-filter.git
```
Build
```
mvn install
```
Repo
```
<repository>
    <id>clojars</id>
    <name>Clojars repository</name>
    <url>https://clojars.org/repo</url>
</repository>
```
## Dependency Information
```
<dependency>
    <groupId>com.sumannewton</groupId>
    <artifactId>dropwizard-dynamic-role-filter</artifactId>
    <version>1.0</version>
</dependency>
```

Leiningen
```
[com.sumannewton/dropwizard-dynamic-role-filter "1.0"]
```
Gradle
```
compile "com.sumannewton:dropwizard-dynamic-role-filter:1.0"
```

## Usage
The example shown below will be assuming that you are using [Dropwizard](dropwizard.github.io/dropwizard) framework.

The only thing that needs to be done in order to use this, is to change the following line in your Application class (assuming you've got auth set-up already) from:
```
environment.jersey().register(RolesAllowedDynamicFeature.class);
```
to:
```
environment.jersey().register(DynamicRolesAllowedDynamicFeature.class);
```
and you're all set to go!

## Example
This lets you use replacement in your `@RolesAllowed` annotations. For example I have the following resource endpoint:
### PathParams
```
@GET
@Path("/users/{userId}")
public Response getUser() {
    return Response.ok().build();
}
```
which I want to return a 200 if you're the appropriate user accessing it. With the old `RolesAllowedDynamicFeature` you would have to do the following:
```
@GET
@Path("/users/{userId}")
@RolesAllowed("users:read")
public Response getUser(@Auth final User user,
                        @PathParam("userId") final String userId) {
    if (user.getName().equals(userId)) {
        return Response.ok().build();
    }
    return Response.status(403).build();
}
```
However, with the `DynamicRolesAllowedDynamicFeature` all you would need to do is:
```
@GET
@Path("/users/{userId}")
@RolesAllowed("users:{userId}:read")
public Response getUser(@PathParam("userId") final String userId) {
    return Response.ok().build();
}
```
### QueryParams
```
@GET
@Path("/users")
@QueryParam("userId")
public Response getUser() {
    return Response.ok().build();
}
```
which I want to return a 200 if you're the appropriate user accessing it. With the old `RolesAllowedDynamicFeature` you would have to do the following:
```
@GET
@Path("/users")
@RolesAllowed("users:read")
public Response getUser(@Auth final User user,
                        @QueryParam("userId") final String userId) {
    if (user.getName().equals(userId)) {
        return Response.ok().build();
    }
    return Response.status(403).build();
}
```
However, with the `DynamicRolesAllowedDynamicFeature` all you would need to do is:
```
@GET
@Path("/users")
@RolesAllowed("users:{userId}:read")
public Response getUser(@QueryParam("userId") final String userId) {
    return Response.ok().build();
}
```
### HeaderParams
```
@GET
@Path("/users")
@HeaderParam("userId")
public Response getUser() {
    return Response.ok().build();
}
```
which I want to return a 200 if you're the appropriate user accessing it. With the old `RolesAllowedDynamicFeature` you would have to do the following:
```
@GET
@Path("/users")
@HeaderParam("userId")
@RolesAllowed("users:read")
public Response getUser(@Auth final User user,
                        @HeaderParam("userId") final String userId) {
    if (user.getName().equals(userId)) {
        return Response.ok().build();
    }
    return Response.status(403).build();
}
```
However, with the `DynamicRolesAllowedDynamicFeature` all you would need to do is:
```
@GET
@Path("/users")
@RolesAllowed("users:{userId}:read")
public Response getUser(@HeaderParam("userId") final String userI) {
    return Response.ok().build();
}
```
Of course, you'll have to update the permissions you assing to a user appropriately. In the first example every user (who you want to have access to that endpoint) would need the broad permission of `user:read`. In the second example, each user would need more specific permissions `user:<userId>:read` where `<userId>` is the userId of the new user.
