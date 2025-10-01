
import java.util.List;


public interface StudentsDataSource {

	/*
	 * Returns a List of students who are taking the specified class.
	 */
	public List<String> getStudents(String className) ;
	

}
