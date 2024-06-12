package com.liatrio.dojo.devopsknowledgeshareapi.functional;

import MethodOrderer.OrderAnnotation;

public @interface TestMethodOrder {

    Class<org.junit.jupiter.api.MethodOrderer.OrderAnnotation> value();

}
