import java.util.Random;

public class Main {
	static double minimum = Double.MAX_VALUE;
	static double[] minArray = new double[200];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] array_200 = new double[200][100];
		double[][] array_20 = new double[200][100];

		Thread[] threadArray = new Thread[200];
		Random rdm = new Random();
		long start_time;
		long end_time;

		// initialize for both 200 and 20 arrays
		for (int x = 0; x < 200; x++) {
			for (int y = 0; y < 100; y++) {
				double initialMin = -5.12;
				double initialMax = 5.12;

				double value = initialMin
						+ (rdm.nextDouble() * (initialMax - initialMin));

				array_200[x][y] = value;
				array_20[x][y] = value;
			}
		}

		// 200 threads
		// start time
		start_time = System.currentTimeMillis();

		for (int x = 0; x < 200; x++) {
			Task1 task1 = new Task1(array_200[x], x);

			threadArray[x] = new Thread(task1);
			threadArray[x].start();
		}

		for (int x = 0; x < 200; x++) {
			try {
				threadArray[x].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		findMinimum(minArray);

		// end time
		end_time = System.currentTimeMillis();

		System.out.printf(
				"Total time for 200 threads: %d\nThe minimum value: %.2f\n\n",
				end_time - start_time, minimum);

		// 20 threads
		// start time
		start_time = System.currentTimeMillis();

		for (int x = 0; x < 20; x++) {
			Task1 task2 = new Task1(array_20[x], x);

			threadArray[x] = new Thread(task2);
			threadArray[x].start();
		}

		for (int x = 0; x < 200; x++) {
			try {
				threadArray[x].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		findMinimum(minArray);

		// end time
		end_time = System.currentTimeMillis();

		System.out.printf(
				"Total time for 20 threads: %d\nThe minimum value: %.2f",
				end_time - start_time, minimum);
	}

	public static void findMinimum(double[] minArray) {
		for (int x = 0; x < 200; x++) {
			if (minArray[x] < minimum)
				minimum = minArray[x];
		}
	}
}
