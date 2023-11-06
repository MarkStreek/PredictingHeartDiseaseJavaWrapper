package ClassifierHeartDisease;

import weka.classifiers.Classifier;
import weka.core.Instances;

public class Classify {

    /**
     * Method that classifies the data instances.
     *
     * @param model the model
     * @param data  the data
     * @return the (predicted)instances
     */
    public Instances classifyData(Classifier model, Instances data) {
        // New instances to store the classified instances
        Instances labeled = new Instances(data);

        // looping though all the data instances
        for (int i = 0; i < data.numInstances(); i++) {
                try {
                    // Classify the data instances
                    double label = model.classifyInstance(data.instance(i));
                    // change the class label
                    labeled.instance(i).setClassValue(label);
                } catch (Exception e) {
                    throw new RuntimeException("An Error occurred while classifying" + e);
                }
        }
        // return the classified instances to controlling class
        return labeled;
    }
}