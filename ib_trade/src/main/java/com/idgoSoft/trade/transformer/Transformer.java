/**
 * 
 */
package com.idgoSoft.trade.transformer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
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
    	
    	Supplier<ArrayList<DataFeed>> supplier = () -> new ArrayList<>();
    	BiConsumer<ArrayList<DataFeed>, DataFeed>  accumulator = (list, name)  ->  list.add(name);
		BiConsumer<ArrayList<DataFeed>, ArrayList<DataFeed>>  combiner = (list, name)  ->  list.addAll(name);
		
		List<DataFeed> listOfDatafeed = indicatorPojo.getDatafeedList().subList(0, 10).stream().collect(supplier, accumulator, combiner);
	
    	return indicatorPojo;

    }
}






























