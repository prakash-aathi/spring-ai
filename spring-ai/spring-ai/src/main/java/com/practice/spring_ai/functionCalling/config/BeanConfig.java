package com.practice.spring_ai.functionCalling.config;

import com.practice.spring_ai.functionCalling.function.CalculateGstTax;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class BeanConfig {


    @Bean
    @Description("Calculate gst price and get total price including tax")
    public Function<CalculateGstTax.GstTaxRequest,CalculateGstTax.GstTaxResponse> calculateGstFunction(){
        return new CalculateGstTax();
    }

}
