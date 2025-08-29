package com.example.QuickDine.bdd;

import  org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/resources/features")
@ConfigurationParameter(key = "cucumber.glue",value = "com.example.QuickDine.bdd")
public class CucumberTest {
}
