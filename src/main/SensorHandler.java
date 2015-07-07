package main;

import java.util.*;

public class SensorHandler
{
    private Hashtable<String,Integer> sensors;
    private List<long[]> times;
    private List<double[]> values;
    private List<double[]> dump;
    private int sensorAmount;

    public SensorHandler()
    {
        Clear();
    }

    public void Clear()
    {
        this.sensors = new Hashtable<>();
        this.times = new ArrayList<>();
        this.values = new ArrayList<>();
        this.dump = new ArrayList<>();
        this.sensorAmount = 0;
    }

    public double[] GetAverageFromMatrix(double[][] matrix)
    {
        int rows = matrix.length;
        int columns = matrix[0].length;

        double[] average = new double[columns];

        for(int e = 0; e < columns; e++)
        {
            double total = 0.0;
            for(int s = 0; s < rows; s++)
            {
                total += matrix[s][e];
            }
            average[e] = total / rows;
        }

        return average;
    }

    public double[][] ScaleMatrix(double[][] matrix)
    {
        int rows = matrix.length;
        int columns = matrix[0].length;

        double[][] scaleMatrix = new double[rows][columns];

        for(int s = 0; s < rows; s++)
        {
            double minValue = GetMinValue(matrix[s]);
            double maxValue = GetMaxValue(matrix[s]);

            double alpha = 100.0 / (maxValue - minValue);
            double beta = alpha * minValue;

            for(int e = 0; e < columns; e++)
            {
                scaleMatrix[s][e] = alpha * matrix[s][e] - beta;
            }
        }

        return scaleMatrix;
    }

    public double[][] PackSensorsToMatrix(List<double[]> dump)
    {
        int shortestArrayLen = GetShortestArrayLen(dump);
        int rows = dump.size();
        int columns = shortestArrayLen;

        double[][] matrix = new double[sensorAmount][shortestArrayLen];

        for(int s = 0; s < sensorAmount; s++)
        {
            for(int e = 0; e < shortestArrayLen; e++)
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

    public int GetShortestArrayLen(List<double[]> dump)
    {
        int shortestArrayLen = Integer.MAX_VALUE;
        for(int d = 0; d < dump.size(); d++)
        {
            if(dump.get(d).length < shortestArrayLen)
            {
                shortestArrayLen = dump.get(d).length;
            }
        }

        return shortestArrayLen;
    }
}