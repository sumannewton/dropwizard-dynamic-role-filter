package com.sumannewton.dropwizard;

import com.sumannewton.dropwizard.auth.TestAuthenticator;
import com.sumannewton.dropwizard.auth.TestAuthorizer;
import com.sumannewton.dropwizard.auth.TestUser;
import com.sumannewton.dropwizard.rest.TestResource;
import com.sumannewton.server.filter.DynamicRolesDynamicFeature;
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
