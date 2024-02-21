package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void givenObject_whenGetsFieldNamesAtRuntime_thenCorrect() {
        Person person = new Person();
        Field[] fields = person.getClass().getDeclaredFields();

        List<String> actualFieldNames = getFieldNames(fields);
        assertTrue(Arrays.asList("name", "age").containsAll(actualFieldNames));
    }

    private static List<String> getFieldNames(Field[] fields) {
        ArrayList<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
}