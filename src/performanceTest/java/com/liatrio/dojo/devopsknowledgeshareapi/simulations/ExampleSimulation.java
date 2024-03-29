package com.liatrio.dojo.devopsknowledgeshareapi.simulations;

import static io.gatling.javaapi.core.CoreDsl.RawFileBody;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.holdFor;
import static io.gatling.javaapi.core.CoreDsl.jumpToRps;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;

public class ExampleSimulation extends Simulation {
  String baseUrl = TestConfiguration.BASE_URL;

  HttpProtocolBuilder httpProtocol = http
    .warmUp(baseUrl + "/actuator/health")
    .baseUrl(baseUrl);

  ScenarioBuilder baseScenario = scenario("Validation for health check")
    .exec(http("/actuator/health")
      .get("/actuator/health")
      .check(status().in(200)));

  {
    setUp(
      baseScenario.injectOpen(constantUsersPerSec(TestConfiguration.PARALLEL_QUERIES).during(Duration.ofMinutes(TestConfiguration.RAMP_DURATION))))
      .throttle(
        jumpToRps(TestConfiguration.RAMP_TO),
        holdFor(Duration.ofMinutes(TestConfiguration.RAMP_DURATION))
      )
      .protocols(httpProtocol)
      .assertions(
        global().successfulRequests().percent().gt(TestConfiguration.SUCCESS_PERCENTAGE)
      );
  }
}
