package ro.usv.rf.utils;

public class ChebyshevDistance implements IDistance{

    @Override
    public double distance(double[] a, double[] b) {
        return DistanceUtils.distCheb(a,b);
    }
}
