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
	private double average;
	
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
