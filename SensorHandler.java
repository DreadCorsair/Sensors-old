import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SensorHandler
{
    private Hashtable<String,Integer> _sensors;
    private List<long[]> _times;
    private List<double[]> _values;
    private int _sensorAmount;

    SensorHandler()
    {
        Clear();
    }

    public void Clear()
    {
        this._sensors = new Hashtable<>();
        this._times = new ArrayList<>();
        this._values = new ArrayList<>();
        this._sensorAmount = 0;
    }

    public int AddSensor(String sensorName, long[] sensorTimes, double[] sensorValues)
    {
        if(_sensors.containsKey(sensorName)) throw new NullPointerException("invalid sensor name");

        int id = 0;
        if(_sensorAmount > 0)
            id = _sensorAmount - 1;

        _sensors.put(sensorName, id);
        _times.add(id, sensorTimes);
        _values.add(id, sensorValues);

        _sensorAmount++;

        return id;
    }

    public void RemoveSensor(String sensorName)
    {
        if(!_sensors.containsKey(sensorName)) throw new NullPointerException("invalid sensor name");

        int id = _sensors.get(sensorName);
        _sensors.remove(sensorName);
        _times.remove(id);
        _values.remove(id);
        _sensorAmount--;
    }

    public double[] CalculateSensorValuesByTime(String sensorName, long timeInterval)
    {
        if(!_sensors.containsKey(sensorName)) throw new NullPointerException("invalid sensor name");
        if(timeInterval == 0) throw new NullPointerException("time interval can not be 0");

        List<Double> valuesByTime = new ArrayList<>();

        int id = _sensors.get(sensorName);
        long[] times = _times.get(id);
        double[] values = _values.get(id);

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