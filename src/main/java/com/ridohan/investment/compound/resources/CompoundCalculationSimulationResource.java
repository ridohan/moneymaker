package com.ridohan.investment.compound.resources;

import com.ridohan.investment.compound.orm.CompoundResult;
import com.ridohan.investment.compound.orm.CompoundSimulationResult;
import com.ridohan.investment.compound.service.CompoundInterestCalculatorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;


@Path("/compound")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompoundCalculationSimulationResource {

    @Inject
    CompoundInterestCalculatorService compoundInterestCalculatorService;

    @GET
    public CompoundSimulationResult getCompoundSimulation(@QueryParam("nbYears") int nbYears, @QueryParam("yield") double yield, @QueryParam("initialAmount") int initialAmount, @QueryParam("monhtlyInvestment") int monthlyInvestment) {

        return compoundInterestCalculatorService.calculateCompoundResults(LocalDate.now(),yield,initialAmount,monthlyInvestment,nbYears);

    }

    @GET
    @Path("/yearly")
    public List<CompoundResult> getCompoundSimulationYearly(@QueryParam("nbYears") int nbYears,@QueryParam("yield") double yield,@QueryParam("initialAmount") int initialAmount,@QueryParam("monhtlyInvestment") int monthlyInvestment) {

        return compoundInterestCalculatorService.calculateCompoundTableYearly(LocalDate.now(),yield,initialAmount,monthlyInvestment,nbYears);

    }
}
