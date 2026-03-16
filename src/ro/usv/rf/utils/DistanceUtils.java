package ro.usv.rf.utils;

public class DistanceUtils {

    public static double distEuclid ( double x[], double y[] ) {
        if(x.length != y.length) throw new SpatiiDeDimensiuniDiferite(
                "("+x.length+", "+y.length+")");

        double d = 0;
        for(int j=0; j< x.length; j++)
            d += (x[j]-y[j])* (x[j]-y[j]);

        return Math.sqrt(d);
    }

    public static double distCity(double x[], double y[]){
        if(x.length != y.length) throw new SpatiiDeDimensiuniDiferite(
                "("+x.length+", "+y.length+")");

        double d = 0;
        for(int j=0; j< x.length; j++)
            d += Math.abs(x[j]-y[j]);

        return d;
    }

    public static double distCheb(double x[], double y[]){
        if(x.length != y.length) throw new SpatiiDeDimensiuniDiferite(
                "("+x.length+", "+y.length+")");

        double d = 0;
        for(int j=0; j< x.length; j++)
            d = Math.max(d, Math.abs(x[j]-y[j]));

        return d;
    }

    public static double[][] matDistEuclid(double[][] patternSet, int numberOfPatterns, int numberOfFeatures){
        double[][] distMatrix = new double[numberOfPatterns][numberOfPatterns];
        for(int i=0;i<numberOfPatterns;i++){
            for(int j=0;j<numberOfPatterns;j++){
                double d = 0;
                for(int x=0;x<numberOfFeatures;x++){
                    d += (patternSet[i][x]-patternSet[j][x]) * (patternSet[i][x]-patternSet[j][x]);
                }
                d = Math.sqrt(d);
                distMatrix[i][j] = d;
            }
        }
        return distMatrix;
    }
}

class SpatiiDeDimensiuniDiferite extends RuntimeException{
    public SpatiiDeDimensiuniDiferite(String message) {
        super(message);
    }
}
