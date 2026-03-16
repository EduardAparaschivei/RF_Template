package ro.usv.rf.utils;

import static ro.usv.rf.utils.DistanceUtils.distCheb;

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

}

class SpatiiDeDimensiuniDiferite extends RuntimeException{
    public SpatiiDeDimensiuniDiferite(String message) {
        super(message);
    }
}
