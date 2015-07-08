package main;

public class Project
{
    public static void main(String [] args)
    {
        long[] x1 = new long[] {4, 5, 6, 10, 11, 12, 100, 101, 200, 300, 301};
        double[] y1 = new double[] {5, 8, 9, 10, 12, 13, 15, 14, 48, 50, 70};

        long[] x2 = new long[] {7, 9, 13, 24, 32, 40, 45, 56, 100, 200};
        double[] y2 = new double[] {1114, 1118, 1119, 1132, 1153, 1121, 1145, 1164, 1177, 11100};

        SensorHandler sh = new SensorHandler();

        sh.AddSensor(x1, y1);
        sh.AddSensor(x2, y2);

        int[] result = sh.GetReferenceVector(10);

        System.out.println();
    }
}