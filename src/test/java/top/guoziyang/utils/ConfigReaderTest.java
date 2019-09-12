package top.guoziyang.utils;

import org.junit.Assert;
import org.junit.Test;
import top.guoziyang.problems.impl.ExplicitTsp;
import top.guoziyang.problems.impl.Simple2dTsp;

public class ConfigReaderTest {

    @Test
    public void readSimple2dTest() {
        Simple2dTsp problem = (Simple2dTsp) ConfigReader.readConfig(this.getClass().getClassLoader().getResource("ALL_tsp/a280.tsp").getPath());
        Assert.assertEquals("a280", problem.getName());
        Assert.assertEquals(280, problem.getDimension());
        Assert.assertEquals("EUC_2D", problem.getEdgeWeightType().getType());
        Assert.assertEquals(280, problem.getNodeCoordSection().size());
        problem = (Simple2dTsp) ConfigReader.readConfig(this.getClass().getClassLoader().getResource("ALL_tsp/att532.tsp").getPath());
        Assert.assertEquals("att532", problem.getName());
        Assert.assertEquals(532, problem.getDimension());
        Assert.assertEquals("ATT", problem.getEdgeWeightType().getType());
        Assert.assertEquals(532, problem.getNodeCoordSection().size());
        problem = (Simple2dTsp) ConfigReader.readConfig(this.getClass().getClassLoader().getResource("ALL_tsp/fnl4461.tsp").getPath());
        Assert.assertEquals("fnl4461", problem.getName());
        Assert.assertEquals(4461, problem.getDimension());
        Assert.assertEquals("EUC_2D", problem.getEdgeWeightType().getType());
        Assert.assertEquals(4461, problem.getNodeCoordSection().size());
        problem = (Simple2dTsp) ConfigReader.readConfig(this.getClass().getClassLoader().getResource("ALL_tsp/burma14.tsp").getPath());
        Assert.assertEquals("burma14", problem.getName());
        Assert.assertEquals(14, problem.getDimension());
        Assert.assertEquals("GEO", problem.getEdgeWeightType().getType());
        Assert.assertEquals(14, problem.getNodeCoordSection().size());
    }

    @Test
    public void readExplicitTspTest() {
        ExplicitTsp problem = (ExplicitTsp) ConfigReader.readConfig(this.getClass().getClassLoader().getResource("ALL_tsp/brazil58.tsp").getPath());
        Assert.assertEquals("brazil58", problem.getName());
        Assert.assertEquals(58, problem.getDimension());
        Assert.assertEquals("EXPLICIT", problem.getEdgeWeightType().getType());
        Assert.assertEquals("UPPER_ROW", problem.getEdgeWeightFormat().getFormat());
        Assert.assertEquals(58, problem.getEdgeWeightSection().length);
    }

    @Test
    public void readExplicitDataTspTest() {

    }

}
