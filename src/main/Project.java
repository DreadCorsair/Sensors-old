package main;

public class Project
{
    public static void main(String [] args)
    {
        long[] sensor1Times = new long[] {4, 5, 6, 10, 11, 12, 100, 101, 200, 300, 301};
        double[] sensor1Values = new double[] {5, 8, 9, 10, 12, 13, 15, 14, 48, 50, 70};

        long[] sensor2Times = new long[] {7, 9, 13, 24, 32, 40, 45, 56, 100, 200};
        double[] sensor2Values = new double[] {14, 18, 19, 132, 153, 121, 145, 164, 177, 1100};

        //создаем экземпл€р класса обработчика
        SensorHandler sh = new SensorHandler();

        //добавл€ем в обработчик датчик под именем "sensor1" и передаем его данные
        sh.AddSensor("sensor1", sensor1Times, sensor1Values);
        //тоже самое дл€ "sensor2"
        sh.AddSensor("sensor2", sensor2Times, sensor2Values);

        //считаем значени€ дл€ "sensor1" на каждые 4мс
        sh.CalculateSensorValuesByTime("sensor1", 4);
        sh.CalculateSensorValuesByTime("sensor2", 4);
//        double[][] matrix = sh.PackSensorsToMatrix();
//        double[][] scaleMatrix = sh.ScaleMatrix();
        //double[] average = sh.GetAverageFromMatrix(scaleMatrix);

        System.out.println();

        //PrintValues("sensor1", 4, result);
    }

    private static void PrintValues(String sensorName, long timeInterval, double[] values)
    {
        long curTime = timeInterval;
        for(int i = 0; i < values.length; i++)
        {
            System.out.printf("%s - time %d: %.2f", sensorName, curTime, values[i]);
            System.out.println();
            curTime += timeInterval;
        }
    }
}