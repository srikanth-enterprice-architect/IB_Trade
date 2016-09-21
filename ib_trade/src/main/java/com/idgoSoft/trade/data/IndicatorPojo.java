/**
 * 
 */
package com.idgoSoft.trade.data;

import java.util.List;


/**
 * @author srikanth.vaddella
 *
 */
public class IndicatorPojo {
	
	private List<DataFeed> datafeedList;
	private List<DataFeed> resultedDataFeed;
	private double average;
	
	
	
	
	
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
