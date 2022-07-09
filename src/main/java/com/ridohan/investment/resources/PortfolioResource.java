package com.ridohan.investment.resources;


import com.ridohan.investment.orm.Portfolio;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/portfolios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PortfolioResource {


    @GET
    public List<Portfolio> get() {
        return Portfolio.listAll();
    }

    @GET
    @Path("/{id}")
    public Portfolio get(@PathParam("id") Long id) {
        return Portfolio.findById(id);
    }

    @POST
    @Transactional
    public Response create(Portfolio portfolio) {
        portfolio.persist();
        return Response.created(URI.create("/portfolios/" + portfolio.id)).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Portfolio update(@PathParam("id") Long id, Portfolio portfolio) {
        Portfolio entity = Portfolio.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        entity.name = portfolio.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Portfolio entity = Portfolio.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }





}
