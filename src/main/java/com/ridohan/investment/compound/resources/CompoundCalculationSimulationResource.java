package com.ridohan.investment.compound.resources;

import com.ridohan.investment.compound.orm.CompoundResult;
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
    public List<CompoundResult> getCompoundSimulation(@QueryParam("nbYears") int nbYears) {

        return compoundInterestCalculatorService.calculateCompoundTable(LocalDate.now(),0.1,100000,100,10);

    }
}
