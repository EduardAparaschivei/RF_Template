package ro.usv.rf.labs;

import ro.usv.rf.Pattern;
import ro.usv.rf.utils.FileUtils;
import ro.usv.rf.utils.StatisticsUtils;

import java.util.Arrays;
import java.util.Map;

public class MainClass {

	public static void main(String[] args) {
		double[][] patternSet = FileUtils.readLearningSetFromFile("in.txt");
		int numberOfPatterns = patternSet.length;
		int numberOfFeatures = patternSet[0].length;
		
		for (int j=0; j<numberOfFeatures; j++)
		{
			double[] feature = new double[numberOfPatterns];
			for (int i=0; i<numberOfPatterns; i++)
			{
				feature[i] = patternSet[i][j];
			}
			System.out.println("Feature average is:" + StatisticsUtils.calculateFeatureAverage(feature));
		}
		
		Map<Pattern, Integer> patternsMap = StatisticsUtils.getPatternsMapFromInitialSet(patternSet);
		
		double[] weightedAverages = StatisticsUtils.calculateWeightedAverages(patternsMap, numberOfFeatures);
		double[] dispersion = StatisticsUtils.calculateDispersion(patternsMap, numberOfFeatures);
		double[] frequency = StatisticsUtils.calculateFrequency(patternsMap, numberOfPatterns);
		double covariance = StatisticsUtils.calculateCovariane(0,0,patternsMap,numberOfFeatures,numberOfPatterns);
		double correlation = StatisticsUtils.correlationCoefficient(0,1,patternsMap,numberOfFeatures,numberOfPatterns);
		double[] squareDeviation = StatisticsUtils.averageSquareDeviation(patternsMap, numberOfFeatures);
		double[][] newFeatures = StatisticsUtils.featuresAutoscalling(patternsMap,patternSet,numberOfFeatures,numberOfPatterns);

		for(double[] i : newFeatures){
			System.out.println(Arrays.toString(i));
		}

				
	}
}
