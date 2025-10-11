import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTest {
  private Source source;
  List<String> filterList = List.of("hello", "world");

  @Test
  public void nullInputList() {
    assertNull(Filter.filter(null, source));
  }

  @Test
  public void nullSource() {
    assertNull(Filter.filter(filterList, null));
  }

  @Test
  public void emptyInputList() {
    assertEquals(Collections.emptySet(), Filter.filter(Collections.emptyList(), new Source() {
      @Override
      public List<String> get() {
        return Collections.emptyList();
      }
    }));
  }

  @Test
  public void getEmptyList() {
    assertEquals(Collections.emptySet(), Filter.filter(filterList, new Source() {
      @Override
      public List<String> get() {
        return Collections.emptyList();
      }
    }));
  }

  @Test
  public void getNull() {
    assertEquals(Collections.emptySet(), Filter.filter(filterList, new Source() {
      @Override
      public List<String> get() {
        return null;
      }
    }));
  }

  @Test
  public void filterReturnSingleString() {
    assertEquals(Set.of("hello"), Filter.filter(List.of("hello"), new Source() {
      @Override
      public List<String> get() {
        return List.of("hello", "world", "and", "you");
      }
    }));
  }

  @Test
  public void filterReturnMultipleString() {
    assertEquals(Set.of("hello", "world"), Filter.filter(filterList, new Source() {
      @Override
      public List<String> get() {
        return List.of("hello", "world", "and", "you");
      }
    }));
  }


  @Test
  public void filterReturnEmpty() {
    assertEquals(Collections.emptySet(), Filter.filter(List.of("WOOHOO"), new Source() {
      @Override
      public List<String> get() {
        return List.of("hello", "world", "and", "you");
      }
    }));
  }
}
