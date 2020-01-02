package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	//커넥션풀 얻는 메소드
	private Connection getConnection() throws Exception{
		Connection conn=null;
		Context init=new InitialContext();	
		DataSource ds=(DataSource)init.lookup("");	//아직 db가 만들어진게 아니라 비워둠 나중에 써야함
		//커넥션풀 얻기
		conn=ds.getConnection();
		
		return conn;
		
	}
	
	//자원 반납메소드 입니다 한번에 close할수있는데 없는 항목은 null로 쓰면 됩니다
	private void closeAll(Connection c,PreparedStatement p,ResultSet r){
		try {
			if(c!=null) c.close();
			if(p!=null) p.close();
			if(r!=null) r.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("자원 반납중 에러");
		}
	}
	
}
