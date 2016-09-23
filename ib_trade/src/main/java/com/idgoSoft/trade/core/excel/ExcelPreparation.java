package com.idgoSoft.trade.core.excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.idgoSoft.trade.data.DataFeed;
import com.idgoSoft.trade.data.IndicatorPojo;

public class ExcelPreparation {

	public void writeExcelFile(IndicatorPojo indicatorPojo) throws IOException {
		String[] headers = { "timestamp", "close", "high", "low", "open",
				"volume", "ema_1", "ema_2", "macd", "signal", "histogram" };
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		String excelFilePath = "NiceJavaBooks.xls";

		int rowCount = 1;
		writeTitleForBook(headers, sheet.createRow(0));
		for (DataFeed dataFeed : indicatorPojo.getResultedDataFeed()) {
			Row row = sheet.createRow(++rowCount);
			writeBookValues(dataFeed, row);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}
	}

	public void writeBookValues(DataFeed dataFeed, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(dataFeed.getTimestamp());

		cell = row.createCell(1);
		cell.setCellValue(dataFeed.getClose());

		cell = row.createCell(2);
		cell.setCellValue(dataFeed.getHigh());

		cell = row.createCell(3);
		cell.setCellValue(dataFeed.getLow());

		cell = row.createCell(4);
		cell.setCellValue(dataFeed.getOpen());

		cell = row.createCell(5);
		cell.setCellValue(dataFeed.getVolume());

		cell = row.createCell(6);
		cell.setCellValue(dataFeed.getEma_1());

		cell = row.createCell(7);
		cell.setCellValue(dataFeed.getEma_2());

		cell = row.createCell(8);
		cell.setCellValue(dataFeed.getMacd());

		cell = row.createCell(9);
		cell.setCellValue(dataFeed.getSignal());

		cell = row.createCell(10);
		cell.setCellValue(dataFeed.getHistogram());

	}

	public void writeTitleForBook(String[] headers, Row row) {

		for (int i = 0; i < headers.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
		}

	}

}