package ClassifierHeartDisease;

import weka.classifiers.Classifier;
import weka.core.SerializationHelper;
import java.io.InputStream;

/**
 * The type Loading model data.
 */
public class LoadingModelData {

    // Model filename
    private static final String MODELNAME = "Stacking_Heart_Disease_Model.model";
    // Declaring the modelfile, because it's placed inside the resources folder
    private InputStream MODELCLASSIFIER = getClass().getClassLoader().getResourceAsStream(MODELNAME);


    /**
     * Method that loads the classifier model.
     *
     * @return the classifier
     */
    public Classifier loadClassifier() {
            try {
                return (Classifier) SerializationHelper.read(this.MODELCLASSIFIER);
            } catch (Exception e) {
                throw new RuntimeException("Error loading the classifier model");
            }
    }
}