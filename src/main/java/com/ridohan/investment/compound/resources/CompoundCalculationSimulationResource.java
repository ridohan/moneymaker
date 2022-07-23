package com.ridohan.investment.compound.resources;

import com.ridohan.investment.compound.orm.CompoundResult;
import com.ridohan.investment.compound.orm.CompoundSimulationResult;
import com.ridohan.investment.compound.service.CompoundInterestCalculatorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@Path("/compound")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompoundCalculationSimulationResource {

    @Inject
    CompoundInterestCalculatorService compoundInterestCalculatorService;

    @GET
    public CompoundSimulationResult getCompoundSimulation(@QueryParam("beginDate") Date beginDate, @QueryParam("nbYears") int nbYears, @QueryParam("yield") double yield, @QueryParam("initialAmount") int initialAmount, @QueryParam("monhtlyInvestment") int monthlyInvestment) {
        LocalDate beginLocalDate;
        if(beginDate == null){
            beginLocalDate = LocalDate.now();
        }else{
            beginLocalDate = beginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return compoundInterestCalculatorService.calculateCompoundResults(beginLocalDate,yield,initialAmount,monthlyInvestment,nbYears);

    }

    @GET
    @Path("/yearly")
    public List<CompoundResult> getCompoundSimulationYearly(@QueryParam("beginDate")  Date beginDate,@QueryParam("nbYears") int nbYears,@QueryParam("yield") double yield,@QueryParam("initialAmount") int initialAmount,@QueryParam("monhtlyInvestment") int monthlyInvestment) {
        LocalDate beginLocalDate;
        if(beginDate == null){
            beginLocalDate = LocalDate.now();
        }else{
            beginLocalDate = beginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return compoundInterestCalculatorService.calculateCompoundTableYearly(beginLocalDate,yield,initialAmount,monthlyInvestment,nbYears);

    }
}
