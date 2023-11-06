package ClassifierHeartDisease;

import org.apache.commons.cli.*;

public class CommandLineParsing {

    private CommandLine commandLine;
    // Variable for storing the commandline arguments
    private String[] arg;
    // Variable for storing the options
    private final Options options = new Options();

    /**
     * Constructor of the Class where the commandline
     * arguments are declared and the methods are called.
     *
     * @param args the arg
     */
    public void initialize(String[] args) {
        this.arg = args;
        buildOptions();
        processCommandLine();
    }

    /**
     * Method that creates/build the argument options
     * The options are stored in 'options'
     */
    private void buildOptions() {
        //Filename option
        Option input = new Option("f", "filename", true, "Name of the file");
        input.setRequired(true);
        options.addOption(input);
        // Confusion matrix option, only when class labels are specified
        Option confusionMatrix = new Option("m", "confusionMatrix", false, "Show the confustion Matrix of the predictions");
        confusionMatrix.setRequired(false);
        options.addOption(confusionMatrix);
        // Area under AOC option, only when class labels are specified
        Option areaUnderAOC = new Option("a", "areaUnderAOC", false, "Get the area under AOC");
        areaUnderAOC.setRequired(false);
        options.addOption(areaUnderAOC);
        // Option to define a filename for output
        Option writingOut = new Option("w", "writingOut", true, "Writing the output to a file");
        writingOut.setRequired(false);
        options.addOption(writingOut);
    }

    /**
     * Method that's actually parses the commandline arguments.
     * The information is stored in the variable: this.commandLine
     */
    private void processCommandLine() {
        try {
            CommandLineParser parser = new DefaultParser();
            this.commandLine = parser.parse(this.options, this.arg);
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("(Line %d) %s: %s",
                    e.getStackTrace()[0].getLineNumber(),
                    e.getClass().getSimpleName(), e.getMessage()));
        }
    }

    /**
     * Help method that prints the help format message
     */
    public void help(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Define the following arguments:", this.options);
    }

    /**
     * Getter method for retrieving the filename
     *
     * @return the filename
     */
    public String getFilename() {
        return this.commandLine.getOptionValue("filename");
    }

    /**
     * Getter method for retrieving the option
     * if the confusion matrix option is present.
     *
     * @return the boolean
     */
    public boolean isConfusionMatrix() {
        return this.commandLine.hasOption("confusionMatrix");
    }

    /**
     * Getter method for retrieving the option
     * if the area under AOC option is present.
     *
     * @return the boolean
     */
    public boolean isAreaUnderAOC() {
        return this.commandLine.hasOption("areaUnderAOC");
    }

    /**
     * Getter method for retrieving the option
     * if the writing output option is present.
     *
     * @return the boolean
     */
    public boolean isWritingOut() {
        return this.commandLine.hasOption("writingOut");
    }

    /**
     * Getter method for retrieving the output filename
     *
     * @return the output filename
     */
    public String getOutputFilename() {
        return this.commandLine.getOptionValue("writingOut");
    }
}
