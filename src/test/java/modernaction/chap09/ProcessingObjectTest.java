package modernaction.chap09;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingObjectTest {

    @Test
    public void chainOfResponsibilityPatternTest() {
        ProcessingObject<String> p1 = new ProcessingObject.HeaderTextProcessing();
        ProcessingObject<String> p2 = new ProcessingObject.SpellCheckerProcessing();

        p1.setSuccessor(p2);
        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println("result = " + result);
    }

    @Test
    public void lambdaChainOfResponsibilityPatternTest() {
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

        String result = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println("result = " + result);
    }

}