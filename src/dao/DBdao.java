package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lss.util.util;
import com.mysql.cj.util.Util;

import dto.Coin_DB_dto;

 
public class DBdao {
	
    private static Connection conn; //DB 커넥션 연결 객체
    static String[] code =util.readLineFile("C:/dev/API.txt").split("\\n");
    private static final String USERNAME = code[0];//DBMS접속 시 아이디
    private static final String PASSWORD = code[1];//DBMS접속 시 비밀번호
    private static final String URL = code[2];//DBMS접속할 DB명
    
    public DBdao() {
        try {
            System.out.println("생성자");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("연결 성공");
        } catch (Exception e) {
            System.out.println("연결 실패 ");
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException e1) {    }
        }
        
        
    }
    
    
    //MainClass에서 받은 데이터 DB 삽입 메소드
    public void insertCoinDB(Coin_DB_dto dto){
        //쿼리문 준비
        String sql = "INSERT INTO coin_price (exchange, coinname, date, opening, closing) VALUES(?,?,?,?,?)";
        
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getExchange());
            pstmt.setString(2, dto.getCoinname());
            pstmt.setInt(3, dto.getDate());
            pstmt.setDouble(4, dto.getOpeing());
            pstmt.setDouble(5, dto.getClosing());
            
            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("데이터 삽입 성공!");
            }
            
        } catch (Exception e) {
            System.out.println("데이터 삽입 실패!");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {}
        }
        
        
    }   
    
    
   
  //DB에서 모든 행 가져오는 메소드
    public static void selectAll() {
        String sql = "SELECT * FROM coin_price";
        PreparedStatement pstmt = null;
        
    	
        try {
        	pstmt = conn.prepareStatement(sql);  
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                System.out.println("exchange: " + rs.getString("exchange"));
                System.out.println("coinname: " + rs.getString("coinname"));
                System.out.println("date: " + rs.getInt("date"));
                System.out.println("opeing: " + rs.getString("opeing"));
                System.out.println("closing: " + rs.getString("closing"));
           }
        } catch (Exception e) {
            System.out.println("select 메서드 예외발생");
        } finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {
            }
        }
    }    
//    public ArrayList<Coin_DB_dto> selectAll(){
//    	
//    }

    
    
}
