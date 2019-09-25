package top.guoziyang.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class ChartUtils {
    public XYSeries series = new XYSeries("data");
    private int i = 0;
    private int maxX = -1;

    public JPanel getChart(int maxX, int maxY) {
        this.maxX = maxX;
        XYSeriesCollection dataCollection = new XYSeriesCollection();
        dataCollection.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(null, "Generation", "Least Distance", dataCollection, PlotOrientation.VERTICAL, false, true, false);
        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);

        chart.setBorderPaint(new Color(0,204,205));
        chart.setBorderVisible(true);

        XYPlot xyplot = (XYPlot) chart.getPlot();

        // Y轴
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setLowerBound(0);
        numberaxis.setUpperBound(maxY);
        numberaxis.setAutoTickUnitSelection(true);
        // 只显示整数值
        // numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // numberaxis.setAutoRangeIncludesZero(true);
        numberaxis.setLowerMargin(0); // 数据轴下（左）边距 ­
        numberaxis.setMinorTickMarksVisible(false);// 标记线是否显示
        numberaxis.setTickMarkInsideLength(0);// 外刻度线向内长度
        numberaxis.setTickMarkOutsideLength(0);

        // X轴的设计
        NumberAxis x = (NumberAxis) xyplot.getDomainAxis();
        x.setAutoRange(true);// 自动设置数据轴数据范围
        // 自己设置横坐标的值
        x.setAutoTickUnitSelection(true);
        // 设置最大的显示值和最小的显示值
        x.setLowerBound(0);
        x.setUpperBound(maxX);
        // 数据轴的数据标签：只显示整数标签
        x.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        x.setAxisLineVisible(true);// X轴竖线是否显示
        x.setTickMarksVisible(false);// 标记线是否显示

        RectangleInsets offset = new RectangleInsets(0, 0, 0, 0);
        xyplot.setAxisOffset(offset);// 坐标轴到数据区的间距
        xyplot.setBackgroundAlpha(0.0f);// 去掉柱状图的背景色
        xyplot.setOutlinePaint(null);// 去掉边框

        return new ChartPanel(chart, true);
    }

    public void addData(int data) {
        if(i == maxX) {
            i = 0;
            series.delete(0, maxX - 1);
        }
        series.add(i, data);
        i ++;
    }

}
