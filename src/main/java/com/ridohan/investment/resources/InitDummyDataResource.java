package com.ridohan.investment.resources;

import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentCategory;
import com.ridohan.investment.orm.InvestmentValueRecord;
import com.ridohan.investment.orm.Portfolio;
import com.ridohan.investment.service.DummyDataServiceImpl;

import javax.inject.Inject;
import javax.sound.sampled.Port;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Path("/init")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InitDummyDataResource {


    @Inject
    DummyDataServiceImpl dummyDataService;

    @GET
    @Transactional
    public List<Portfolio> init() {
        return  dummyDataService.init();

    }







}