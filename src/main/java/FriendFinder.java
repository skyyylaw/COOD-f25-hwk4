
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FriendFinder {
	
	protected ClassesDataSource classesDataSource;
	protected StudentsDataSource studentsDataSource;
	
	public FriendFinder(ClassesDataSource cds, StudentsDataSource sds) {
		classesDataSource = cds;
		studentsDataSource = sds;
	}

	public Set<String> findClassmates(String me) { 

		if (me == null)
			return Collections.emptySet();

		if (me.isEmpty())
			return Collections.emptySet();

		if (classesDataSource == null) 
			return null;

		if (studentsDataSource == null) 
			return null;
		
		List<String> myClasses = classesDataSource.getClasses(me);
		if (myClasses == null) {
			return Collections.emptySet();
		}
		
		Set<String> classmates = new HashSet<String>();
		
		for (String myClass : myClasses) {

			if (myClass == null)
				continue;
			
			List<String> students = studentsDataSource.getStudents(myClass);
			
			if (students == null) 
				return Collections.emptySet();
			
			for (String otherStudent : students) {
				
				if (otherStudent == null)
					continue;
				
				
				List<String> theirClasses = classesDataSource.getClasses(otherStudent);
				
				if (theirClasses == null)
					continue;
							
				boolean allSame = true;
				for (String c : myClasses) {
					
					if (c == null) {
						continue;
					}
					
					if (theirClasses.contains(c) == false) {
						allSame = false;
						break;
					}
				}
				if (allSame) {
					if (otherStudent.equals(me) == false) {
						classmates.add(otherStudent);
					}
				}
			}

		}
				
		return classmates;
	}
}
