package com.practice.spring_ai.functionCalling.function;


import java.util.function.Function;

public class CalculateGstTax implements Function<CalculateGstTax.GstTaxRequest, CalculateGstTax.GstTaxResponse> {

    public record GstTaxRequest(double productAmount){}
    public record GstTaxResponse(double taxAmount,double productAmount,double totalAmount){}

    @Override
    public GstTaxResponse apply(GstTaxRequest gstTaxRequest) {
        double taxAmount = gstTaxRequest.productAmount() * 0.18;
        double totalAmount = gstTaxRequest.productAmount() + taxAmount;
        System.out.println("=== GstTaxResponse is called  args: "+gstTaxRequest.productAmount()+ " tax: "+taxAmount+" total: "+totalAmount);
        return new GstTaxResponse(taxAmount, gstTaxRequest.productAmount(), totalAmount );
    }

}
