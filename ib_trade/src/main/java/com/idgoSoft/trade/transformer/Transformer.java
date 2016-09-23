/**
 * 
 */
package com.idgoSoft.trade.transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import com.IdgoSoft.Trade.core.excel.ExcelPreparation;
import com.idgoSoft.trade.data.DataFeed;
import com.idgoSoft.trade.data.IndicatorPojo;

/**
 * @author srikanth.vaddella
 *
 */
public class Transformer {

	private IndicatorPojo indicatorPojo;
	ToDoubleFunction<DataFeed> averageCaluction = dataFeed -> {
		return dataFeed.getClose();	};
	
	ToDoubleFunction<DataFeed> averageCaluction1 = dataFeed -> {
		return dataFeed.getMacd();	};

	/**
	 * @param listOfStockData
	 * @return
	 * @throws IOException 
	 */
	public IndicatorPojo dataFeedPreparation(
			ArrayList<LinkedHashMap<String, ? extends Number>> listOfStockData) throws IOException {
		indicatorPojo = new IndicatorPojo();
		indicatorPojo.setDatafeedList(listOfStockData.parallelStream().collect(
				ArrayList::new, convertDataFeedMaptoListofDatafeed(),
				ArrayList::addAll));
		dataFeedCalPreparation(indicatorPojo);
		ExcelPreparation excelPreparation = new ExcelPreparation();
		excelPreparation.writeExcelFile(indicatorPojo);
		return indicatorPojo;

	}

	/**
	 * @return
	 */
	public BiConsumer<ArrayList<DataFeed>, LinkedHashMap<String, ? extends Number>> convertDataFeedMaptoListofDatafeed() {
		BiConsumer<ArrayList<DataFeed>, LinkedHashMap<String, ? extends Number>> accumulator = (
				dataFeedList, dataFeedData) -> {
			DataFeed dataFeed = new DataFeed();
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

		indicatorPojo.setAverage(indicatorPojo.getDatafeedList().stream().limit(12).collect(Collectors.averagingDouble(averageCaluction)));
		indicatorPojo.setAverage2(indicatorPojo.getDatafeedList().stream().limit(26).collect(Collectors.averagingDouble(averageCaluction)));
		List<DataFeed> listOfDatafeed = indicatorPojo.getDatafeedList().stream().collect(
				        ArrayList<DataFeed>::new,
						caluclateIndicators(indicatorPojo),
						ArrayList<DataFeed>::addAll);
		indicatorPojo.setResultedDataFeed(listOfDatafeed);
		return indicatorPojo;

	}

	/**
	 * @param indicatorPojo
	 * @return
	 */
	public BiConsumer<ArrayList<DataFeed>, DataFeed> caluclateIndicators(
			IndicatorPojo indicatorPojo) {
		return (list, name) -> caluclate(indicatorPojo, list, name);
	}

	/**
	 * @param indicatorPojo
	 * @param list
	 * @param name
	 */
	public void caluclate(IndicatorPojo indicatorPojo,	ArrayList<DataFeed> list, DataFeed dataFeed) {
		int size = list.size();
		double a = (12+1);
		double a1 = (2/a);
		
		double b = (26+1);
		double b1 = (2/b);
		
		double c = (9+1);
		double c1 = (2/c);
		
		if (size == 11) {
			dataFeed.setEma_1(indicatorPojo.getAverage());
		}
		if (size > 11) {
			dataFeed.setEma_1(dataFeed.getClose() * a1 + (list.get(size - 1).getEma_1()) * (1 - a1));
		}
		if (size == 25) {
			dataFeed.setEma_2(indicatorPojo.getAverage2());
			dataFeed.setMacd(dataFeed.getEma_1() - dataFeed.getEma_2());
		}
		if (size > 25) {
			dataFeed.setEma_2((dataFeed.getClose() * b1 + (list.get(size - 1).getEma_2()) * (1 - b1)));
			dataFeed.setMacd(dataFeed.getEma_1() - dataFeed.getEma_2());

		}
		if (size == 33) {
			dataFeed.setSignal(list.stream().skip(25).collect(Collectors.averagingDouble(averageCaluction1)));
			dataFeed.setHistogram(dataFeed.getMacd() - dataFeed.getSignal());
		}

		if (size > 33) {
			dataFeed.setSignal(dataFeed.getMacd() * c1 + (list.get(size-1).getSignal() - 1) * (1 - c1));
			dataFeed.setHistogram(dataFeed.getMacd() - dataFeed.getSignal());
		}
		
		System.out.println(dataFeed.toString());
		list.add(dataFeed);
	}

}
