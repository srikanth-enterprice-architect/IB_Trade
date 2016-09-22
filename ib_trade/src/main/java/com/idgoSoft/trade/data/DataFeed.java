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

}
