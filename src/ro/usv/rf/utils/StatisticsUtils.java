package ro.usv.rf.utils;

import ro.usv.rf.Pattern;

import java.util.HashMap;
import java.util.Map;

public class StatisticsUtils {
	
	public static double calculateFeatureAverage(double[] feature)
	{
		double average = 0.0;
		for (int i=0; i<feature.length; i++)
		{
			average += feature[i];
		}
		average = average/feature.length;
		return average;
	}
	
	public static Map<Pattern, Integer> getPatternsMapFromInitialSet(double[][] patternSet) {
		Map<Pattern, Integer> patternsMap = new HashMap<Pattern, Integer>();
		// enter code here
		return patternsMap;
	}

	public static double[] calculateWeightedAverages(Map<Pattern, Integer> patternsMap, int numberOfFeatures) {
		double[]  weightedAverages = new double[numberOfFeatures];
		//enter code here
		return weightedAverages;
	}
}
