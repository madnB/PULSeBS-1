package team13.pulsbes.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.exception.InvalidUserException;
import team13.pulsbes.serviceimpl.OfficerService;
import team13.pulsbes.utils.Constants;

@RestController
@RequestMapping("/support_officer")
public class SupportOfficerController {
	
	@Autowired
	OfficerService officerService;
	
	Logger log = Logger.getLogger("SupportOfficerController");
	private static final String TYPE_SUPPORT = "support_officer";

	@PostMapping(value = Constants.ADD_STUDENTS, consumes = "text/csv")
	public void addStudents(@RequestBody String file,@CookieValue(value = "username") String username,@CookieValue(value = "type") String type) throws InvalidUserException, IOException {
		File f = new File("Students.csv");
		FileWriter myWriter = new FileWriter(f);
		myWriter.write(file);
		myWriter.close();
		if(type.equals(TYPE_SUPPORT)) {
			officerService.addStudentList(f);
			f.delete();
		}
		else {
			myWriter.close();
			f.delete();
			throw new InvalidUserException("Invalid User");
		}
	}
	@PostMapping(value = Constants.ADD_TEACHERS)
	public void addTeachers(@RequestBody String file,@CookieValue(value = "username") String username,@CookieValue(value = "type")String type) throws InvalidUserException, IOException {
		File f = new File("Teachers.csv");
		FileWriter myWriter = new FileWriter(f);
		myWriter.write(file);
		myWriter.close();
		if(type.equals(TYPE_SUPPORT)) {
			officerService.addTeacherList(f);
			f.delete();
		}
		else {
			myWriter.close();
			f.delete();
			throw new InvalidUserException("Invalid User");
		}
	}
	@PostMapping(value = Constants.ADD_COURSES)
	public void addCourses(@RequestBody String file,@CookieValue(value = "username") String username,@CookieValue(value = "type")String type) throws InvalidUserException, IOException {
		File f = new File("Courses.csv");
		FileWriter myWriter = new FileWriter(f);
		myWriter.write(file);
		myWriter.close();
		if(type.equals(TYPE_SUPPORT)) {
			officerService.addCourseList(f);
			f.delete();
		}
		else {
			myWriter.close();
			f.delete();
			throw new InvalidUserException("Invalid User");
		}
	}
	@PostMapping(value = Constants.ENROLL_STUDENTS)
	public void enrollStudents(@RequestBody String file,@CookieValue(value = "username") String username,@CookieValue(value = "type")String type) throws InvalidCourseException, InvalidStudentException, InvalidUserException, IOException {
		File f = new File("Enroll_students.csv");
		FileWriter myWriter = new FileWriter(f);
		myWriter.write(file);
		myWriter.close();
		if(type.equals(TYPE_SUPPORT)) {
			officerService.enrollStudent(f);
			f.delete();
		}
		else {
			myWriter.close();
			f.delete();
			throw new InvalidUserException("Invalid User");
		}
	}
	@PostMapping(value = Constants.REMOVE_LECTURES)
	public void removeLectures(String year, String dateStart, String dateEnd, @CookieValue(value = "username") String username,@CookieValue(value = "type")String type) throws InvalidUserException {
			if(type.equals(TYPE_SUPPORT)) {
			officerService.removeLectures(year, dateStart, dateEnd);			
		}
		else {
			throw new InvalidUserException("Invalid User");
		}
	}
	@PostMapping(value = Constants.READD_LECTURES)
	public void readdLectures(String year, String dateStart, String dateEnd, @CookieValue(value = "username") String username,@CookieValue(value = "type")String type) throws InvalidUserException {
			if(type.equals(TYPE_SUPPORT)) {
			officerService.readdLectures(year, dateStart, dateEnd);			
		}
		else {
			throw new InvalidUserException("Invalid User");
		}
	}
}
