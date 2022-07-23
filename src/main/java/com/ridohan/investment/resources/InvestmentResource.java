package com.ridohan.investment.resources;


import com.ridohan.investment.compound.orm.CompoundResult;
import com.ridohan.investment.compound.service.CompoundInterestCalculatorService;
import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentEntry;
import com.ridohan.investment.orm.InvestmentValueRecord;
import com.ridohan.investment.orm.Portfolio;
import com.ridohan.investment.service.InvestmentService;
import io.quarkus.panache.common.Sort;
import org.jboss.logging.annotations.Param;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Path("/investments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvestmentResource {

    @Inject
    InvestmentService investmentService;

    @Inject
    CompoundInterestCalculatorService compoundInterestCalculatorService;


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
    @Path("/{id}/records")
    public List<InvestmentValueRecord> getRecords(@PathParam("id") Long id) {
        return investmentService.getValueRecords(Investment.findById(id));
    }

    @POST
    @Transactional
    public Response create(Investment investment) {
        investment.persist();
        return Response.created(URI.create("/investments/" + investment.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Investment update(@PathParam("id") Long id, Investment investment) {
        Investment entity = Investment.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        entity.name = investment.name;

        return entity;
    }


    @POST
    @Path("/{id}/records")
    @Transactional
    public InvestmentValueRecord addValueRecord(@PathParam("id") Long id, InvestmentValueRecord investmentValueRecord) {
        Investment entity = Investment.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        //investmentEntry.persist();
        entity.records.add(investmentValueRecord);
        entity.persist();
        return investmentValueRecord;
    }

    @DELETE
    @Path("/{id}/records/{recordId}")
    @Transactional
    public void deleteRecord(@PathParam("id") Long id,@PathParam("recordId") Long recordId) {
        Investment entity = Investment.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }else{
            InvestmentValueRecord record = InvestmentValueRecord.findById(recordId);
            if(record == null) {
                throw new NotFoundException();
            }
            record.delete();

        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Investment entity = Investment.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }



    @GET
    @Path("/{id}/yield")
    public Double  getAverageYield(@PathParam("id")  Long id) {

        Investment investment = Investment.findById(id);
        if(investment == null) {
            throw new NotFoundException();
        }
        return investmentService.calculateAverageYield(investment);
    }

    @GET
    @Path("/{id}/yield/annual/{year}")
    public Double  getAverageAnnualYield(@PathParam("id")  Long id,@PathParam("year") int year) {
        Investment investment = Investment.findById(id);
        if(investment == null) {
            throw new NotFoundException();
        }

        return investmentService.calculateAverageAnnualYield(investment,year);
    }



    @GET
    @Path("/{id}/compound")
    public List<CompoundResult> getCompoundSimulation(@PathParam("id")  Long id,@QueryParam("nbYears")  @DefaultValue("10") int nbYears, @QueryParam("monthlyInvestment") Double monthlyInvestment) {
        Investment investment = Investment.findById(id);
        if(investment == null) {
            throw new NotFoundException();
        }

        if(monthlyInvestment == null){
            monthlyInvestment = investmentService.getAverageMonthlyInvestment(investment);
        }
        return compoundInterestCalculatorService.calculateCompoundTable(investment,monthlyInvestment,nbYears);

    }

}
