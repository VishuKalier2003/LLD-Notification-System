package logging.logger.utility;

import org.springframework.stereotype.Component;

@Component
public class Printer {

    private final StringBuilder builder;

    @SuppressWarnings("unused")
    private Printer() {
        this.builder = new StringBuilder();
    }
    
    public void printDouble() {
        builder.setLength(0);
        System.out.println(builder.append("=".repeat(70)).toString());
    }

    public void printSingle() {
        builder.setLength(0);
        System.out.println(builder.append("-".repeat(70)).toString());
    }

    public void printWord(String c, String w) {
        builder.setLength(0);
        System.out.println(builder.append("Class ").append(c).append(" : ").append(w).toString());
    }

    public void printtest(int a) {
        builder.setLength(0);
        builder.append(" ".repeat(30));
        System.out.println(builder.append("TEST ").append(a).toString());
    }
}
