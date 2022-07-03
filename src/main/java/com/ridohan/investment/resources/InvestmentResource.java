package com.ridohan.investment.resources;


import com.ridohan.investment.orm.Investment;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/investments")
@ApplicationScoped
public class InvestmentResource {


    @GET
    public Uni<List<Investment>> get() {
        return Investment.listAll(Sort.by("name"));
    }

    @GET
    @Path("/{id}")
    public Uni<Investment> getSingle(Long id) {
        return Investment.findById(id);
    }

    @POST
    public Uni<Response> create(Investment investment) {
        return Panache.<Investment>withTransaction(investment::persist)
                .onItem().transform(inserted -> Response.created(URI.create("/investments/" + inserted.id)).build());
    }
}
