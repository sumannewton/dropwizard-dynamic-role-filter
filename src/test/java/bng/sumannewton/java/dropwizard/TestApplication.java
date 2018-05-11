package bng.sumannewton.java.dropwizard;

import bng.sumannewton.java.dropwizard.auth.TestAuthenticator;
import bng.sumannewton.java.dropwizard.auth.TestAuthorizer;
import bng.sumannewton.java.dropwizard.auth.TestUser;
import bng.sumannewton.java.dropwizard.rest.TestResource;
import bng.sumannewton.java.server.filter.DynamicRolesDynamicFeature;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TestApplication extends Application<TestConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public String getName() {
        return "Test";
    }

    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {

    }

    @Override
    public void run(final TestConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<TestUser>()
                .setAuthenticator(new TestAuthenticator())
                .setAuthorizer(new TestAuthorizer())
                .buildAuthFilter()));
        environment.jersey().register(DynamicRolesDynamicFeature.class);

        environment.jersey().register(new TestResource());

    }

}
