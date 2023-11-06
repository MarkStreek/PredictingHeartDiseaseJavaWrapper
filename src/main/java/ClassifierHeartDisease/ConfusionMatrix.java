package ClassifierHeartDisease;

import weka.core.Instances;

public class ConfusionMatrix implements UserFeedback{

    // Variables for the confusion matrix
    // For extra additions in the future, these variables could be used for rates
    private int TP = 0;
    private int FN = 0;
    private int FP = 0;
    private int TN = 0;

    /**
     * Method that implements the confusion matrix contract.
     * This method calculates the values for the matrix.
     *
     * @param data
     * @param predictions
     * @return the confusion matrix
     */
    @Override
    public String getFeedback(Instances data, Instances predictions) {
        double classified;
        double actual;
        // looping through both, the predicted as the original instances
        for (int i = 0; i < data.numInstances(); i++) {
            // Retrieve the class labels
            classified = predictions.instance(i).classValue();
            actual = data.instance(i).classValue();
            if (classified == 0.0) {
                if (actual == 0.0) {
                    TN++;
                } else {FN++;}
            } else if (actual == 0.0) {
                FP++;
            } else {TP++;}
        }
        // Return the confusion matrix
        return String.format("\nClassified as: \nPresence | " +
                "Absence \n---------|--------\n\t%d  | %d " +
                "\n\t%d   | %d", TN, FP, FN, TP);
    }
}
