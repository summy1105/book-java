package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoatTest {

    @Test
    public void givenObject_whenGetsClassName_thenCorrect() {
        Object goat = new Goat("goat");
        Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("reflection.Goat", clazz.getName());
        assertEquals("reflection.Goat", clazz.getCanonicalName());
    }

    @Test
    public void givenClassName_whenCreatesObject_thenCorrect() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("reflection.Goat");

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("reflection.Goat", clazz.getName());
        assertEquals("reflection.Goat", clazz.getCanonicalName());
    }

    @Test
    public void givenClass_whenRecognisesModifiers_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("reflection.Goat");
        Class<?> animalClass = Class.forName("reflection.Animal");

        int goatModifiers = goatClass.getModifiers();
        int animalModifiers = animalClass.getModifiers();

        //PUBLIC = 0x00000001;
        System.out.println("goatModifiers = " + Integer.toString(goatModifiers, 16));

        //ABSTRACT = 0x00000400; + PUBLIC = 0x00000001;
        System.out.println("animalModifiers = " + Integer.toString(animalModifiers, 16));

        assertTrue(Modifier.isPublic(goatModifiers));
        assertTrue(Modifier.isAbstract(animalModifiers));
        assertTrue(Modifier.isPublic(animalModifiers));
    }

    @Test
    public void givenClass_whenGetsPackageInfo_thenCorrect() {
        Goat goat = new Goat("goat");
        Class<?> goatClass = goat.getClass();
        Package pkg = goatClass.getPackage();

        assertEquals("reflection", pkg.getName());
    }

    @Test
    public void givenClass_whenGetsSuperClass_thenCorrect() {
        Goat goat = new Goat("goat");
        String str = "any string";

        Class<? extends Goat> goatClass = goat.getClass();
        Class<?> goatSuperClass = goatClass.getSuperclass();

        assertEquals("Animal", goatSuperClass.getSimpleName());
        assertEquals("Object", str.getClass().getSuperclass().getSimpleName());
    }

    @Test
    public void givenClass_whenGetsImplementInterfaces_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("reflection.Goat");
        Class<?> animalClass = Class.forName("reflection.Animal");

        Class<?>[] goatInterfaces = goatClass.getInterfaces();
        Class<?>[] animalInterfaces = animalClass.getInterfaces();

        assertEquals(1, goatInterfaces.length);
        assertEquals(1, animalInterfaces.length);
        assertEquals("Locomotion", goatInterfaces[0].getSimpleName());
        assertEquals("Eating", animalInterfaces[0].getSimpleName());
    }

    @Test
    public void givenClass_whenGetsConstructor_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("reflection.Goat");

        Constructor<?>[] constructors = goatClass.getConstructors();

        assertEquals(1, constructors.length);
        assertEquals("reflection.Goat", constructors[0].getName());
    }

    @Test
    public void givenClass_whenGetsFields_thenCorrect() throws ClassNotFoundException {
        Class<?> animalClass = Class.forName("reflection.Animal");
        Field[] declaredFields = animalClass.getDeclaredFields();
        List<String> actualFields = getFieldNames(declaredFields);

        assertEquals(2, actualFields.size());
        assertTrue(actualFields.containsAll(Arrays.asList("name", "CATEGORY")));
    }


    private static List<String> getFieldNames(Field[] fields) {
        ArrayList<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    @Test
    public void givenClass_whenGetsMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> animalClass = Class.forName("reflection.Animal");
        Method[] methods = animalClass.getDeclaredMethods();
        List<String> actualMethods = getMethodNames(methods);

        assertEquals(3, actualMethods.size()); // constructor 포함X
        assertTrue(actualMethods.containsAll(Arrays.asList("getName", "setName", "getSound")));
    }

    private List<String> getMethodNames(Method[] methods) {
        ArrayList<String> methodNames = new ArrayList<>();
        for (Method method: methods) {
            methodNames.add(method.getName());
            System.out.println("method.getName() = " + method.getName());
        }
        return methodNames;
    }

}