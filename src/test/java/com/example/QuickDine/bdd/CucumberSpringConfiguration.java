package com.example.QuickDine.bdd;


import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.example.QuickDine.QuickDineApplication;

@CucumberContextConfiguration
@SpringBootTest
@ContextConfiguration(classes = QuickDineApplication.class)

public class CucumberSpringConfiguration {
}
