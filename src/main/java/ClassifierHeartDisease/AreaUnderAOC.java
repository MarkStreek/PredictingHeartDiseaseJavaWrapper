package ClassifierHeartDisease;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class AreaUnderAOC implements UserFeedback{

    // Storing the model
    private final Classifier model;


    /**
     * Constructor method of the class
     *
     * @param model the model
     */
    public AreaUnderAOC(Classifier model) {
        this.model = model;
    }

    /**
     * Area Under AOC implementation of the contract
     * @param data
     * @param predictions
     * @return
     */
    @Override
    public String getFeedback(Instances data, Instances predictions) {
        try {
            Evaluation eval = new Evaluation(predictions);
            eval.evaluateModel(this.model, data);
            return String.format("Area Under ROC: " + eval.areaUnderROC(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
