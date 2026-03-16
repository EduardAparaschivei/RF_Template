package ro.usv.rf.utils;

public class CityBlockDistance implements IDistance{

    @Override
    public double distance(double[] a, double[] b) {
        return DistanceUtils.distCity(a,b);
    }
}
