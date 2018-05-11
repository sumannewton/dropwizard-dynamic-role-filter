package bng.sumannewton.java.server.filter.integration;

import bng.sumannewton.java.IntegrationTest;
import bng.sumannewton.java.dropwizard.TestApplication;
import bng.sumannewton.java.dropwizard.TestConfiguration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.internal.util.Base64;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Category(IntegrationTest.class)
public class DynamicRolesIntegrationTest {

    private static Client client;

    @ClassRule
    public static final DropwizardAppRule<TestConfiguration> dropwizardAppRule =
            new DropwizardAppRule<>(TestApplication.class);

    @BeforeClass
    public static void setUpClass() {
        client = new JerseyClientBuilder(dropwizardAppRule.getEnvironment()).build("TestClient");
    }

    @Test
    public void rolesAllowedHeaderReplacement() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", userId)
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void rolesAllowedHeaderReplacementInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", UUID.randomUUID().toString())
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoHeaderReplacementUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint2/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", userId)
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoHeaderReplacementInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint2/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", UUID.randomUUID().toString())
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoHeaderParamForReplacementUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint3/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", userId)
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoHeaderParamForReplacementInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint3/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", UUID.randomUUID().toString())
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void permitAllUserIdHeader() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint4/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", userId)
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void permitAllInvalidUserIdHeader() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint4/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", UUID.randomUUID().toString())
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void DenyAllUserIdHeader() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint5/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", userId)
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void DenyAllInvalidUserIdHeader() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/headerparamendpoint5/user", dropwizardAppRule.getLocalPort()))
                .request()
                .header("user", UUID.randomUUID().toString())
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedReplacement() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint/%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void rolesAllowedReplacementInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint/%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoReplacementUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint2/%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoReplacementInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint2/%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoPathParamForReplacementUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint3/%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoPathParamForReplacementInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint3/%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void permitAllUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint4/%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void permitAllInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint4/%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void DenyAllUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint5/%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void DenyAllInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/pathparamendpoint5/%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedQueryParamReplacement() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint/user?user=%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void rolesAllowedQueryParamReplacementInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint/user?user=%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoQueryParamReplacementUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint2/user?user=%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoQueryParamReplacementInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint2/user?user=%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoQueryParamForReplacementUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint3/user?user=%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void rolesAllowedNoQueryParamForReplacementInvalidUserId() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint3/user?user=%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void permitAllUserIdQueryParam() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint4/user?user=%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void permitAllInvalidUserIdQueryParam() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint4/user?user=%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void DenyAllUserIdQueryParam() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint5/user?user=%s", dropwizardAppRule.getLocalPort(), userId))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }

    @Test
    public void DenyAllInvalidUserIdQueryParam() {

        final String userId = UUID.randomUUID().toString();
        final String password = UUID.randomUUID().toString();

        final Response response = client.target(
                String.format("http://localhost:%d/queryparamendpoint5/user?user=%s", dropwizardAppRule.getLocalPort(), UUID.randomUUID().toString()))
                .request()
                .header("Authorization", String.format("Basic %s", Base64.encodeAsString(String.format("%s:%s", userId, password))))
                .get();

        Assert.assertEquals(403, response.getStatus());

    }
}