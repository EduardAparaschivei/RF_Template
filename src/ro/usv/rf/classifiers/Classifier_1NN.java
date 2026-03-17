package ro.usv.rf.classifiers;

import ro.usv.rf.utils.DistanceUtils;

import java.util.Arrays;
import java.util.Random;

public class Classifier_1NN extends AbstractClassifier {
	

	private boolean debug = true;
	
	@Override
	public void training() {
		if(M==0)
			throw new RuntimeException("train(): No supervised learning set provided (M=0)");
		// all rest were done in super.train(X,F,iClass)
	}

	//TODO: implement predict method
	@Override
	public int predict(double[] z) {
		//your code here
		int closestIndex = -1;
		double minDistance = Double.MAX_VALUE;
		double[] distances = new double[X.length];
		for(int i=0;i<X.length;i++){
			double distance = DistanceUtils.distEuclid(z,X[i]);
			if(distance < minDistance){
				closestIndex = i;
				minDistance = distance;
			}
		}

		return iClass[closestIndex];
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


}
