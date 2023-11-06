package ClassifierHeartDisease;

import weka.classifiers.Classifier;
import weka.core.Instances;

public class Controlling {

    // Commandline arguments class instance
    private final CommandLineParsing starter;
    // Storing model
    private Classifier model;
    // Storing data
    private Instances data;
    // Storing predictions/classifications
    private Instances predictions;
    // Boolean for if the labels are given
    // (for confusionmatrix and areaUnderAOC option)
    private boolean labelsGiven;


    /**
     * Constructor method that stores the
     * commandline arugments class instance.
     * Also calls methods of the class.
     *
     * @param starter the starter
     */
    public Controlling(CommandLineParsing starter) {
        // Storing the commandline argument instance
        this.starter = starter;
        // loading model and user data
        loading();
        // making the predictions
        predicting();
        // User feedback
        userScore();
    }

    /**
     * Method that loads ALL the data.
     * It calls two other classes:
     *  - class for loading user data
     *  - class for loading the model
     */
    private void loading() {
        // load model
        this.model = new LoadingModelData().loadClassifier();
        // Loading user data
        LoadingUserData userData = new LoadingUserData();
        // The commandline arguments are passed, this way the user can access the filename
        this.data = userData.loadData(this.starter);
        // Getting boolean if labels were in the original data
        this.labelsGiven = userData.isLabelsGiven();
    }

    /**
     * Method that instantiates the classifying class
     * In this class, the main part of the application is
     * running.
     */
    private void predicting(){
        // Making the predictions in the Classify class
        this.predictions = new Classify().classifyData(this.model, this.data);
    }

    /**
     * Method which displays the correct output depending on the user.
     * Three output options are available:
     *  1. Confusion matrix
     *  2. AreaUnderAOC
     *  3. Writing predictions to outputfile
     *
     *  If the user didn't specify any output,
     *  the predictions are being written to sys.stdout
     */
    private void userScore() {
        // Instantiate the confusion matrix class
        if (this.starter.isConfusionMatrix()) {
            // Only when the class labels are given, the confusion matrix can be constructed
            if (!this.labelsGiven) {
                throw new IllegalArgumentException("User didn't provide labels at input");
            } else System.out.println(new ConfusionMatrix().getFeedback(this.predictions, this.data));}

        // Instantiate the area under AOC class
        if (this.starter.isAreaUnderAOC()) {
            if (!this.labelsGiven) {
                throw new IllegalArgumentException("User didn't provide labels at input");
            } else System.out.println(new AreaUnderAOC(this.model).getFeedback(this.data, this.predictions));}

        // Instantiate the writing output class
        if (this.starter.isWritingOut()) {
            WritingOutput writingOutput = new WritingOutput(this.starter);
            System.out.println(writingOutput.getFeedback(this.data, this.predictions));}

        // User didn't provide any feedback
        if (!this.starter.isConfusionMatrix() & !this.starter.isAreaUnderAOC() & !this.starter.isWritingOut()) {
            System.out.println("\nPredictions:" + "\n" + this.predictions + "\n");
        }
    }
}
