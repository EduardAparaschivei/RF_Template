package ro.usv.rf.utils;

public class DistanceMatrix {
    private double[][] matDist;

    public DistanceMatrix(double [][] patternSet){
        matDist = new double[patternSet.length][patternSet.length];
        for(int i=0;i<patternSet.length;i++){
            for(int j = 0;j<patternSet.length;j++){
                matDist[i][j] = DistanceUtils.distEuclid(patternSet[i],patternSet[j]);
            }
        }
    }

    @Override
    public String toString() {
        String str = new String();
        for(double[] lineset : matDist){
            for(double vLine : lineset){
                str += String.format("%.2f ", vLine);
            }
            str+="\n";
        }
        return str;
    }

    public double[][] neighbours(int i){
        double[][] neigh = new double[2][matDist[0].length];
        for(int j=0;j<matDist[0].length;j++){
            neigh[0][j] = j;
            neigh[1][j] = matDist[i][j];
        }

        for(int j=0;j<neigh[0].length;j++){
            for(int x=j+1;x<neigh[0].length-1;x++){
                if(neigh[1][x+1] < neigh[1][x]){
                     double aux = neigh[1][x+1];

                }
            }
        }


        return neigh;
    }
}
