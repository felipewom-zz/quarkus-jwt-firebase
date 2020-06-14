package com.github.felipewom

import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal
import java.util.*
import javax.annotation.security.PermitAll
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext

@Path("/jwt")
class FirebaseAuth {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    fun hello(@Context securityContext: SecurityContext): String {
        if(!securityContext.isSecure){
            return "Hello Anonymous User"
        }
        val user = securityContext.userPrincipal as DefaultJWTCallerPrincipal
        val email = user.getClaim<Any>("email")
        return "hello $email"
    }

    @GET
    @Path("claims")
    @Produces(MediaType.APPLICATION_JSON)
    fun claimNames(@Context securityContext: SecurityContext): Map<*, *> {
        val user = securityContext.userPrincipal as DefaultJWTCallerPrincipal ?: return HashMap<Any?, Any?>()
        val claims: MutableMap<String, Any> = HashMap()
        for (claimName in user.claimNames) {
            claims[claimName] = user.getClaim(claimName)
        }
        return claims
    }
}