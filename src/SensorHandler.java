import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SensorHandler
{
    private Hashtable<String,Integer> sensors;
    private List<long[]> times;
    private List<double[]> values;
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
        this.sensorAmount = 0;
    }

    public void AddSensor(String sensorName, long[] sensorTimes, double[] sensorValues)
    {
        if(sensors.containsKey(sensorName)) throw new NullPointerException("invalid sensor name");

        int id = 0;
        if(sensorAmount > 0)
            id = sensorAmount - 1;

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
                valuesByTime.add(curValue);
                curTime += timeInterval;
            }
            else
            {
                i++;
            }
        }

        return ListToArray(valuesByTime);
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