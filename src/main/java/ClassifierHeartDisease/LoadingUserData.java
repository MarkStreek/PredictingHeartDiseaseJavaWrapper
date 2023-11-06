package ClassifierHeartDisease;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import java.io.File;
import java.util.List;

public class LoadingUserData {

    // Boolean if the user provided class labels
    private boolean labelsGiven;

    /**
     * Method that loads data instances.
     * It also tries to add a new attribute to the data.
     * This is done because this attribute is going to
     * store the classified prediction.
     *
     * @param starter the starter
     * @return the instances
     */
    public Instances loadData(CommandLineParsing starter) {

        if (new File(starter.getFilename()).isFile()) {
            try {
                // Reading the data
                ConverterUtils.DataSource reader = new ConverterUtils.DataSource(starter.getFilename());
                Instances data = reader.getDataSet();
                try {
                    // Try to add a new attribute, this is the attribute to predict
                    List<String> classLabels = List.of("Absence", "Presence");
                    Attribute newClassAttribute = new Attribute("Heart.Disease", classLabels);
                    data.insertAttributeAt(newClassAttribute, data.numAttributes());
                    data.setClassIndex(data.numAttributes() - 1);
                    this.labelsGiven = false;
                } catch (IllegalArgumentException e) {
                    // If the attribute already exists, set this as class label
                    data.setClassIndex(data.numAttributes() - 1);
                    this.labelsGiven = true;
                }
                return data;
            } catch (Exception e) {
                throw new RuntimeException(String.format("(Line %d) %s: %s",
                        e.getStackTrace()[0].getLineNumber(),
                        e.getClass().getSimpleName(), e.getMessage()));
            }
        } else throw new RuntimeException("Something is wrong with the given file!");
    }

    /**
     * Getter method to retrieve the option
     * if the labels are given.
     *
     * @return the boolean
     */
    public boolean isLabelsGiven() {
        return labelsGiven;
    }
}
