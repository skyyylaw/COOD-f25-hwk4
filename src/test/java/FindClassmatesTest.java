import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindClassmatesTest {
  StudentsDataSource sds = new StudentsDataSource() {
    @Override
    public List<String> getStudents(String className) {
      return List.of("John", "Holland");
    }
  };
  ClassesDataSource cds = new ClassesDataSource() {
    @Override
    public List<String> getClasses(String studentName) {
      return List.of("Calculus", "History");
    }
  };
  FriendFinder ff = new FriendFinder(cds, sds);

  @Test
  public void nullMe() {
    assertEquals(Collections.emptySet(), ff.findClassmates(null));
  }


  @Test
  public void emptyMe() {
    assertEquals(Collections.emptySet(), ff.findClassmates(""));
  }

  @Test
  public void nullCDS() {
    ff = new FriendFinder(null, sds);
    assertNull(ff.findClassmates("hello"));
  }

  @Test
  public void nullSDS() {
    ff = new FriendFinder(cds, null);
    assertNull(ff.findClassmates("hello"));
  }

  @Test
  public void getClassesReturnNull() {
    cds = new ClassesDataSource() {
      @Override
      public List<String> getClasses(String studentName) {
        return null;
      }
    };
    ff = new FriendFinder(cds, sds);
    assertEquals(Collections.emptySet(), ff.findClassmates("hello"));
  }

  @Test
  public void AHasANullClass() {
    sds = new StudentsDataSource() {
      @Override
      public List<String> getStudents(String className) {
        if (className.equals("Calculus")) {
          return List.of("A", "B", "C");
        } else if (className.equals("History")) {
          return List.of("A", "B");
        } else {
          return Collections.emptyList();
        }
      }
    };
    cds = new ClassesDataSource() {
      @Override
      public List<String> getClasses(String studentName) {
        if (studentName.equals("A")) {
          List<String> list = new ArrayList<>(List.of("Calculus", "History"));
          list.add(null);
          return list;
        } else if (studentName.equals("B")) {
          return List.of("Calculus", "History");
        } else if (studentName.equals("C")) {
          return List.of("Calculus");
        } else {
          return Collections.emptyList();
        }
      }
    };
    ff = new FriendFinder(cds, sds);
    assertEquals(Set.of("B"), ff.findClassmates("A"));
  }

  @Test
  public void calculusReturnsNull() {
    sds = new StudentsDataSource() {
      @Override
      public List<String> getStudents(String className) {
        if (className.equals("Calculus")) {
          return null;
        } else if (className.equals("History")) {
          return List.of("A", "B");
        } else {
          return Collections.emptyList();
        }
      }
    };
    cds = new ClassesDataSource() {
      @Override
      public List<String> getClasses(String studentName) {
        if (studentName.equals("A")) {
          return List.of("Calculus", "History");
        } else if (studentName.equals("B")) {
          return List.of("Calculus", "History");
        } else if (studentName.equals("C")) {
          return List.of("Calculus");
        } else {
          return Collections.emptyList();
        }
      }
    };
    ff = new FriendFinder(cds, sds);
    assertEquals(Collections.emptySet(), ff.findClassmates("A"));
  }

  @Test
  public void calculusHasANullStudente() {
    sds = new StudentsDataSource() {
      @Override
      public List<String> getStudents(String className) {
        if (className.equals("Calculus")) {
          ArrayList<String> list = new ArrayList<>(List.of("A", "B", "C"));
          list.add(null);
          return list;
        } else if (className.equals("History")) {
          return List.of("A", "B");
        } else {
          return Collections.emptyList();
        }
      }
    };
    cds = new ClassesDataSource() {
      @Override
      public List<String> getClasses(String studentName) {
        if (studentName.equals("A")) {
          return List.of("Calculus", "History");
        } else if (studentName.equals("B")) {
          return List.of("Calculus", "History");
        } else if (studentName.equals("C")) {
          return List.of("Calculus");
        } else {
          return Collections.emptyList();
        }
      }
    };
    ff = new FriendFinder(cds, sds);
    assertEquals(Set.of("A", "B"), ff.findClassmates("C"));
  }

  @Test
  public void BReturnsNullClasses() {
    sds = new StudentsDataSource() {
      @Override
      public List<String> getStudents(String className) {
        if (className.equals("Calculus")) {
          return List.of("A", "B");
        } else if (className.equals("History")) {
          return List.of("A", "B");
        } else {
          return Collections.emptyList();
        }
      }
    };
    cds = new ClassesDataSource() {
      @Override
      public List<String> getClasses(String studentName) {
        if (studentName.equals("A")) {
          return List.of("Calculus", "History");
        } else if (studentName.equals("B")) {
          return null;
        } else if (studentName.equals("C")) {
          return List.of("Calculus");
        } else {
          return Collections.emptyList();
        }
      }
    };
    ff = new FriendFinder(cds, sds);
    assertEquals(Set.of("A"), ff.findClassmates("C"));
  }

  @Test
  public void normalTestCase() {
    sds = new StudentsDataSource() {
      @Override
      public List<String> getStudents(String className) {
        if (className.equals("Calculus")) {
          ArrayList<String> list = new ArrayList<>(List.of("A", "B", "C"));
          list.add(null);
          return list;
        } else if (className.equals("History")) {
          ArrayList<String> list = new ArrayList<>(List.of("A", "B"));
          list.add(null);
          return list;
        } else {
          return Collections.emptyList();
        }
      }
    };
    cds = new ClassesDataSource() {
      @Override
      public List<String> getClasses(String studentName) {
        if (studentName.equals("A")) {
          List<String> list = new ArrayList<>(List.of("Calculus", "History"));
          list.add(null);
          return list;
        } else if (studentName.equals("B")) {
          List<String> list = new ArrayList<>(List.of("Calculus", "History"));
          list.add(null);
          return list;
        } else if (studentName.equals("C")) {
          List<String> list = new ArrayList<>(List.of("Calculus"));
          list.add(null);
          return list;
        } else {
          return Collections.emptyList();
        }
      }
    };
    ff = new FriendFinder(cds, sds);
    assertEquals(Set.of("B"), ff.findClassmates("A"));
    assertEquals(Set.of("A"), ff.findClassmates("B"));
    assertEquals(Set.of("A", "B"), ff.findClassmates("C"));
    assertEquals(Collections.emptySet(), ff.findClassmates("D"));
  }


}
