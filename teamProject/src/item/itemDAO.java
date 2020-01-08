package item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class itemDAO {

	//커넥션풀 얻는 메소드
	private Connection getConnection() throws Exception{
		Connection conn=null;
		Context init=new InitialContext();	
		DataSource ds=(DataSource)init.lookup("");	//아직 db가 만들어진게 아니라 비워둠 나중에 써야함
		//커넥션풀 얻기
		conn=ds.getConnection();
		
		return conn;
		
	} // getConnection()메소드 끝
	
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
	}// closeAll()메소드 끝
	
	
	
	public void addOrder(){ //주문등록(구매자가 결제 정보를 등록하는 단계)

		
	}//addOrder()메소드 끝
	
	
	
	public void modOrder(){//주문수정(구매자가 결제 정보를 수정하는 단계-주소지, 수량, 상품 옵션 등)
		
	}//modOrder()메소드 끝
	
	
	
	public void delOrder(){ //주문취소(구매자가 주문을 취소하는 단계)
		
	}//delOrder()메소드 끝
	
	
	public void addItem(){ //상품등록(판매자가 상품을 등록하는 단계)
		
	}//addItem()메소드 끝
	
	
	public void modItem(){ //상품수정(판매자가 상품에 대한 정보를 수정)
		
	}//modItem()메소드 끝
	
	
	public void delItem(){ //상품삭제(판매자가 상품을 삭제)
		
	}//delItem()메소드 끝
	
	
	public void listItem(){
		//상품조회(구매자가 구입한 상품을 조회-단순 상품 리스트만 뜨게 하며 그 리스트에서 그 상품을 클릭했을 시, 상세조회 페이지-상품1개 조회 페이지로 이동)
		
	}//listItem()메소드 끝
	
	
	public void addCart(){ //장바구니 등록(구매자의 장바구니 담기 기능)
		
	}//addCart()메소드 끝
	
	
	public void modCart(){ //장바구니 수정(구매자가 담아놓은 상품중 수량, 색상등의 옵션을 수정하는 기능)
		
	}//modCart()메소드 끝
	
	
	public void delCart(){ //장바구니 삭제(담아놓은 상품 삭제)
		
	}//delCart()메소드 끝
	
	
	public void listCart(){ //상품1개 조회(상품조회페이지에서 상품을 클릭했을 시 구매한 상품 상세조회-상품정보, 구입정보 조회)
		
	}//listCart()메소드 끝
	
	
	public void styleItem(){ //상픔추천(회원가입시 입력한 선호 스타일에 맞는 스타일 추천-큐트, 에니, 모던 등등)
		
	}//styleItem()메소드 끝
	
	
	public void listOrder(){ //주문조회(결제후 주문 상태 간단히 확인-결제진행중, 결제완료, 제작중, 배송중, 배송완료, 상품취소중 등등)
		
	}//listOrder()메소드 끝
	
	
	public void readOrder(){ //주문 상세보기(결제 완료 후 상품 단계-상품 제작과정 단계 및 배송조회 )
		
	}//readOrder()메소드 끝
	
} //ItemDAO()메소드 끝

