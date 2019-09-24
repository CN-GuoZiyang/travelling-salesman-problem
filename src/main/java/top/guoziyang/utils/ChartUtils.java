package top.guoziyang.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class ChartUtils {
    public XYSeries series = new XYSeries("data");

    public JPanel getChart() {
        XYSeriesCollection dataCollection = new XYSeriesCollection();
        dataCollection.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(null, "Generation", "Least Distance", dataCollection, PlotOrientation.VERTICAL, false, true, false);

        return null;
    }

}
