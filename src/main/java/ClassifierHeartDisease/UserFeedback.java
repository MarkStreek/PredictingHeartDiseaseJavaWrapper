package ClassifierHeartDisease;

import weka.core.Instances;

public interface UserFeedback {

    /**
     * getFeedback contract
     * This contract has multiple implementations,
     * because the output of the application can be different (duh...)
     *
     * @param data        the data
     * @param predictions the predictions
     * @return the feedback
     */
    String getFeedback(Instances data, Instances predictions);
}
