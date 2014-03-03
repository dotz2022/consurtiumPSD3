package lecturermanagement;

import repository.Session;
import mycampus.Course;

public interface ILecturerService {

	
	public void importCourse(Course course);
	
	public void addSession(Session session);
	
	public void getAllSessionFromTimeTable(); // JOSELYN
	
	
}
