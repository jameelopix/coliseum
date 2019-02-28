package framework.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import coliseum.jpa.Filter;
import coliseum.jpa.Operator;
import coliseum.jpa.SearchObject;
import framework.jpa.entity.Address;
import framework.jpa.entity.Gender;
import framework.jpa.entity.Student;
import framework.jpa.repo.AddressRepo;
import framework.jpa.repo.StudentRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:persistence_config.xml" })
public class SingleTableTest {

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String AGE = "age";
	private static final String DOB = "dob";
	private static final String GENDER = "gender";
	private static final String SALARY = "salary";

	static String dateFormat = "dd-MM-yyyy";

	static long ageQuotient = (1000l * 60l * 60l * 24l * 365l);

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Before
	public void setup() {
//		Date baseDate = parseDate("01-01-2018", dateFormat);

		Address address = new Address();
		address.setCity("CITY");
		address.setDistrict("District");
		address.setState("State");
		address.setStreet("Street");
		address = addressRepo.save(address);

		for (Object[] stud : studentData) {
			Date dob = parseDate(stud[2], dateFormat);
//			long diff = baseDate.getTime() - dob.getTime();
//			long age = (diff) / ageQuotient;

			Student student = new Student();
			student.setName((String) stud[0]);
			student.setGender((Gender) stud[1]);
			student.setDob(dob);
			student.setAge((Integer) stud[3]);
			student.setSalary((Integer) stud[4]);
			student.setAddress(address);
			studentRepo.save(student);
		}
	}

	@Test
	public void testOperator() {
		this.testEqualsOperator();
		this.testNotEqualsOperator();
		this.testInOperator();
		this.testNotInOperator();
		this.testGreaterThanOperator();
		this.testGreaterThanOrEqualsOperator();
		this.testLesserThanOperator();
		this.testLesserThanOrEqualsOperator();
		this.testNullOperator();
		this.testNotNullOperator();
	}

	private void testNullOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		filter = new Filter(SALARY).by(Operator.IS_NULL);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 4, students.size());
	}

	private void testNotNullOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		filter = new Filter(SALARY).by(Operator.IS_NOT_NULL);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 2, students.size());
	}

	private void testGreaterThanOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		// ------------------------------------------ String
		filter = new Filter(NAME).by(Operator.GREATER_THAN).with("Asraf");
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 4, students.size());

		// ------------------------------------------ Integer
		filter = new Filter(AGE).by(Operator.GREATER_THAN).with(25);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", new Long(1l), students.get(0).getId());

		// ------------------------------------------ Date
		filter = new Filter(DOB).by(Operator.GREATER_THAN).with(parseDate("03-08-2010", dateFormat));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", new Long(6l), students.get(0).getId());

		// ------------------------------------------ Enum
		filter = new Filter(GENDER).by(Operator.GREATER_THAN).with(Gender.FEMALE);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 3, students.size());

		// ------------------------------------------ Long
		filter = new Filter(ID).by(Operator.GREATER_THAN).with(5l);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", new Long(6l), students.get(0).getId());
	}

	private void testGreaterThanOrEqualsOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		// ------------------------------------------ String
		filter = new Filter(NAME).by(Operator.GREATER_THAN_OR_EQUALS).with("Asraf");
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 5, students.size());

		// ------------------------------------------ Integer
		filter = new Filter(AGE).by(Operator.GREATER_THAN_OR_EQUALS).with(25);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 2, students.size());

		// ------------------------------------------ Date
		filter = new Filter(DOB).by(Operator.GREATER_THAN_OR_EQUALS).with(parseDate("03-08-2010", dateFormat));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 2, students.size());

		// ------------------------------------------ Enum
		filter = new Filter(GENDER).by(Operator.GREATER_THAN_OR_EQUALS).with(Gender.FEMALE);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 6, students.size());

		// ------------------------------------------ Long
		filter = new Filter(ID).by(Operator.GREATER_THAN_OR_EQUALS).with(5l);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 2, students.size());
	}

	private void testLesserThanOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		// ------------------------------------------ String
		filter = new Filter(NAME).by(Operator.LESS_THAN).with("Asraf");
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 1, students.size());

		// ------------------------------------------ Integer
		filter = new Filter(AGE).by(Operator.LESS_THAN).with(25);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 4, students.size());

		// ------------------------------------------ Date
		filter = new Filter(DOB).by(Operator.LESS_THAN).with(parseDate("03-08-2010", dateFormat));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 4, students.size());

		// ------------------------------------------ Enum
		filter = new Filter(GENDER).by(Operator.LESS_THAN).with(Gender.FEMALE);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 0, students.size());

		// ------------------------------------------ Long
		filter = new Filter(ID).by(Operator.LESS_THAN).with(2l);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", new Long(1l), students.get(0).getId());
	}

	private void testLesserThanOrEqualsOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		// ------------------------------------------ String
		filter = new Filter(NAME).by(Operator.LESS_THAN_OR_EQUALS).with("Asraf");
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 2, students.size());

		// ------------------------------------------ Integer
		filter = new Filter(AGE).by(Operator.LESS_THAN_OR_EQUALS).with(25);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 5, students.size());

		// ------------------------------------------ Date
		filter = new Filter(DOB).by(Operator.LESS_THAN_OR_EQUALS).with(parseDate("03-08-2010", dateFormat));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 5, students.size());

		// ------------------------------------------ Enum
		filter = new Filter(GENDER).by(Operator.LESS_THAN_OR_EQUALS).with(Gender.FEMALE);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 3, students.size());

		// ------------------------------------------ Long
		filter = new Filter(ID).by(Operator.LESS_THAN_OR_EQUALS).with(5l);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 5, students.size());
	}

	private void testNotInOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		// ------------------------------------------ String
		filter = new Filter(NAME).by(Operator.NOT_IN).with(Arrays.asList("Asraf", "Jameel"));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 4, students.size());

		// ------------------------------------------ Integer
		filter = new Filter(AGE).by(Operator.NOT_IN).with(Arrays.asList(29, 25));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 4, students.size());

		// ------------------------------------------ Date
		filter = new Filter(DOB).by(Operator.NOT_IN)
				.with(Arrays.asList(parseDate("03-08-1994", dateFormat), parseDate("19-12-1993", dateFormat)));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 4, students.size());

		// ------------------------------------------ Enum
		filter = new Filter(GENDER).by(Operator.NOT_IN).with(Arrays.asList(Gender.MALE, Gender.FEMALE));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 0, students.size());

		// ------------------------------------------ Long
		filter = new Filter(ID).by(Operator.NOT_IN).with(Arrays.asList(2l, 3l, 4l, 5l));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 2, students.size());
	}

	private void testEqualsOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		// ------------------------------------------ String
		filter = new Filter(NAME).by(Operator.EQUALS).with("Asraf");
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Mismatch", new Long(2l), students.get(0).getId());

		// ------------------------------------------ Integer
		filter = new Filter(AGE).by(Operator.EQUALS).with(29);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Mismatch", new Long(1l), students.get(0).getId());

		// ------------------------------------------ Date
		filter = new Filter(DOB).by(Operator.EQUALS).with(parseDate("03-08-1994", dateFormat));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Mismatch", new Long(3l), students.get(0).getId());

		// ------------------------------------------ Enum
		filter = new Filter(GENDER).by(Operator.EQUALS).with(Gender.MALE);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 3, students.size());

		// ------------------------------------------ Long
		filter = new Filter(ID).by(Operator.EQUALS).with(3l);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Mismatch", new Long(3l), students.get(0).getId());
	}

	private void testNotEqualsOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		// ------------------------------------------ String
		filter = new Filter(NAME).by(Operator.NOT_EQUALS).with("Asraf");
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 5, students.size());

		// ------------------------------------------ Integer
		filter = new Filter(AGE).by(Operator.NOT_EQUALS).with(29);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 5, students.size());

		// ------------------------------------------ Date
		filter = new Filter(DOB).by(Operator.NOT_EQUALS).with(parseDate("03-08-1994", dateFormat));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 5, students.size());

		// ------------------------------------------ Enum
		filter = new Filter(GENDER).by(Operator.NOT_EQUALS).with(Gender.MALE);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 3, students.size());

		// ------------------------------------------ Long
		filter = new Filter(ID).by(Operator.NOT_EQUALS).with(3l);
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 5, students.size());
	}

	private void testInOperator() {
		SearchObject searchObject = null;
		List<Student> students = null;
		Filter filter = null;

		// ------------------------------------------ String
		filter = new Filter(NAME).by(Operator.IN).with(Arrays.asList("Asraf", "Jameel"));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 2, students.size());

		// ------------------------------------------ Integer
		filter = new Filter(AGE).by(Operator.IN).with(Arrays.asList(29, 25));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 2, students.size());

		// ------------------------------------------ Date
		filter = new Filter(DOB).by(Operator.IN)
				.with(Arrays.asList(parseDate("03-08-1994", dateFormat), parseDate("19-12-1993", dateFormat)));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 2, students.size());

		// ------------------------------------------ Enum
		filter = new Filter(GENDER).by(Operator.IN).with(Arrays.asList(Gender.MALE, Gender.FEMALE));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 6, students.size());

		// ------------------------------------------ Long
		filter = new Filter(ID).by(Operator.IN).with(Arrays.asList(2l, 3l, 4l, 5l));
		searchObject = new SearchObject();
		searchObject.setFilters(Arrays.asList(filter));
		students = studentRepo.search(searchObject);
		Assert.assertEquals("Record Size Mismatch", 4, students.size());
	}

	private static Date parseDate(Object date, String parseFormat) {
		if (date != null) {
			try {
				return new SimpleDateFormat(parseFormat).parse((String) date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static Object[][] studentData = {
			// 1
			{ "Jameel", Gender.MALE, "02-06-1989", 29, 10000 },
			// 2
			{ "Asraf", Gender.MALE, "19-12-1993", 25, 20000 },
			// 3
			{ "Saffida", Gender.FEMALE, "03-08-1994", 24, null },
			// 4
			{ "Safrin", Gender.FEMALE, "03-08-2010", 8, null },
			// 5
			{ "Shakil", Gender.MALE, "23-03-2007", 11, null },
			// 6
			{ "Ajmina", Gender.FEMALE, "14-07-2014", 4, null } };
}
