/**
 * 
 */
package com.idgoSoft.trade.transformer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	public IndicatorPojo dataFeedPreparation(ArrayList<LinkedHashMap<String, ? extends Number>> listOfStockData) {
		indicatorPojo = new IndicatorPojo();
		indicatorPojo.setDatafeedList(listOfStockData
				.parallelStream()
				.collect(ArrayList::new, convertDataFeedMaptoListofDatafeed(), ArrayList::addAll));
		dataFeedCalPreparation(indicatorPojo);
		return indicatorPojo;

	}

	/**
	 * @return
	 */
	public BiConsumer<ArrayList<DataFeed>, LinkedHashMap<String, ? extends Number>> convertDataFeedMaptoListofDatafeed() {
		BiConsumer<ArrayList<DataFeed>, LinkedHashMap<String, ? extends Number>> accumulator = (dataFeedList , dataFeedData) -> { DataFeed dataFeed = new DataFeed();
			dataFeed.setClose((Double) dataFeedData.get("close"));
			dataFeed.setHigh((Double) dataFeedData.get("high"));
			dataFeed.setLow((Double) dataFeedData.get("low"));
			dataFeed.setOpen((Double) dataFeedData.get("open"));
			dataFeed.setVolume((int) dataFeedData.get("volume"));
			dataFeed.setTimestamp((int) dataFeedData.get("Timestamp"));
			dataFeedList.add(dataFeed);
		};
		return accumulator;
	}

	/**
	 * @param listOfStockData
	 * @return
	 */
	public IndicatorPojo dataFeedCalPreparation(IndicatorPojo indicatorPojo) {

		Stream<DataFeed> stream = indicatorPojo.getDatafeedList().stream();
		indicatorPojo.setAverage(stream.limit(12).collect(Collectors.averagingDouble(averageCaluction)));
		indicatorPojo.setAverage2(stream.limit(26).collect(Collectors.averagingDouble(averageCaluction)));
		List<DataFeed> listOfDatafeed = stream.skip(11).collect(ArrayList<DataFeed>::new, caluclateIndicators(indicatorPojo), ArrayList<DataFeed>::addAll);
		indicatorPojo.setResultedDataFeed(listOfDatafeed);
		return indicatorPojo;

	}

	/**
	 * @param indicatorPojo
	 * @return
	 */
	public BiConsumer<ArrayList<DataFeed>, DataFeed> caluclateIndicators(IndicatorPojo indicatorPojo) {
		return (list, name) -> caluclate(indicatorPojo, list, name);
	}

	/**
	 * @param indicatorPojo
	 * @param list
	 * @param name
	 */
	public void caluclate(IndicatorPojo indicatorPojo, ArrayList<DataFeed> list, DataFeed name) {
		int size = list.size();
		if (size == 0) {
			name.setEma_1(indicatorPojo.getAverage());
		} else {
			double ema_1 = (name.getClose() * (2 / 12 + 1))
					+ (list.get(size - 1).getEma_1() * (1 - (2 / 12 + 1)));
			name.setEma_1(ema_1);
		}
		list.add(name);
	}

}
