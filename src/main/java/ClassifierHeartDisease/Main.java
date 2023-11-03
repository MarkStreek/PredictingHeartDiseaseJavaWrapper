package ClassifierHeartDisease;

public class Main {

    /**
     * The entry point of application.
     * Parses the command line arguments and runs the application
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        CommandLineParsing starter = new CommandLineParsing();
        try {
            starter.initialize(args);
            System.out.println("Starting Classifier Application...");
            Controlling controlling = new Controlling(starter);
        } catch (Exception e) {
            System.err.println((String.format("Something went wrong on Line: %d: %s: %s",
                    e.getStackTrace()[0].getLineNumber(),
                    e.getClass().getSimpleName(), e.getMessage())));
            starter.help();
        }
    }
}
