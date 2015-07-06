public class Project
{
    public static void main(String [] args)
    {
        long[] sensorTimes = new long[] {4, 5, 6, 10, 11, 12, 100, 101, 200, 300, 301};
        double[] sensorValues = new double[] {5, 8, 9, 10, 12, 13, 15, 14, 48, 50, 70};

        //создаем экземпл€р класса обработчика
        SensorHandler sp = new SensorHandler();

        //добавл€ем в обработчик датчик под именем "sensor1" и передаем его данные
        sp.AddSensor("sensor1", sensorTimes, sensorValues);
        //тоже самое дл€ "sensor2"
        sp.AddSensor("sensor2", sensorTimes, sensorValues);
        //удал€ем "sensor2"
        sp.RemoveSensor("sensor2");

        //считаем значени€ дл€ "sensor1" на каждые 4мс
        double[] result = sp.CalculateSensorValuesByTime("sensor1", 4);

        PrintValues("sensor1", 4, result);
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