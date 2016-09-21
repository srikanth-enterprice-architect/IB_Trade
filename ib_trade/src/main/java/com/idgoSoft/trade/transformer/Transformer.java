/**
 * 
 */
package com.idgoSoft.trade.transformer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import com.idgoSoft.trade.data.DataFeed;
import com.idgoSoft.trade.data.IndicatorPojo;

/**
 * @author srikanth.vaddella
 *
 */
public class Transformer {
	
	private IndicatorPojo indicatorPojo;
	ToDoubleFunction<DataFeed> averageCaluction = dataFeed -> dataFeed.getClose();

    /**
     * @param listOfStockData
     * @return
     */
    public List<DataFeed> dataFeedPreparation(ArrayList<LinkedHashMap<String, ? extends Number>> listOfStockData) {
    	indicatorPojo = new IndicatorPojo();
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
    	indicatorPojo.setDatafeedList(arrayListDataFeed);
	return arrayListDataFeed;

    }

    /**
     * @param listOfStockData
     * @return
     */
    public IndicatorPojo dataFeedCalPreparation(IndicatorPojo indicatorPojo) {	
    	indicatorPojo.setAverage(indicatorPojo.getDatafeedList().stream().collect(Collectors.averagingDouble(averageCaluction)));
    	
    	
	
    return indicatorPojo;

    }
}






























