package com.mkyong.common;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mkyong.models.Category;
import com.mkyong.models.Stock;
import com.mkyong.models.StockDetail;
import com.mkyong.persistence.HibernateUtil;

public class App {
	
	public static HibernateUtil hu = HibernateUtil.getHibernateUtil();
	static Logger log = Logger.getRootLogger();
	
    @SuppressWarnings("deprecation")
	public static void main( String[] args ) {
//// 1. Connecting and creating Data.
//    	Stock stock1 = new Stock(0, "JUL", "Juul Enterprise");
//		hu.save(stock1);
//		
//		
//    	Stock stock2 = new Stock(0, "GOOG", "Google.com");
//		hu.save(stock2);
//    	Stock stock3 = new Stock(0, "APL", "Apple");
//		hu.save(stock3);
//    	Stock stock4 = new Stock(0, "WIN", "Winston Sports Gear");
//		hu.save(stock4);
//// 2. OneToOne Relationships
    	Stock stock = new Stock(0, "GOOG", "Google.com");
    	StockDetail stockDetail = new StockDetail();
		stockDetail.setCompName("Google Incorporated");
		stockDetail.setCompDesc("Tech giant");
		stockDetail.setRemark("www.google.com");
		//stockDetail.setListedDate(new Date());

		stock.setStockDetail(stockDetail);
		stockDetail.setStock(stock);
    	
		hu.save(stock); // Notice only the stock needs got saved here!

//// 3. OneToMany Relationship
//        StockDailyRecord stockDailyRecord = new StockDailyRecord();
//        stockDailyRecord.setPriceOpen(1.2f);
//        stockDailyRecord.setPriceClose(1.1f);
//        stockDailyRecord.setPriceChange(10.0f);
//        stockDailyRecord.setVolume(3000000L);
//        stockDailyRecord.setDate(new Date(2018,2,23));
//        
//        Stock stock = hu.read(Stock.class, 4);
//        hu.newDailyRecord(stock, stockDailyRecord);
        
 //// 4. ManyToMany
//  		Stock stock = new Stock();
//        stock.setStockCode("045");
//        stock.setStockName("CLA");
//  		Stock stock2 = new Stock();
//        stock2.setStockCode("999");
//        stock2.setStockName("DVLNMB");
//      	Stock stock = hu.criteriaGetObjectsByField(Stock.class, "CLA", "name").get(0);
//      	Stock stock2 = hu.criteriaGetObjectsByField(Stock.class, "DVLNMB", "name").get(0);
//        Stock stock3 = hu.read(Stock.class, 4);
// 
////        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
//        Category category1 = hu.read(Category.class,1);
// //       Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");
//    	Category category2 = hu.criteriaGetObjectsByField(Category.class, "INVESTMENT", "name").get(0);
//    	//        Category category3 = new Category("ENTERTAINMENT", "FOR FUN COMPANY");
//        Category category3 = hu.read(Category.class,3);
// //       Category category4 = new Category("RESEARCH", "SCEINTIFIC COMPANY");
//    	Category category4 = hu.criteriaGetObjectsByField(Category.class, "CONSUMER", "name").get(0);

//        hu.addCategoryToStocks(category1,stock,stock2);
//        log.fatal("ONE");
//        hu.addCategoryToStocks(category3,stock);        
//        log.fatal("TWO");
//
//        hu.addStockToCategories(stock2, category4, category2);
//        log.fatal("THREE");
//
//        hu.addStockToCategories(stock3, category4);
//        log.fatal("FOUR");
    	
//    	// Adding things works fine like this so long as neither already exists in the db!!
//    	Category category = new Category("ART", "MUCH ART MUCH WOWE");
//    	Stock stock = new Stock(0,"PJM","PLEASE JUST MOVE");
//    	stock.getCategories().add(category);
//    	hu.save(stock);

		System.out.println("Done");
    	
    	
    	
    	
    	
    	
    	



    }

}
