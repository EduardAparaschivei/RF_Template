package ro.usv.rf.labs;

import ro.usv.rf.classifiers.Classifier_1NN;
import ro.usv.rf.learningsets.SupervisedLearningSet;
import ro.usv.rf.utils.DistanceUtils;

import static ro.usv.rf.classifiers.Classifier_1NN.classifyAndDisplayResult;

public class Lab4_Acasa {
    public static void main(String[] args) {
// for Problem 2.1.
        String[] numeClase = new String[] {"","A", "B", "C"};
        SupervisedLearningSet setSuperv3 = new SupervisedLearningSet("testexam_numeric.txt", numeClase);
        Classifier_1NN nnClassifier_1nn = new Classifier_1NN(DistanceUtils::distCityBlock);
       // nnClassifier_1nn.setDebug(false);
        setSuperv3.getF()[3]=2; // to prove ambiguities solution
        nnClassifier_1nn.train(setSuperv3);
        System.out.println("indiceClasa (cf. dist CityBlock)="+nnClassifier_1nn.predict(new double[] {2,4}));
        nnClassifier_1nn = new Classifier_1NN(); //Euclidian Distance
        nnClassifier_1nn.train(setSuperv3);
        System.out.println("indiceClasa (cf. dist. Euclidiene)="+nnClassifier_1nn.predict(new double[] {2,4}));
// for Problem 2.2.

        double[][] testSet = new double[][]{ {2, 4}, {4, 2}, {10, 5}, {5,5} };
    classifyAndDisplayResult ( nnClassifier_1nn, numeClase,testSet);

    classifyAndDisplayResult ( nnClassifier_1nn,numeClase, setSuperv3.getX());
}
} // end of class
