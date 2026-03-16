package ro.usv.rf.utils;

public class EuclidianDistance implements IDistance{

    @Override
    public double distance(double[] a, double[] b) {
        return DistanceUtils.distEuclid(a,b);
    }
}
