package ro.usv.rf.utils;

public class DataUtils {

    public static void printMatrix(double[][] x){
        for(double[] lin: x){

            for(double xcrt: lin)
                System.out.print(xcrt+"\t");
            System.out.println();
        }
    }

    public static double[][] normalizeLearningSet(double[][] patternSet)
    {
        double[][] normalizedPatternSet = new double[patternSet.length][patternSet[0].length];

        for(int j=0;j<patternSet[0].length;j++){

            double minj = patternSet[0][j];
            double maxj = patternSet[0][j];

            for(int i=1;i<patternSet.length;i++){

                if(patternSet[i][j] > maxj){maxj = patternSet[i][j];}
                if(patternSet[i][j] < minj){minj = patternSet[i][j];}
            }

            for(int i=0;i<patternSet.length;i++){

                normalizedPatternSet[i][j] = (patternSet[i][j] - minj)/(maxj-minj);
            }
        }

        return normalizedPatternSet;
    }
}
