package SelfDB;

import java.util.Scanner;

import dao.DBdao;
import dto.Coin_DB_dto;

public class MainClass {
	public static void main(String[] args) {
		
//		Input Coin Data
//		Opening and closing price of each exchange.
		
		// User Input 
		
		Scanner ex = new Scanner(System.in);
		System.out.println("거래소: ");
		String a = ex.next();
		
		Scanner coin = new Scanner(System.in);
		System.out.println("코인종목: ");
		String b = coin.next();
		
		Scanner date = new Scanner(System.in);
		System.out.println("날짜: ");
		int c = date.nextInt();
		
		Scanner opening = new Scanner(System.in);
		System.out.println("시가: ");
		double d = opening.nextDouble();
		
		Scanner closing = new Scanner(System.in);
		System.out.println("종가: ");
		double e = closing.nextDouble();
		
		// Insert DB
		Coin_DB_dto dto = new Coin_DB_dto();
		dto.setExchange(a);
		dto.setCoinname(b);
		dto.setDate(c);
		dto.setOpening(d);
		dto.setClosing(e);
		
		DBdao dao = new DBdao();
		dao.insertCoinDB(dto);
		
		// View All Data
		dao.selectAll();
	}
}
