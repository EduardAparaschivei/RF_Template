package ro.usv.rf.classifiers;

import ro.usv.rf.utils.DataUtils;
import ro.usv.rf.utils.DistanceUtils;
import ro.usv.rf.utils.IDistance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Classifier_1NN extends AbstractClassifier {

	IDistance d;
	private boolean debug = true;
	private double[] classWeights;

	public Classifier_1NN(IDistance d) {
		super();
		this.d = d;
	}

	public Classifier_1NN() {
		this(DistanceUtils::distEuclid);
	}

	@Override
	public void training() {
		if(M==0)
			throw new RuntimeException("train(): No supervised learning set provided (M=0)");
		// all rest were done in super.train(X,F,iClass)
		classWeights = calculateClassWeights();
		if(debug){
			for(int i=1;i<=M;i++){
				System.out.println("weight of "+classNames[i] + " is "+classWeights[i]);
			}
		}
	}

	@Override
	public int predict(double[] z) {
		int closestIndex = -1;
		double highestWeight = 0;
		double minDistance = Double.MAX_VALUE;
		double[] distances = new double[X.length];
		double[] indexes = new double[X.length];
		int uniqueCount = 0;

		for(int i=0;i<X.length;i++){
			double distance = d.distance(z,X[i]);
			boolean distanceFound = false;
			int pos = 0;
			//decide where to insert
			while (pos < uniqueCount && distances[pos] < distance) {
				pos++;
			}

			//same distance at same pos
			if (pos < uniqueCount && distances[pos] == distance) {
				distanceFound = true;
				int existingIndex = (int) indexes[pos];


				if (f[i] > f[existingIndex]) {
					indexes[pos] = i;
				}
			}

			//if new distance then insert
			if (!distanceFound) {
				for (int j = uniqueCount; j > pos; j--) {
					distances[j] = distances[j - 1];
					indexes[j] = indexes[j - 1];
				}
				distances[pos] = distance;
				indexes[pos] = i; // salvăm indexul original al pattern-ului
				uniqueCount++;
			}
		}

		if(debug){
			double[][] orderedDistances = new double[2][uniqueCount];
			for (int i=0;i< uniqueCount;i++){
				orderedDistances[0][i] = indexes[i];
				orderedDistances[1][i] = distances[i];
			}
			DataUtils.sortDistancesAndIndexes(orderedDistances);
			DataUtils.printDistanceMatrix(orderedDistances,X,z);

		}

		for (int i = 0; i < uniqueCount; i++) {
			int origIndex = (int) indexes[i];

			if (distances[i] < minDistance) {
				minDistance = distances[i];
				highestWeight = f[origIndex];
				closestIndex = i;
			} else if (distances[i] == minDistance) {
				int currentWinnerIndex = (int) indexes[closestIndex];

				if (iClass[origIndex] != iClass[currentWinnerIndex]) {
					if (f[origIndex] > highestWeight) {
						highestWeight = f[origIndex];
						closestIndex = i;
					} else if (f[origIndex] == highestWeight) {
						double currentClassWeight = classWeights[iClass[currentWinnerIndex]];
						double candidateClassWeight = classWeights[iClass[origIndex]];

						if (candidateClassWeight > currentClassWeight) {
							closestIndex = i;
						}
					}
				}
			}
		}
		int indexCorect = (int) indexes[closestIndex];
		return iClass[indexCorect];
	}

	
    static public void classifyAndDisplayResult(AbstractClassifier classifier, String[] classNames, double[][] testSet) {
    	System.out.println("\nPatterns class:"+ Arrays.deepToString(testSet) + ":");
    	Arrays.stream(classifier.predict(testSet))
    	.mapToObj(k-> (classNames==null ? k :classNames[k]) +" ")
        .forEach(System.out::print);
    }

    public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public double[] calculateClassWeights(){
		double[] classWeights = new double[M + 1];
		for(int i=0;i<X.length;i++){
			int clasa = iClass[i];
			double weight = f[i];
			classWeights[clasa] = classWeights[clasa] + weight;
		}
		return classWeights;
	}
}
