import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SensorHandler
{
    private Hashtable<String,Integer> sensors;
    private List<long[]> times;
    private List<double[]> values;
    private List<double[]> dump;
    private int shortestArray;
    private int sensorAmount;

    SensorHandler()
    {
        Clear();
    }

    public void Clear()
    {
        this.sensors = new Hashtable<>();
        this.times = new ArrayList<>();
        this.values = new ArrayList<>();
        this.dump = new ArrayList<>();
        this.shortestArray = Integer.MAX_VALUE;
        this.sensorAmount = 0;
    }

    public double[][] PackSensorsToMatrix()
    {
        double[][] matrix = new double[sensorAmount][shortestArray];

        for(int s = 0; s < sensorAmount; s++)
        {
            for(int e = 0; e < shortestArray; e++)
            {
                matrix[s][e] = dump.get(s)[e];
            }
        }

        return matrix;
    }

    public void AddSensor(String sensorName, long[] sensorTimes, double[] sensorValues)
    {
        if(sensors.containsKey(sensorName)) throw new NullPointerException("invalid sensor name");

        int id = sensorAmount;

        sensors.put(sensorName, id);
        times.add(id, sensorTimes);
        values.add(id, sensorValues);

        sensorAmount++;
    }

    public void RemoveSensor(String sensorName)
    {
        if(!sensors.containsKey(sensorName)) throw new NullPointerException("invalid sensor name");

        int id = sensors.get(sensorName);
        sensors.remove(sensorName);
        times.remove(id);
        values.remove(id);

        sensorAmount--;
    }

    public double[] CalculateSensorValuesByTime(String sensorName, long timeInterval)
    {
        if(!sensors.containsKey(sensorName)) throw new NullPointerException("invalid sensor name");
        if(timeInterval == 0) throw new NullPointerException("time interval can not be 0");

        List<Double> valuesByTime = new ArrayList<>();

        int id = sensors.get(sensorName);
        long[] times = this.times.get(id);
        double[] values = this.values.get(id);

        long curTime = timeInterval;
        int i = 1;
        while(i < times.length)
        {
            if(times[i] >= curTime)
            {
                double curValue = GetValueByTime(curTime, times[i - 1], times[i], values[i - 1], values[i]);
                if(curValue < 0)
                {
                    valuesByTime.add(0.0);
                }
                else
                {
                    valuesByTime.add(curValue);
                }
                curTime += timeInterval;
            }
            else
            {
                i++;
            }
        }

        double[] valuesByTimeAr = ListToArray(valuesByTime);
        if(valuesByTimeAr.length < shortestArray)
        {
            shortestArray = valuesByTimeAr.length;
        }

        dump.add(valuesByTimeAr);
        return valuesByTimeAr;
    }

    private double GetValueByTime(double time, double timeMin, double timeMax, double valueMin, double valueMax)
    {
        return (time - timeMin) / (timeMax - timeMin) * (valueMax - valueMin) + valueMin;
    }

    private double[] ListToArray(List<Double> list)
    {
        double[] array = new double[list.size()];
        for(int i = 0; i < array.length; i++)
        {
            array[i] = list.get(i);
        }

        return array;
    }
}