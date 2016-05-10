import java.util.Random;
import java.util.concurrent.Callable;

public class Task1 implements Callable<Double> {
	double[] array;
	double[] tempArray;
	Random rdm = new Random();
	double minimumValue = Double.MAX_VALUE;
	int length;

	public Task1(double[] array, int length) {
		this.array = array;
		this.length = length;
		this.tempArray = array.clone();
	}

	@Override
	public Double call() throws Exception {
		// TODO Auto-generated method stub
		double result = 0;

		// TODO Auto-generated method stub
		for (int x = 0; x < 350; x++) {
			result = 0;
			for (int y = 0; y < 100; y++) {
				result += (array[y] * array[y]) * y;
			}

			if (result < minimumValue) {
				minimumValue = result;

				passArray(tempArray, array);
			} else {
				passArray(array, tempArray);
			}

			for (int b = 0; b < 100; b++) {
				double initialMin = -0.5;
				double initialMax = 0.5;
				double value = initialMin + rdm.nextDouble()
						* (initialMax - initialMin);

				if (array[b] + value > -5.12 && array[b] + value < 5.12)
					array[b] += value;
			}
		}
		Main.minArray[length] = minimumValue;

		return minimumValue;
	}

	public void passArray(double[] firstArray, double[] secondArray) {
		for (int z = 0; z < 100; z++)
			firstArray[z] = secondArray[z];
	}
}
