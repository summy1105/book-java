package standard.ch12.annotation;

public class AnnotationEx2 {
    static class NewClass {
        int newField;
        int getNewField(){return newField;}

        @Deprecated
        int oldField;
        @Deprecated
        int getOldField(){return oldField;}
    }

    public static void main(String[] args) {
        NewClass nc = new NewClass();
        nc.oldField = 10;
        System.out.println("nc.getOldField() = " + nc.getOldField());
    }
}
