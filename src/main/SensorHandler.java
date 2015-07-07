package main;

import java.util.*;

public class SensorHandler
{
    private List<long[]> times;
    private List<double[]> values;

    public SensorHandler()
    {
        this.times = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public void AddSensor(long[] sensorTimes, double[] sensorValues)
    {
        times.add(sensorTimes);
        values.add(sensorValues);
    }

    public void Start(long timeInterval)
    {
        double[][] matrix = PackSensorsToMatrix(CalculateAllValuesByTime(timeInterval));
        double[][] scaleMatrix = ScaleMatrix(matrix);
        GetAverageFromMatrix(scaleMatrix);

        Clear();
    }

    private double[][] PackSensorsToMatrix(List<double[]> dump)
    {
        int rows = dump.size();
        int columns = GetShortestArrayLen(dump);

        double[][] matrix = new double[rows][columns];

        for(int s = 0; s < rows; s++)
        {
            for(int e = 0; e < columns; e++)
            {
                matrix[s][e] = dump.get(s)[e];
            }
        }

        return matrix;
    }

    private int GetShortestArrayLen(List<double[]> dump)
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

    private List<double[]> CalculateAllValuesByTime(long timeInterval)
    {
        List<double[]> dump = new ArrayList<>();
        int sensorAmount = values.size();
        for(int s = 0; s < sensorAmount; s++)
        {
            dump.add(CalculateSensorValuesByTime(s, timeInterval));
        }

        return dump;
    }

    private double[] CalculateSensorValuesByTime(int id, long timeInterval)
    {
        List<Double> valuesByTime = new ArrayList<>();

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

    private double[][] ScaleMatrix(double[][] matrix)
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

    private double[] GetAverageFromMatrix(double[][] matrix)
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

    private void Clear()
    {
        this.times = new ArrayList<>();
        this.values = new ArrayList<>();
    }
}