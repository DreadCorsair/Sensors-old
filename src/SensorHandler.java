import java.util.*;

public class SensorHandler
{
    private Hashtable<String,Integer> sensors;
    private List<long[]> times;
    private List<double[]> values;
    private List<double[]> dump;
    private double[][] matrix;
    private int shortestArrayLen;
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
        this.shortestArrayLen = Integer.MAX_VALUE;
        this.sensorAmount = 0;
    }

    public double[] GetAverageFromMatrix(double[][] matrix)
    {
        double[] average = new double[shortestArrayLen];

        for(int e = 0; e < shortestArrayLen; e++)
        {
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            for(int s = 0; s < sensorAmount; s++)
            {
                if(matrix[s][e] < min)
                {
                    min = matrix[s][e];
                }
                else if(matrix[s][e] > max)
                {
                    max = matrix[s][e];
                }
            }
            average[e] = (min + max) / sensorAmount;
        }

        return average;
    }

    public double[][] ScaleMatrix()
    {
        double[][] scaleMatrix = new double[sensorAmount][shortestArrayLen];

        for(int s = 0; s < sensorAmount; s++)
        {
            double minValue = GetMinValue(matrix[s]);
            double maxValue = GetMaxValue(matrix[s]);

            double alpha = 100.0 / (maxValue - minValue);
            double beta = alpha * minValue;

            for(int e = 0; e < shortestArrayLen; e++)
            {
                scaleMatrix[s][e] = alpha * matrix[s][e] - beta;
            }
        }

        return scaleMatrix;
    }

    public double[][] PackSensorsToMatrix()
    {
        double[][] matrix = new double[sensorAmount][shortestArrayLen];

        for(int s = 0; s < sensorAmount; s++)
        {
            for(int e = 0; e < shortestArrayLen; e++)
            {
                matrix[s][e] = dump.get(s)[e];
            }
        }

        this.matrix = matrix;
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
        if(valuesByTimeAr.length < shortestArrayLen)
        {
            shortestArrayLen = valuesByTimeAr.length;
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

    private double GetMinValue(double[] ar)
    {
        double min = Double.MAX_VALUE;
        for(int i = 0; i < ar.length; i++)
        {
            if(ar[i] < min)
            {
                min = ar[i];
            }
        }

        return min;
    }

    private double GetMaxValue(double[] ar)
    {
        double max = Double.MIN_VALUE;
        for(int i = 0; i < ar.length; i++)
        {
            if(ar[i] > max)
            {
                max = ar[i];
            }
        }

        return max;
    }
}