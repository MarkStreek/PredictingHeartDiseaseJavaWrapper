package ClassifierHeartDisease;

import weka.classifiers.Classifier;
import weka.core.SerializationHelper;

import java.io.File;
import java.io.InputStream;

public class LoadingModelData {

    private static final String MODELNAME = "Stacking_Heart_Disease_Model.model";
    private InputStream MODELCLASSIFIER = getClass().getClassLoader().getResourceAsStream(MODELNAME);

    public Classifier loadClassifier() {
            try {
                return (Classifier) SerializationHelper.read(this.MODELCLASSIFIER);
            } catch (Exception e) {
                throw new RuntimeException("Error loading the classifier model");
            }
            // check the existence of the model
    }
}