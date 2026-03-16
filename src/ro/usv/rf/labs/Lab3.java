package ro.usv.rf.labs;

import ro.usv.rf.utils.DataUtils;
import ro.usv.rf.utils.DistanceMatrix;
import ro.usv.rf.utils.DistanceUtils;
import ro.usv.rf.utils.FileUtils;

import java.util.Arrays;

public class Lab3 {
    public static void main(String[] args) {
        double[][] patternset = FileUtils.readLearningSetFromFile("in2.txt");
        int numberOfPatterns = patternset.length;
        int numberOfFeatures = patternset[0].length;

        double dist = DistanceUtils.distEuclid(patternset[0],patternset[1]);

        DistanceMatrix distMat = new DistanceMatrix(patternset);
        System.out.println(distMat.toString());
        System.out.println();
        double[][] neighbours = distMat.neighbours(0);
        System.out.println(Arrays.deepToString(neighbours));

    }
}
