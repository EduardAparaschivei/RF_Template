package ro.usv.rf.utils;

public class DistanceMatrix {
    private double[][] matDist;

    public DistanceMatrix(double[][] patternSet, IDistance metric){
        matDist = new double[patternSet.length][];
        for(int i=0;i<patternSet.length;i++){
            matDist[i] = new double[i+1];
            for(int j = 0;j<=i;j++){
                matDist[i][j] = metric.distance(patternSet[i],patternSet[j]);
            }
        }
    }

    public double d(int i, int j){
        if(i >= j){
            return matDist[i][j];
        }else {
            return matDist[j][i];
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for(int i=0;i<matDist.length;i++){
            for(int j=0;j<matDist.length;j++){
                str.append(String.format("%.2f ", d(i, j)));
            }
            str.append("\n");
        }
        return str.toString();
    }

    public double[][] neighbours(int i){
        double[][] neigh = new double[2][matDist.length];
        for(int j=0;j<matDist.length;j++){
            neigh[0][j] = j;
            neigh[1][j] = d(i,j);
        }

        for(int j = 0; j < neigh[0].length - 1; j++){
            for(int x = j + 1; x < neigh[0].length; x++){
                if(neigh[1][x] < neigh[1][j]){
                    double aux = neigh[1][j];
                    neigh[1][j] = neigh[1][x];
                    neigh[1][x] = aux;

                    aux = neigh[0][j];
                    neigh[0][j] = neigh[0][x];
                    neigh[0][x] = aux;
                }
            }
        }


        return neigh;
    }


}
