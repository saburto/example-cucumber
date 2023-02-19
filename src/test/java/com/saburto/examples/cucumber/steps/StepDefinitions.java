package com.saburto.examples.cucumber.steps;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    @Autowired
    TestRestTemplate restTemplate;

    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("today is Sunday")
    public void today_is_sunday() {
        this.scenario.log("This is a custom test");
        var body = restTemplate.getForObject("/day", String.class);
        assertThat(body).isEqualTo("Hello World");
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_friday_yet() {
        try {
            Thread.sleep(1000 * 5);
        } catch (Exception e) {
        }
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String string) {
    }

    @Given("today is Friday")
    public void today_is_Friday() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("today is {string}")
    public void today_is(String day) {
        scenario.log(day.toString());

        restTemplate.getRestTemplate().setInterceptors(List.of(new ClientHttpRequestInterceptor() {

            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {

                ClientHttpResponse response = execution.execute(request, body);

                scenario.log(request.getURI().toString());

                return response;
            }

        }));

        var body = restTemplate.getForObject("/day", String.class);
        assertThat(body).isEqualTo("Hello World");
    }
}