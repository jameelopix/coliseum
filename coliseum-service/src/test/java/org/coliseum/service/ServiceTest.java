package org.coliseum.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import coliseum.service.BusinessException;
import coliseum.service.ErrorDTO;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service_config.xml", "classpath:webflow_config.xml" })
public class ServiceTest {

	@Autowired
	StudentService studentService;

	@Test
	public void createProxy() {
		System.out.println("ServiceTest.createProxy()");
		try {
			studentService.getStudent(new StudentServiceRequest());
		} catch (BusinessException exception) {

			System.out.println("ServiceTest BusinessException-------");
			List<ErrorDTO> errorDTOs = exception.getErrorDTOs();
			for (ErrorDTO errorDTO : errorDTOs) {
				System.out.println(errorDTO.toString());
			}
		}
	}

}
