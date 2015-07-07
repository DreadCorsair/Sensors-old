package test;

import main.SensorHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SensorHandlerTest {

    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testClear() throws Exception {

    }

    @Test
    public void testGetAverageFromMatrix() throws Exception
    {
        SensorHandler sh = new SensorHandler();

        double[][] matrix = new double[][]
                {
                        {8.73, 7.77, 1.63},
                        {3.18, 8.67, 1.36},
                        {5.30, 3.18, 6.79}
                };

        double[] expected = new double[] {5.73, 6.54, 3.26};
        double[] result = sh.GetAverageFromMatrix(matrix);

        assertArrayEquals("", expected, result, 0.01);
    }

    @Test
    public void testScaleMatrix() throws Exception {

    }

    @Test
    public void testPackSensorsToMatrix() throws Exception {

    }

    @Test
    public void testAddSensor() throws Exception {

    }

    @Test
    public void testRemoveSensor() throws Exception {

    }

    @Test
    public void testCalculateSensorValuesByTime() throws Exception {

    }
}