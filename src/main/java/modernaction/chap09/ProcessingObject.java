package modernaction.chap09;

public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {
        T r = handleWork(input);
        if (successor != null) {
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);


    public static class HeaderTextProcessing extends ProcessingObject<String> {
        @Override
        protected String handleWork(String text) {
            return "From Raoul, Mario and Alan: " + text;
        }
    }

    public static class SpellCheckerProcessing extends ProcessingObject<String> {
        @Override
        protected String handleWork(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }
}
