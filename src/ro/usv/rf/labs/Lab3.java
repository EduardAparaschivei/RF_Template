package ro.usv.rf.labs;

import ro.usv.rf.utils.*;

import java.util.Arrays;

public class Lab3 {
    public static void main(String[] args) {
        double[][] patternset = FileUtils.readLearningSetFromFile("in2.txt");
        int numberOfPatterns = patternset.length;
        int numberOfFeatures = patternset[0].length;

        double dist = DistanceUtils.distEuclid(patternset[0],patternset[1]);

        DistanceMatrix distMat = new DistanceMatrix(patternset, new CityBlockDistance());
        System.out.println(distMat);
        System.out.println();
        double[][] neighbours = distMat.neighbours(6);

        for(double[] i : neighbours){
            for(double j : i){
                String str = String.format("%.2f ", j);
                System.out.print(str);
            }
            System.out.println();
        }

    }
}
