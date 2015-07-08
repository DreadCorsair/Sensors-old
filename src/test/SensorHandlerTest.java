package test;

import main.SensorHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SensorHandlerTest
{
    @Test
    public void testStart() throws Exception
    {
        assert true;
    }

    @Test
    public void testAddToArray() throws Exception
    {
        SensorHandler sh = new SensorHandler();

        double[] ar = new double[] {-3.0, -2.0 , 0.0, 1.0, 2.0, 3.0};

        double[] expected = new double[] {-13.0, -12.0 , -10.0, -9.0, -8.0, -7.0};
        double[] result = sh.AddToArray(ar, -10);

        assertArrayEquals(expected, result, 0.0001);
    }

//    @Test
//    public void testPackSensorsToMatrix() throws Exception
//    {
//        SensorHandler sh = new SensorHandler();
//
//        List<double[]> sensors = new ArrayList<>();
//        sensors.add(new double[] {1.0, 2.0, 3.0});
//        sensors.add(new double[] {4.0, 5.0, 6.0});
//        sensors.add(new double[] {7.0, 8.0, 9.0});
//
//        double[][] expected = new double[][]
//                {
//                        {1.0, 2.0, 3.0},
//                        {4.0, 5.0, 6.0},
//                        {7.0, 8.0, 9.0}
//                };
//        double[][] result = sh.PackSensorsToMatrix(sensors);
//
//        assertArrayEquals(expected[0], result[0], 0.0001);
//        assertArrayEquals(expected[1], result[1], 0.0001);
//        assertArrayEquals(expected[2], result[2], 0.0001);
//    }
//
//    @Test
//    public void testGetShortestArrayLen() throws Exception
//    {
//        SensorHandler sh = new SensorHandler();
//
//        List<double[]> testList = new ArrayList<>();
//        testList.add(new double[]{1.0, 2.0, 3.0, 4.0, 5.0});
//        testList.add(new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0});
//        testList.add(new double[]{1.0, 2.0, 3.0, 4.0});
//
//        int expected = 4;
//        int result = sh.GetShortestArrayLen(testList);
//
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void testCalculateAllValuesByTime()
//    {
//        assert false;
//    }
//
//    @Test
//    public void testCalculateSensorValuesByTime() throws Exception
//    {
//        SensorHandler sh = new SensorHandler();
//
//        long[] sensorTimes = new long[] {5L, 10L, 15L, 20L, 40L, 45L};
//        double[] sensorValues = new double[] {100.0, 200.0, 300.0, 400.0, 500.0, 600.0};
//        sh.AddSensor(sensorTimes, sensorValues);
//
//        double[] expected = new double[] {200.0, 400.0, 450.0, 500.0};
//        double[] result = sh.CalculateSensorValuesByTime(0, 10L);
//
//        assertArrayEquals(expected, result, 0.0001);
//    }
//
//    @Test
//    public void testGetValueByTime()
//    {
//        SensorHandler sh = new SensorHandler();
//
//        double expected = 2.775;
//        double result = sh.GetValueByTime(4.44, 2.22, 6.66, 2.22, 3.33);
//
//        assertEquals(expected, result, 0.0001);
//    }
//
//    @Test
//    public void testListToArray()
//    {
//        SensorHandler sh = new SensorHandler();
//
//        List<Double> testList = new ArrayList<>();
//        testList.add(342.342);
//        testList.add(893.893);
//        testList.add(239.239);
//
//        double[] expected = new double[] {342.342, 893.893, 239.239};
//        double[] result = sh.ListToArray(testList);
//
//        assertArrayEquals(expected, result, 0.0001);
//    }
//
//    @Test
//    public void testScaleMatrix() throws Exception
//    {
//        SensorHandler sh = new SensorHandler();
//
//        double[][] matrix = new double[][]
//                {
//                        {1.0, 8.0, 9.0},
//                        {4.0, 5.0, 6.0},
//                        {7.0, 8.0, 3.0}
//                };
//
//        double[][] expected = new double[][]
//                {
//                        {0.0, 87.5, 100.0},
//                        {0.0, 50.0, 100.0},
//                        {80.0, 100.0, 0.0},
//                };
//        double[][] result = sh.ScaleMatrix(matrix);
//
//        assertArrayEquals(expected[0], result[0], 0.0001);
//        assertArrayEquals(expected[1], result[1], 0.0001);
//        assertArrayEquals(expected[2], result[2], 0.0001);
//    }
//
//    @Test
//    public void testGetMinValue() throws Exception
//    {
//        SensorHandler sh = new SensorHandler();
//
//        double[] testAr = new double[] {215.215, 809.809, 362.362, 746.746, 832.832, 477.477};
//
//        double expected = 215.215;
//        double result = sh.GetMinValue(testAr);
//
//        assertEquals(expected, result, 0.0001);
//    }
//
//    @Test
//    public void testGetMaxValue() throws Exception
//    {
//        SensorHandler sh = new SensorHandler();
//
//        double[] testAr = new double[] {215.215, 809.809, 362.362, 746.746, 832.832, 477.477};
//
//        double expected = 832.832;
//        double result = sh.GetMaxValue(testAr);
//
//        assertEquals(expected, result, 0.0001);
//    }
//
//    @Test
//    public void testGetAverageFromMatrix() throws Exception
//    {
//        SensorHandler sh = new SensorHandler();
//
//        double[][] matrix = new double[][]
//                {
//                        {8.73, 7.77, 1.63},
//                        {3.18, 8.67, 1.36},
//                        {5.30, 3.18, 6.79}
//                };
//
//        double[] expected = new double[] {5.73, 6.54, 3.26};
//        double[] result = sh.GetAverageFromMatrix(matrix);
//        assertArrayEquals(expected, result, 0.01);
//    }
}