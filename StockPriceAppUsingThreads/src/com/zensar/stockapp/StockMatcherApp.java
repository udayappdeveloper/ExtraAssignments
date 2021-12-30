package com.zensar.stockapp;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StockMatcherApp extends JFrame {
	private static final long serialVersionUID = 1L;

	JTable buySellTable, orderMatcherTable;

	Map<String, BuyAndSellModel> stockMap = new TreeMap<>();
	String[][] stockData = {

			{ "IBM", "12", "11" },

			{ "Reliance", "55", "53" },

			{ "TCS", "23", "23" },

			{ "Wipro", "17", "15" },

			{ "Zensar", "74", "70" }

	};;
	String[] stockColumnNames = { "Stock Name", "Buy price", "Sell price" };;

	String[] orderMatchColumnNames = { "Stock Name", "Order Price", "Order Match Count" };;
	String[][] orderMatchData = {

			{ "IBM", "", "" },

			{ "Reliance", "", "" },

			{ "TCS", "", "" },

			{ "Wipro", "", "" },

			{ "Zensar", "", "" }

	};;

	public StockMatcherApp()

	{

		UpdateUiWithBaseValues();
		inItBasicMap();
		startThreads();

	}

	private void startThreads() {

		Runnable buyStock = () -> {

			try {
				buyStock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Runnable sellStock = () -> {

			try {
				sellStock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Runnable matchStock = () -> {

			try {
				matchStock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		// By Using Threads

		/*
		 * new Thread(buyStock).start(); 
		 * new Thread(sellStock).start(); 
		 * new Thread(matchStock).start();
		 * 
		 */

		// By Using FixedThreadPool
		ExecutorService poolExecutorService = Executors.newFixedThreadPool(3);
		poolExecutorService.execute(buyStock);
		poolExecutorService.execute(sellStock);
		poolExecutorService.execute(matchStock);
		poolExecutorService.shutdown();

	}

	public void UpdateUiWithBaseValues() {

		Container contentPane = getContentPane();

		contentPane.setLayout(new FlowLayout());

		buySellTable = new JTable(stockData, stockColumnNames);

		orderMatcherTable = new JTable(orderMatchData, orderMatchColumnNames);

		JScrollPane spBuySell = new JScrollPane(buySellTable);

		spBuySell.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Stock Buy/Sell Table"));

		JScrollPane spOrderMatcher = new JScrollPane(orderMatcherTable);

		spOrderMatcher
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Order Matcher Table"));

		contentPane.add(spBuySell);

		contentPane.add(spOrderMatcher);

	}

	public void inItBasicMap() {
		stockMap.put("IBM", new BuyAndSellModel());
		stockMap.put("Reliance", new BuyAndSellModel());
		stockMap.put("TCS", new BuyAndSellModel());
		stockMap.put("Wipro", new BuyAndSellModel());
		stockMap.put("Zensar", new BuyAndSellModel());

	}

	public static void main(String[] args)

	{
		JFrame jframe = new StockMatcherApp();
		jframe.setBounds(200, 200, 1000, 300);
		jframe.setTitle("Stock Price App");
		jframe.setVisible(true);

	}

	public void buyStock() throws InterruptedException {
		while (true) {
			int index = 0;
			for (String stockKey : stockMap.keySet()) {
				BuyAndSellModel buyAndSellTableList = stockMap.get(stockKey);
				List<Integer> buyersList = buyAndSellTableList.buyList;
				Random random = new Random();
				int stockPrice = random.nextInt(100);
				buyersList.add(stockPrice);
				stockData[index][1] = stockPrice + "";
				repaint();
				// System.out.println("Buy Price value :" + stockKey + ":" + stockPrice);
				Thread.sleep(1000);
				index++;
			}
		}
	}

	public void sellStock() throws InterruptedException {

		while (true) {
			int index = 0;
			for (String stockKey : stockMap.keySet()) {
				BuyAndSellModel buyAndSellTableList = stockMap.get(stockKey);
				List<Integer> sellersList = buyAndSellTableList.sellList;
				Random random = new Random();
				int stockPrice = random.nextInt(100);
				sellersList.add(stockPrice);
				// System.out.println("\t Sell price Value:" + stockKey + ":" + stockPrice);
				stockData[index][2] = stockPrice + "";
				repaint();
				Thread.sleep(500);
				index++;
			}

		}

	}

	public void matchStock() throws InterruptedException {

		while (true) {
			int index = 0;
			List<Integer> buyersList;
			List<Integer> sellersList;
			for (String stockKey : stockMap.keySet()) {
				BuyAndSellModel buyAndSellTableList = stockMap.get(stockKey);
				buyersList = buyAndSellTableList.buyList;
				sellersList = buyAndSellTableList.sellList;

				int avgBuyingValue = 0;
				int size = buyersList.size();
				if (size <= 5) {
					avgBuyingValue = (int) buyersList.stream().mapToInt(a -> a).average().orElse(0);

				} else {
					avgBuyingValue = (int) buyersList.stream().skip(buyersList.size() - 5).mapToInt(a -> a).average()
							.orElse(0);

				}

				int sAvgSellValue = 0;
				int sSIze = sellersList.size();

				if (sSIze > 0) {
					if (sSIze <= 5) {
						sAvgSellValue = (int) sellersList.stream().mapToInt(a -> a).average().orElse(0);

					} else {
						sAvgSellValue = (int) sellersList.stream().skip(sellersList.size() - 5).mapToInt(a -> a)
								.average().orElse(0);

					}

					final long favgBuyingValue = avgBuyingValue;

					final long favgSellingValue = sAvgSellValue;

					if (avgBuyingValue > sAvgSellValue) {
						orderMatchData[index][1] = avgBuyingValue + "";
						long count = buyersList.stream().filter(v -> v > favgBuyingValue).count();
						orderMatchData[index][2] = count + "";
					}

					else {
						orderMatchData[index][1] = sAvgSellValue + "";
						long count = sellersList.stream().filter(v -> v > favgSellingValue).count();
						orderMatchData[index][2] = count + "";
					}

					repaint();
					Thread.sleep(1000);
					index++;

				}

			}
		}

	}

}

class BuyAndSellModel {
	List<Integer> buyList = new CopyOnWriteArrayList<Integer>();
	List<Integer> sellList = new CopyOnWriteArrayList<Integer>();
}