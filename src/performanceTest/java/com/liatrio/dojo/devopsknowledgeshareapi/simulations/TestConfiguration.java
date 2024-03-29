package com.liatrio.dojo.devopsknowledgeshareapi.simulations;

public final class TestConfiguration {
  public static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
  public static final Integer PARALLEL_QUERIES = 2;
  public static final Integer RAMP_FROM = 1;
  public static final Integer RAMP_TO = 5;
  public static final Integer RAMP_DURATION = 2;
  public static final Double SUCCESS_PERCENTAGE = 80.0;
}
