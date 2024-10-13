package com.sinjin.database;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

class TestConnetion {

   @Test
   void testConnetion() {
      
      Connection conn = null;
      
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String id = "scott";
        String pw = "tiger";
      
       try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
          
           conn = DriverManager.getConnection(url, id, pw);
           System.out.println(conn.isClosed()?"접속종료":"접속중");

           conn.close();
           System.out.println(conn.isClosed()?"접속종료":"접속중");
      } catch (Exception e) {
          e.printStackTrace();
      } 
      
      
      
   }

}