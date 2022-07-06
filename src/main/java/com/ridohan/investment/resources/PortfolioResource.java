package com.ridohan.investment.resources;


import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.Portfolio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public Investment get(@PathParam("id") Long id) {
        return Portfolio.findById(id);
    }






}
