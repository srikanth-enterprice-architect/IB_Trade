/**
 * 
 */
package com.idgoSoft.trade.data;

import java.util.HashMap;
import java.util.List;


/**
 * @author srikanth.vaddella
 *
 */
public class IndicatorPojo {
	
	private List<DataFeed> datafeedList;
	private List<DataFeed> resultedDataFeed;
	private double average;
	private double average2;
	private List<HashMap<String,String>> objectTomap;
	
	

	public List<HashMap<String, String>> getObjectTomap() {
		return objectTomap;
	}
	public void setObjectTomap(List<HashMap<String, String>> objectTomap) {
		this.objectTomap = objectTomap;
	}
	/**
	 * @return the average2
	 */
	public double getAverage2() {
		return average2;
	}
	/**
	 * @param average2 the average2 to set
	 */
	public void setAverage2(double average2) {
		this.average2 = average2;
	}
	/**
	 * @return the resultedDataFeed
	 */
	public List<DataFeed> getResultedDataFeed() {
		return resultedDataFeed;
	}
	/**
	 * @param resultedDataFeed the resultedDataFeed to set
	 */
	public void setResultedDataFeed(List<DataFeed> resultedDataFeed) {
		this.resultedDataFeed = resultedDataFeed;
	}
	/**
	 * @return the datafeedList
	 */
	public List<DataFeed> getDatafeedList() {
		return datafeedList;
	}
	/**
	 * @param datafeedList the datafeedList to set
	 */
	public void setDatafeedList(List<DataFeed> datafeedList) {
		this.datafeedList = datafeedList;
	}
	/**
	 * @return the average
	 */
	public double getAverage() {
		return average;
	}
	/**
	 * @param average the average to set
	 */
	public void setAverage(double average) {
		this.average = average;
	}
	
	
	

}
