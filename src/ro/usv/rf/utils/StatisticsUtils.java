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
		for(int i=0; i<patternSet.length; i++){
			Pattern p = new Pattern(patternSet[i]);
			if(!patternsMap.containsKey(p)){
				patternsMap.put(p, 1);
			}
			else {
				patternsMap.put(p,patternsMap.get(p)+1);
			}
		}
		return patternsMap;
	}


	public static double[] calculateWeightedAverages(Map<Pattern, Integer> patternsMap, int numberOfFeatures) {
		double[]  weightedAverages = new double[numberOfFeatures];
		//enter code here
		int numberOfPatterns = 0;
		for (Map.Entry<Pattern, Integer> p : patternsMap.entrySet()) {
			double[] pv = p.getKey().getPatternValues();
			numberOfPatterns += p.getValue();
			for(int i=0; i<numberOfFeatures; i++){
				weightedAverages[i] += pv[i] * p.getValue();
			}
		}
		for(int i=0; i<numberOfFeatures; i++){
			weightedAverages[i] /= numberOfPatterns;
		}
		return weightedAverages;
	}


	public static double[] calculateDispersion(Map<Pattern, Integer> patternsMap, int numberOfFeatures) {
		double[] dispersion = new double[numberOfFeatures];
		double[] weightedAverages = calculateWeightedAverages(patternsMap, numberOfFeatures); //x̃j media ponderată a feature-ului j.
		int numberOfPatterns = 0;
		for(Map.Entry<Pattern, Integer> p : patternsMap.entrySet()){
			double[] pv = p.getKey().getPatternValues();//xij valoarea feature-ului i din patternul curent.
			numberOfPatterns += p.getValue();//n – numărul total de patternuri  n = Σ fi
			for(int i=0; i<numberOfFeatures; i++){
				dispersion[i] += (pv[i] - weightedAverages[i]) * (pv[i] - weightedAverages[i])/*(xij - x̃j)² abaterea pătratică față de medie.*/ * p.getValue()/*fi de câte ori apare patternul în dataset. */;
				//dispersion[i] += ... === Σ (xij - x̃j)² fi
			}
		}
		for(int i=0; i<numberOfFeatures; i++){
			dispersion[i] /= (numberOfPatterns-1);
		}
		return dispersion;
	}

	public static double[] calculateFrequency(Map<Pattern, Integer> patternsMap, int numberOfPatterns){
		double [] frequency = new double[patternsMap.size()];
		int counter = 0;
		for(Map.Entry<Pattern, Integer> p : patternsMap.entrySet()){
			int patterns = p.getValue();
			double freq = (double) patterns /numberOfPatterns;
			frequency[counter] = freq;
			counter++;
		}
		return frequency;
	}


	public static double calculateCovariane(int feature1, int feature2, Map<Pattern, Integer> patternsMap, int numberOfFeatures, int totalPatterns) {
		double covariance = 0.0;
		double[] weightedAverages = calculateWeightedAverages(patternsMap, numberOfFeatures);
		int numberOfPatterns = 0;
		for(Map.Entry<Pattern, Integer> p : patternsMap.entrySet()){
			double[] pv = p.getKey().getPatternValues();
			numberOfPatterns = p.getValue();
			for(int i=0;i<numberOfPatterns;i++){
				covariance += (pv[feature1] - weightedAverages[feature1])*(pv[feature2] - weightedAverages[feature2]);
			}
		}
		covariance = covariance/(totalPatterns-1);

		return covariance;
	}

	public static double correlationCoefficient(int feature1, int feature2, Map<Pattern, Integer> patternMap, int numberOfFeatures, int totalPatterns){
		double covariance = calculateCovariane(feature1,feature2,patternMap,numberOfFeatures,totalPatterns);
		double[] dispersion = calculateDispersion(patternMap,numberOfFeatures);

        return covariance/Math.sqrt(dispersion[feature1] * dispersion[feature2]);
	}

	public static double[] averageSquareDeviation(Map<Pattern, Integer> patternMap, int numberOfFeatures){
		double[] squareDeviation= new double[numberOfFeatures];
		double[] dispersion =  calculateDispersion(patternMap,numberOfFeatures);
		for(int i =0;i<numberOfFeatures;i++){
			squareDeviation[i] = Math.sqrt(dispersion[i]);
		}
		return squareDeviation;
	}

	public static double[][] featuresAutoscalling(Map<Pattern, Integer> patternMap, double[][] patternSet,int numberOfFeatures, int numberOfPatterns){
		double[] averages = calculateWeightedAverages(patternMap, numberOfFeatures);
		double[] squareDeviation = averageSquareDeviation(patternMap, numberOfFeatures);
		double[][] newFeatures = new double[numberOfPatterns][numberOfFeatures];

		for(int i = 0;i<numberOfPatterns;i++){
			for(int j=0;j<numberOfFeatures;j++){
				newFeatures[i][j] = (patternSet[i][j] - averages[j])/squareDeviation[j];
			}
		}

		return newFeatures;
	}
}

