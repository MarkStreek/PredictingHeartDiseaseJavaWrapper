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
            System.out.println("Starting Classifier Application...");
            // Parsing the command line arguments
            starter.initialize(args);
            // Starting the program
            // Why parsing the starter variable as argument?
            // This way, we can call the getter methods of this class!
            Controlling controlling = new Controlling(starter);
            System.out.println("Closing application... Bye!");
        } catch (Exception e) {
            System.err.println((String.format("Something went wrong on Line: %d: %s: %s",
                    e.getStackTrace()[0].getLineNumber(),
                    e.getClass().getSimpleName(), e.getMessage())));
            starter.help();
        }
    }
}
