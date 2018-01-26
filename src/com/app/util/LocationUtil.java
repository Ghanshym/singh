package com.app.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.model.Location;
import com.app.service.ILocationService;

@Component
public class LocationUtil {
	@Autowired
	private ILocationService service;

	public List<Location> getAllLocs() {
		List<Location> locList = service.getAllLocations();
		return locList;
	}

	/**
	 * generate Pie Chart
	 * 
	 * @throws IOException
	 */
	public void generatePieChart(String path, List<Object[]> data) {
		// dataset
System.out.println("path  "+path);
		DefaultPieDataset dataset = new DefaultPieDataset();
		System.out.println("List<Object[] " + data);
		for (Object ob[] : data) {
			// dataset.setValue(key, value);
			System.out.println("ob[] " + ob[0] + ":  :" + ob[1]);
			dataset.setValue(ob[0].toString(), new Double(ob[1].toString()));
		}
		// JFree obj
		JFreeChart chart = ChartFactory.createPieChart3D("Locationwise Report",
				dataset, true, true, false);
		// save as image
		try {
			System.out.println("path::::::::" + path);
			ChartUtilities.saveChartAsJPEG(new File(path + "/LocAReport.jpg"),
					chart, 400, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * generate Bar chart
	 */
	public void generateBarChart(String path, List<Object[]> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Object ob[] : data) {
			dataset.setValue(new Double(ob[1].toString()), ob[0].toString(), "");
		}
		JFreeChart chart = ChartFactory.createBarChart3D("Locationwise Report",
				"Location Types", "Count", dataset);
		System.out.println(chart);
		try {
			System.out.println("path::::::::" + path);
			ChartUtilities.saveChartAsJPEG(new File(path + "/LocBReport.jpg"),
					chart, 400, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
