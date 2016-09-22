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

import com.idgoSoft.trade.core.excel.ExcelPreparation;
import com.idgoSoft.trade.data.DataFeed;
import com.idgoSoft.trade.data.IndicatorPojo;

/**
 * @author srikanth.vaddella
 *
 */
public class Transformer {

	private IndicatorPojo indicatorPojo;
	ToDoubleFunction<DataFeed> averageCaluction = dataFeed -> {
		System.out.println(dataFeed.getClose());
		return dataFeed.getClose();
	};
	
	ToDoubleFunction<DataFeed> averageCaluction1 = dataFeed -> {
		System.out.println(dataFeed.getClose());
		return dataFeed.getMacd();
	};

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

		indicatorPojo.setAverage(indicatorPojo.getDatafeedList().stream()
				.limit(12)
				.collect(Collectors.averagingDouble(averageCaluction)));
		indicatorPojo.setAverage2(indicatorPojo.getDatafeedList().stream()
				.limit(26)
				.collect(Collectors.averagingDouble(averageCaluction)));
		List<DataFeed> listOfDatafeed = indicatorPojo
				.getDatafeedList()
				.stream()
				.skip(12)
				.collect(ArrayList<DataFeed>::new,
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
		double ema;
		double ema2;
		double ema_1 = 0;
		double ema_2;
		int size = list.size();

		double average = indicatorPojo.getAverage();
		double average2 = indicatorPojo.getAverage2();
		double close = dataFeed.getClose();
		double signalAvg;

		if (size == 0) {
			dataFeed.setEma_1(average);
		}
		if (size > 0) {
			ema = list.get(size - 1).getEma_1();
			ema_1 = (close * (2 / (12 + 1)) + ema * (1 - (2 / (12 + 1))));
			dataFeed.setEma_1(ema_1);
		}
		if (size == 14) {
			dataFeed.setEma_2(average2);
			dataFeed.setMacd(ema_1 - average2);
		}
		if (size > 14) {
			ema2 = list.get(size - 1).getEma_2();
			ema_2 = (close * (2 / (26 + 1)) + ema2 * (1 - (2 / (26 + 1))));
			dataFeed.setEma_2(ema_2);
			dataFeed.setMacd(ema_1 - ema_2);

		}
		if (size == 22) {
			signalAvg = list.stream().skip(13).collect(Collectors.averagingDouble(averageCaluction1));
			dataFeed.setSignal(signalAvg);
			dataFeed.setHistogram(dataFeed.getMacd() - dataFeed.getSignal());
		}

		if (size > 22) {
			double signal = (dataFeed.getMacd() * (2 / (9 + 1)) + (list.get(size-1).getSignal() - 1) * (1 - (2 / (9 + 1))));
			dataFeed.setSignal(signal);
			dataFeed.setHistogram(dataFeed.getMacd() - dataFeed.getSignal());
		}
		list.add(dataFeed);
	}

}
