package com.semih;

import com.semih.starter.CarGalleryApplication;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CarGalleryApplication.class)
class CarGalleryApplicationTests {

	@Test
	void Test(){
		System.out.println("Hello World");
	}
}
