package com.ridohan;

import com.ridohan.investment.orm.Investment;
import com.ridohan.investment.orm.InvestmentValueRecord;

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


    @GET
    @Transactional
    public List<Investment> init() {
        if(Investment.findByName("DEGIRO") == null){
            Investment degiro = new Investment("DEGIRO");
            addInvestmentValueRecord(LocalDate.of(2020,01,01),100,110,degiro);
            addInvestmentValueRecord(LocalDate.of(2022,05,01),100,50,degiro);
            addInvestmentValueRecord(LocalDate.of(2022,07,01),100,200,degiro);

            degiro.persistAndFlush();
        }

        return Investment.listAll();

    }

    private void addInvestmentValueRecord(LocalDate date, double investedAmount, double value, Investment investment){
        InvestmentValueRecord record = new InvestmentValueRecord();

        record.investedAmount=investedAmount;
        record.date=date;
        record.value=value;
        record.persist();
        investment.records.add(record);
    }


//
//    private void initRecipe(String recipeName, Map<String,Quantity> ingredients ){
//
//        Recipe recipe = Recipe.findByName(recipeName);
//        if(recipe == null){
//            recipe = new Recipe();
//            recipe.name = recipeName;
//
//            for (Map.Entry<String, Quantity> entry : ingredients.entrySet()) {
//                RecipeCompound recipeCompound = new RecipeCompound();
//                recipeCompound.ingredient = findOrCreateIngredient(entry.getKey() );
//                recipeCompound.quantity = entry.getValue();
//                recipe.recipeCompounds.add(recipeCompound);
//            }
//
//
//            recipe.persistAndFlush();
//
//        }
//
//    }
//
//
//        private void initIngredientFamily(String ingredientFamilyName, List<String> ingredients ){
//        IngredientFamily ingredientFamily  = IngredientFamily.findByName(ingredientFamilyName);
//        if(ingredientFamily == null){
//            ingredientFamily = new IngredientFamily();
//            ingredientFamily.name = ingredientFamilyName;
//
//            for(String ingredientString : ingredients){
//                Ingredient ingredient  = findOrCreateIngredient(ingredientString);
//                ingredientFamily.ingredients.add(ingredient);
//                ingredient.family=ingredientFamily;
//
//            }
//
//            ingredientFamily.persistAndFlush();
//        }
//    }
//
//    public Ingredient findOrCreateIngredient(String name){
//        Ingredient ingredient  = Ingredient.findByName(name);
//        if(ingredient == null){
//            ingredient = new Ingredient();
//            ingredient.name = name;
//        }
//        return ingredient;
//    }




}