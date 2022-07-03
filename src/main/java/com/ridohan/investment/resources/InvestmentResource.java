package com.ridohan.investment.resources;


import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.service.InvestmentService;
import io.quarkus.panache.common.Sort;
import org.jboss.logging.annotations.Param;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/investments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvestmentResource {

    @Inject
    InvestmentService investmentService;


    @GET
    public List<Investment> get() {
        return Investment.listAll();
    }

    @GET
    @Path("/{id}")
    public Investment get(@PathParam("id") Long id) {
        return Investment.findById(id);
    }


    @GET
    @Path("/{id}/yield")
    public Double  getAverageYield(@PathParam("id")  Long id) {

        Investment investment = Investment.findById(id);

        return investmentService.calculateAverageYield(investment);
    }

    @GET
    @Path("/{id}/yield/annual/{year}")
    public Double  getAverageAnnualYield(@PathParam("id")  Long id,@PathParam("year") int year) {

        Investment investment = Investment.findById(id);

        return investmentService.calculateAverageAnnualYield(investment,year);
    }




}
