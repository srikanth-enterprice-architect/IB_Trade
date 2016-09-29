/**
 * 
 */
package com.idgoSoft.trade.data;

/**
 * @author srikanth.vaddella
 *
 */
public class DataFeed {

    public int timestamp;
    private double close;
    private double high;
    private double low;
    private double open;
    private int volume;
    private double macd;
    private double ema_1;
    private double ema_2;
    private double signal;
    private double histogram;
    private double highest_High;
    private double lowest_Low;
    private double persentage_of__k;
    private double persentage_of__d;
    private String ticker;


    
    

    /**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * @return the highest_High
	 */
	public double getHighest_High() {
		return highest_High;
	}

	/**
	 * @param highest_High the highest_High to set
	 */
	public void setHighest_High(double highest_High) {
		this.highest_High = highest_High;
	}

	/**
	 * @return the lowest_Low
	 */
	public double getLowest_Low() {
		return lowest_Low;
	}

	/**
	 * @param lowest_Low the lowest_Low to set
	 */
	public void setLowest_Low(double lowest_Low) {
		this.lowest_Low = lowest_Low;
	}

	/**
	 * @return the persentage_of__k
	 */
	public double getPersentage_of__k() {
		return persentage_of__k;
	}

	/**
	 * @param persentage_of__k the persentage_of__k to set
	 */
	public void setPersentage_of__k(double persentage_of__k) {
		this.persentage_of__k = persentage_of__k;
	}

	/**
	 * @return the persentage_of__d
	 */
	public double getPersentage_of__d() {
		return persentage_of__d;
	}

	/**
	 * @param persentage_of__d the persentage_of__d to set
	 */
	public void setPersentage_of__d(double persentage_of__d) {
		this.persentage_of__d = persentage_of__d;
	}

	/**
	 * @return the signal
	 */
	public double getSignal() {
		return signal;
	}

	/**
	 * @param signal the signal to set
	 */
	public void setSignal(double signal) {
		this.signal = signal;
	}

	/**
	 * @return the histogram
	 */
	public double getHistogram() {
		return histogram;
	}

	/**
	 * @param histogram the histogram to set
	 */
	public void setHistogram(double histogram) {
		this.histogram = histogram;
	}

	/**
	 * @return the ema_1
	 */
	public double getEma_1() {
		return ema_1;
	}

	/**
	 * @param ema_1 the ema_1 to set
	 */
	public void setEma_1(double ema_1) {
		this.ema_1 = ema_1;
	}

	/**
	 * @return the ema_2
	 */
	public double getEma_2() {
		return ema_2;
	}

	/**
	 * @param ema_2 the ema_2 to set
	 */
	public void setEma_2(double ema_2) {
		this.ema_2 = ema_2;
	}

	/**
     * @return the macd
     */
    public double getMacd() {
	return macd;
    }

    /**
     * @param macd
     *            the macd to set
     */
    public void setMacd(double macd) {
	this.macd = macd;
    }

    /**
     * @return the timestamp
     */
    public int getTimestamp() {
	return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(int timestamp) {
	this.timestamp = timestamp;
    }

    /**
     * @return the close
     */
    public double getClose() {
	return close;
    }

    /**
     * @param close
     *            the close to set
     */
    public void setClose(double close) {
	this.close = close;
    }

    /**
     * @return the high
     */
    public double getHigh() {
	return high;
    }

    /**
     * @param high
     *            the high to set
     */
    public void setHigh(double high) {
	this.high = high;
    }

    /**
     * @return the low
     */
    public double getLow() {
	return low;
    }

    /**
     * @param low
     *            the low to set
     */
    public void setLow(double low) {
	this.low = low;
    }

    /**
     * @return the open
     */
    public double getOpen() {
	return open;
    }

    /**
     * @param open
     *            the open to set
     */
    public void setOpen(double open) {
	this.open = open;
    }

    /**
     * @return the volume
     */
    public int getVolume() {
	return volume;
    }

    /**
     * @param volume
     *            the volume to set
     */
    public void setVolume(int volume) {
	this.volume = volume;
    }

	@Override
	public String toString() {
		return "DataFeed [timestamp=" + timestamp + ", close=" + close
				+ ", high=" + high + ", low=" + low + ", open=" + open
				+ ", volume=" + volume + ", macd=" + macd + ", ema_1=" + ema_1
				+ ", ema_2=" + ema_2 + ", signal=" + signal + ", histogram="
				+ histogram + "]";
	}
    
    
    

}
