package com.github.felipewom;

import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.Map;

@Path("/jwt")
public class FirebaseAuth {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String hello(@Context SecurityContext securityContext) {
        final var user = (DefaultJWTCallerPrincipal) securityContext.getUserPrincipal();
        if (user == null){
            return "Hello Anonymous User";
        }
        final var email = user.getClaim("email");
        return "hello " + email;
    }

    @GET
    @Path("claims")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Map claimNames(@Context SecurityContext securityContext) {
        final var user = (DefaultJWTCallerPrincipal) securityContext.getUserPrincipal();
        if (user == null)
            return new HashMap();

        Map<String, Object> claims = new HashMap<>();
        for (String claimName : user.getClaimNames()) {
            claims.put(claimName, user.getClaim(claimName));
        }
        return claims;
    }

}