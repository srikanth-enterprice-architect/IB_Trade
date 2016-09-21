/**
 * 
 */
package com.idgoSoft.trade.transformer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.idgoSoft.trade.data.DataFeed;

/**
 * @author srikanth.vaddella
 *
 */
public class Transformer {

    /**
     * @param listOfStockData
     * @return
     */
    public List<DataFeed> dataFeedPreparation(ArrayList<LinkedHashMap<String, ? extends Number>> listOfStockData) {
	ArrayList<DataFeed> arrayListDataFeed = new ArrayList<DataFeed>();
	listOfStockData.stream().forEach(mappedData -> {
	    final DataFeed dataFeed = new DataFeed();
	    dataFeed.setClose((Double) mappedData.get("close"));
	    dataFeed.setHigh((Double) mappedData.get("high"));
	    dataFeed.setLow((Double) mappedData.get("low"));
	    dataFeed.setOpen((Double) mappedData.get("open"));
	    dataFeed.setVolume((int) mappedData.get("volume"));
	    dataFeed.setTimestamp((int) mappedData.get("Timestamp"));
	    arrayListDataFeed.add(dataFeed);
	});
	return arrayListDataFeed;

    }

    /**
     * @param listOfStockData
     * @return
     */
    public List<DataFeed> dataFeedCalPreparation(List<DataFeed> mappedData) {
	ArrayList<DataFeed> arrayListDataFeed = new ArrayList<DataFeed>();

	return arrayListDataFeed;

    }
}
