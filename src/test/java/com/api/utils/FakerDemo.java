package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {
	
	public static void main(String[] args) {
		
		Faker faker = new Faker(new Locale("en-IND"));
		String firstName = faker.name().firstName();
		System.out.println(firstName);
		
		String lastName = faker.name().lastName();
		System.out.println(lastName);
		
		String buildingNumber = faker.address().buildingNumber();
		System.out.println(buildingNumber);
		
		String streetAddress = faker.address().streetAddress();
		System.out.println(streetAddress);
		
		String city = faker.address().city();
		System.out.println(city);
		
		String digits = faker.number().digits(5);
		System.out.println(digits);
		
		String numerify = faker.numerify("782#######");
		System.out.println(numerify);
		System.out.println(faker.numerify("782#######"));
		System.out.println(faker.numerify("782#######"));
		System.out.println(faker.numerify("782#######"));
		
		
		String emailAddress = faker.internet().emailAddress();
		System.out.println(emailAddress);
		
	}

}
