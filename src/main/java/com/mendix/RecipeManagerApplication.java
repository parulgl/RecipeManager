package com.mendix;


import com.mendix.resources.RecipesResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class RecipeManagerApplication extends Application<RecipeManagerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new RecipeManagerApplication().run(args);
    }

    @Override
    public String getName() {
        return "recipemanager";
    }

    @Override
    public void initialize(final Bootstrap<RecipeManagerConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                        .modules(new RecipeModule())
                .build());
    }

    @Override
    public void run(final RecipeManagerConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(RecipesResource.class);
    }

}
