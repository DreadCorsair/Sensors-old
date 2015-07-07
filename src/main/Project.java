package main;

public class Project
{
    public static void main(String [] args)
    {
        long[] x1 = new long[] {4, 5, 6, 10, 11, 12, 100, 101, 200, 300, 301};
        double[] y1 = new double[] {5, 8, 9, 10, 12, 13, 15, 14, 48, 50, 70};

        long[] x2 = new long[] {7, 9, 13, 24, 32, 40, 45, 56, 100, 200};
        double[] y2 = new double[] {14, 18, 19, 132, 153, 121, 145, 164, 177, 1100};

        SensorHandler sh = new SensorHandler();

        sh.AddSensor(x1, y1);
        sh.AddSensor(x2, y2);

        sh.Start(10);
    }
}