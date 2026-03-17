package ro.usv.rf.utils;

/*
 * Autor:PSG  
 */
public class DistanceUtils {
	
	public static double distEuclid ( double x[], double y[] )  {
		if(x.length != y.length) throw new DifferentSpaceSizeException("("+x.length+", "+y.length+")");
		
		double d = 0;
		for(int j=0; j< x.length; j++)
			d += (x[j]-y[j])* (x[j]-y[j]);
	    return Math.sqrt(d);
	}

	public static double distCityBlock ( double x[], double y[] )  {
		if(x.length != y.length) throw new DifferentSpaceSizeException("("+x.length+", "+y.length+")");
		
		double d = 0;

	    return d;
	}
	public static double distCanberra ( double x[], double y[] )  {
		if(x.length != y.length) throw new DifferentSpaceSizeException("("+x.length+", "+y.length+")");
		
		double d = 0;

	    return d;
	}

	public static double distCebisev ( double x[], double y[] )  {
		if(x.length != y.length) throw new DifferentSpaceSizeException("("+x.length+", "+y.length+")");
		
		double dmax = 0;

 	    return dmax;
	}
	
}



class DifferentSpaceSizeException extends RuntimeException{

	public DifferentSpaceSizeException(String message) {
		super(message);
	}
}
