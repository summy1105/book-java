package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BirdTest {

    @Test
    public void givenClass_whenGetsAllConstructors_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Constructor<?>[] constructors = birdClass.getConstructors();

        assertEquals(3, constructors.length);
    }

    @Test
    public void givenClass_whenGetsEachConstructorByParamTypes_then() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> birdClass = Class.forName("reflection.Bird");

        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);

        System.out.println("cons1 = " + cons1.getParameters().length);
        System.out.println("cons2.getParameters().length = " + cons2.getParameters().length);
        Arrays.stream(cons3.getParameters()).forEach(parameter -> System.out.println("parameter = " + parameter.getType().getName()));
    }

    @Test
    public void givenClass_whenInstantiatesObjectsAtRuntime_thenCorrect() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);

        Bird bird1 = (Bird) cons1.newInstance();
        Bird bird2 = (Bird) cons2.newInstance("Weaver bird");
        Bird bird3 = (Bird) cons3.newInstance("dove", true);

        assertEquals("bird", bird1.getName());
        assertEquals("Weaver bird", bird2.getName());
        assertEquals("dove", bird3.getName());

        assertFalse(bird1.walks());
        assertTrue(bird3.walks());
    }

    @Test
    public void givenClass_whenGetsPublicFields_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Field[] fields = birdClass.getFields();

        assertEquals(1, fields.length);
        assertEquals("CATEGORY", fields[0].getName());
    }

    @Test
    public void givenClass_whenGetsPublicFieldByName_thenCorrect() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Field field = birdClass.getField("CATEGORY");

        assertEquals("CATEGORY", field.getName());
    }

    @Test
    public void givenClass_whenGetsDeclaredFields_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Field[] fields = birdClass.getDeclaredFields();

        assertEquals(1, fields.length);
        assertEquals("walks", fields[0].getName());
    }

    @Test
    public void givenClass_whenGetsFieldsByName_thenCorrect() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Field field = birdClass.getDeclaredField("walks");

        assertEquals("walks", field.getName());
    }

    @Test
    public void givenClassField_whenGetsType_thenCorrect() throws ClassNotFoundException, NoSuchFieldException {
        Field field = Class.forName("reflection.Bird").getDeclaredField("walks");
        Class<?> fieldClass = field.getType();

        assertEquals("boolean", fieldClass.getSimpleName());
    }

    @Test
    public void givenClassField_whenSetsAndGetsValue_thenCorrect() throws ReflectiveOperationException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        Field field = birdClass.getDeclaredField("walks");
        field.setAccessible(true);

        assertFalse(field.getBoolean(bird));
        assertFalse(bird.walks());

        field.setBoolean(bird, true);

        assertTrue(field.getBoolean(bird));
        assertTrue(bird.walks());
    }

    @Test
    public void givenClassField_whenGetsAndSetsWithNull_thenCorrect() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Field field = birdClass.getField("CATEGORY");
        field.setAccessible(true); //it is declared as public static, we don’t need an instance of the class containing them.

        assertEquals("domestic", field.get(null)); // static -> no need a target instance
    }

    @Test
    public void givenClass_whenGetsAllPublicMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Method[] methods = birdClass.getMethods();
        List<String> methodNames = getMethods(methods);

        assertTrue(methodNames.containsAll(Arrays.asList("equals", "notifyAll", "hashCode", "walks", "eats", "toString")));
    }

    private List<String> getMethods(Method[] methods) {
        ArrayList<String> methodNames = new ArrayList<>();
        for (Method method :
                methods) {
            methodNames.add(method.getName());
        }
        return methodNames;
    }

    @Test
    public void givenMethodName_whenGetsMethod_thenCorrect() throws NoSuchMethodException {
        Bird bird = new Bird();
        Method walksMethod = bird.getClass().getDeclaredMethod("walks");
        Method setWalksMethod = bird.getClass().getDeclaredMethod("setWalks", boolean.class);

        assertTrue(walksMethod.canAccess(bird));
        assertTrue(setWalksMethod.canAccess(bird));
    }

    @Test
    public void givenMethod_whenInvokes_thenCorrect() throws ReflectiveOperationException {
        Class<?> birdClass = Class.forName("reflection.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
        Method walksMethod = birdClass.getDeclaredMethod("walks");
        boolean walks = (boolean) walksMethod.invoke(bird);

        assertFalse(walks);
        assertFalse(bird.walks());

        setWalksMethod.invoke(bird, true);

        boolean walks2 = (boolean) walksMethod.invoke(bird);
        assertTrue(walks2);
        assertTrue(bird.walks());
    }
}