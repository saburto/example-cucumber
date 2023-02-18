package com.saburto.examples.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;
import io.cucumber.java.Before;

public class StepDefinitions {


private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }


@Given("today is Sunday")
public void today_is_sunday() {
    this.scenario.log("This is a custom test");
}
@When("I ask whether it's Friday yet")
public void i_ask_whether_it_s_friday_yet() {
    try {
        Thread.sleep(1000 * 5);
    } catch (Exception e) {
        // TODO: handle exception
    }
}
@Then("I should be told {string}")
public void i_should_be_told(String string) {
}

    @Given("today is Friday")
    public void today_is_Friday() {
        // Write code here that turns the phrase above into concrete actions
    }
}