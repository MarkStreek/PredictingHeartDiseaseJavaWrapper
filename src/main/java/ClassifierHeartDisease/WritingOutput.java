package ClassifierHeartDisease;

import weka.core.Instances;
import java.io.FileWriter;

public class WritingOutput implements UserFeedback{

    // Storing the commandLine arguments
    private final CommandLineParsing starter;

    /**
     * Constructor method of the class.
     *
     * @param starter the starter
     */
    public WritingOutput(CommandLineParsing starter) {
        this.starter = starter;
    }

    /**
     * Method that implements the output writing contract.
     * It calls the other method and the result of that methods
     * is returned to the controlling class.
     *
     * @param data
     * @param predictions
     * @return message
     */
    @Override
    public String getFeedback(Instances data, Instances predictions) {
        System.out.println("Trying to write the predictions to output file...");
        writeOutput(predictions);
        return "Output was successfully written to output file!";
    }

    /**
     * Method that writes the output predictions to a file.
     *
     * @param predictions
     */
    private void writeOutput(Instances predictions) {
        try {
            // Call the getter method to get the output filename
            String filename = this.starter.getOutputFilename();
            // Check if the filename ends with .arff, otherwise add it
            if (!filename.endsWith(".arff")) {
                filename += ".arff";}
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(String.valueOf(predictions));
            myWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Error writing output to a file:");
        }
    }
}
