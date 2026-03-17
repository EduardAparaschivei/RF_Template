package ro.usv.rf.utils;

import java.util.LinkedHashMap;
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
	

	
	public static Map<Pattern, Double> getPatternsMapFromInitialSet(double[][] patternSet) {
		Map<Pattern, Double> patternsMap = new LinkedHashMap<Pattern, Double>();
		
		for (int i=0;i<patternSet.length; i++)
		{
			Pattern patternObject = new Pattern( patternSet[i]);
			if(patternsMap.containsKey(patternObject))
			{
				double currentWeight = patternsMap.get(patternObject);
				double newWeight = currentWeight +1;
				patternsMap.put(patternObject, newWeight);
			}
			else
			{
				patternsMap.put(patternObject, 1.);
			}
		}
		return patternsMap;
	}

	public static Map<Pattern, Pair<Double, Integer>> getPatternsMapFromInitialSet
			(double[][] patternSet, int []iClass) {
		Map<Pattern, Pair<Double, Integer>> patternsMap = new LinkedHashMap<>();
		for (int i=0;i<patternSet.length; i++)
		{
			Pattern patternObject = new Pattern( patternSet[i]);
			if(patternsMap.containsKey(patternObject))
			{
				Pair<Double, Integer> pGasit = patternsMap.get(patternObject);
				if(pGasit.getB()==iClass[i]) {
					pGasit.setA(pGasit.getA() + 1);
					patternsMap.put(patternObject,pGasit);
				}else{
					throw new RuntimeException("Identical Patterns:"+ patternSet[i]+
							"with different classes: "+pGasit.getB()+" and "+iClass[i]+"." );
				}
			}else
			{patternsMap.put(patternObject, new Pair(1., iClass[i]));
			}}
		return patternsMap;
	}


	public static double[] calculateWeightedAverages(Map<Pattern, Double> patternsMap, int numberOfFeatures) {
		double[]  weightedAverages = new double[numberOfFeatures];
		int totalWeight = 0;
		for(Map.Entry<Pattern, Double> entry : patternsMap.entrySet())
		{
			Pattern patternObject = entry.getKey();
			double weight = entry.getValue();
			totalWeight += weight;
			double[] patternValues = patternObject.getPatternValues();
			for (int j=0; j<numberOfFeatures; j++)
			{
				weightedAverages[j] += patternValues[j] * weight;
			}
		}
		for (int j=0; j<numberOfFeatures; j++)
		{
			weightedAverages[j] /= totalWeight;
			System.out.println(weightedAverages[j]);
		}

		return weightedAverages;
	}
	
	public static double[] getPatternWeigths(Map<Pattern, Double> patternsMap) {
		return patternsMap.values().stream()
				.mapToDouble(x -> x.doubleValue() )
				.toArray();		
	}

	public static double[][] getPatterns(Map<Pattern, Double> patternsMap) {
		return patternsMap.keySet().stream()
				.map(Pattern::getPatternValues )
				.toArray(double[][]::new);		
	}

	public static double[] calculateWeightedAverages(double[][] X, double[] f) {
		// avgj = Sigma(xj*fi) / Sigma(fi)
		int n = X.length;
		int p = X[0].length;
		int j;
		double[] avgj = new double [p];
		//to add implementation
		return avgj;
	}

	public static double[] calculateFeaturesStandardDeviations(double[][] X, double[] f, double[] avgj) {
		// stdj = Sigma[fi(xj - avgj)^2] / Sigma(fi)
		//
		int n = X.length;
		int p = X[0].length;
		int j;
		double[] stdj = new double [p];
        //to add implementation
		return  stdj;
		
	}

	
	public static double[] calculateFeaturesStandardDeviations(double[][] X, double[] f) {
		// stdj = Sigma[fi(xj - avgj)^2] / Sigma(fi)
		double avgj[] = calculateWeightedAverages(X, f);
		return calculateFeaturesStandardDeviations(X, f, avgj);
	}
	
	public double[] calculateFeaturesAverages(double[][] X) {
		return calculateWeightedAverages(getPatternsMapFromInitialSet(X), X[0].length);
	}
	

}
