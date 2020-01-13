package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.LetterBean;
import bean.MemberBean;



public class MemberDAO {
	
	//기본 적으로 석현 씨가 올린 이메일 검색 메서드가 있는 DAO에 만들었 습니다 
	
	
	//커넥션,프리페어드스테이트먼트,리설트셋 미리 선언해뒀습니다
	//아래의 이름으로 사용해 주세요
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet res=null;
	DataSource ds=null;
	
	//커넥션풀 얻는 메소드
	private Connection getConnection() throws Exception{
		Connection conn=null;
		Context init=new InitialContext();	
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/roomdy");	
		
		//커넥션풀 얻기
		conn=ds.getConnection();
		
		return conn;
		
	}//getConnection()메서드 끝
	
	
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
	}//closeAll()메서드 끝
	
	
	
	//회원탈퇴 시 아이디, 비번 맞을때 (탈퇴 직전 확인)
	public int dleMemberKo(String id, String pw){
		int check = -1; // 1 ->아이디, 비밀번호 맞음
						// 0 -> 아이디 맞음, 비밀번호 틀림
						// -1 ->  아이디 틀림
		String sql ="";
		
		try {
			conn = getConnection();
			sql = "select * from dymember where id=?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			res = pst.executeQuery();
			
			if(res.next()){
				if(pw.equals(res.getString("pw"))){
					check = 1; 
				}else {
					check = 0; 
				}
			}else{
				check = -1;
			}
		} catch (Exception e) {
			System.out.println("탈퇴 오류"+e);
			e.printStackTrace();
		}finally{
			closeAll(conn,pst,res);
		}
		return check;
	}//login()메서드 끝
	
	
	//아이디 비밀번호 비교
		public boolean login(String id, String pw){
			try{
				conn = ds.getConnection();
				String sql = "select id from dymember "
									+ "where id = ? and pw = ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				pst.setString(2, pw);
				res = pst.executeQuery();
				
				while(res.next()){
					return true; 
				}
			}
			catch(Exception e){
				System.out.println("아이디와 비밀번호 비교 오류");
				e.printStackTrace();
			}
			finally{
				closeAll(conn,pst,res);
			}
			return false;
		} // login 메소드 종료
	
		//회원탈퇴
		public void delMember(String email){
			try {
				conn = ds.getConnection();
				String sql = "DELETE FROM dymember WHERE id=?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, email);
				pst.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("Error in deleteMember()");
				e.printStackTrace();
			}finally {
				closeAll(conn,pst,res);
			}
		}//delMember메소드 종료
	
	//중복 아이디가 있는지 찾는 메서드
	public boolean checkUser(String id) {
		boolean check = false;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT id, pw FROM dymember WHERE id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			
			res = pst.executeQuery();
			
			if(res.next()) check = true;
			else check = false;
		} catch(Exception e) {
			System.out.println("checkUser()메서드에서 에러 : " + e);
		} finally {
			closeAll(conn,pst,res);
		}
		
		return check;
	}//중복 아이디 메서드 끝
	
	//회원가입 추가 하는 메서드
	public void addMember(MemberDAO memberBean){
		try {
			//커넥션 갖고오기
			conn = getConnection();
			/*Connection conn = ds.getConnection();
			String id = memberBean.getId();
			String pw = memberBean.getPw();
			String name = memberBean.getName();
			String nickname = memberBean.getNickname();
			String email = memberBean.getEmail();
			Timestamp joindate = memberBean.getJoindate();
			Integer zipcode = memberBean.getZipcode();
			String addr1 = memberBean.getAddr1();
			String addr2 = memberBean.getAddr2();
			String auth = memberBean.getAuth();
			String style = memberBean.getStyle();*/
			//sql문
			String sql = "insert into dymember(id, pw, name, nickname, email, joindate, zipcode, addr1, addr2, auth, style)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
		/*  pst.setString(1, id);
			pst.setString(2, pw);
			pst.setString(3, name);
			pst.setString(4, nickname);
			pst.setString(5, email);
			pst.setTimestamp(6, joindate);
			pst.setInt(7, zipcode);
			pst.setString(8, addr1);
			pst.setString(9, addr2);
			pst.setString(10, auth);
			pst.setString(11, style);*/
			pst.setString(1, memberBean.getId());
			pst.setString(2, memberBean.getPw());
			pst.setString(3, memberBean.getName());
			pst.setString(4, memberBean.getNickname());
			pst.setString(5, memberBean.getEmail());
			pst.setTimestamp(6, memberBean.getJoindate());
			pst.setInt(7, memberBean.getZipcode());
			pst.setString(8, memberBean.getAddr1());
			pst.setString(9, memberBean.getAddr2());
			pst.setInt(10, memberBean.getAuth());
			pst.setString(11, memberBean.getStyle());
			//prepareStatement에 설정된 INSERT전체 문장을 DB에 실행
			pst.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println("addMember안들어감 :" + e);
		}finally {
			//자원해제
			closeAll(conn,pst,null);
		}
		
	}//addMember() 메서드 끝
	
	
	//마이 페이지 확인 하는 메서드
	public MemberBean infoMember(String id){
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}//infoMember() 메서드 끝
	
	
	//이메일 인증 하는 메서드    (변수를 적어주세요 ㅠㅠ)
	public boolean emailMember(String email){
		boolean result = true;
		try {
			conn = getConnection();
			String sql = "SELECT email FROM p_member WHERE email=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			res = pst.executeQuery();
			if(res.next()){
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			System.out.println("Error in emailMember()");
			e.printStackTrace();
		}finally {
			closeAll(conn, pst, res);
		}
		return result;
	}//emailMember()메서드 끝
	
	//이메일 인증번호 6자리 생성 메서드
	public String authNum() {
		StringBuffer authNum = new StringBuffer();

		for (int i = 0; i < 6; ++i) {
			int randNum = (int) (Math.random() * 10.0D);
			authNum.append(randNum);
		}

		return authNum.toString();
	}//이메일 인증 번호 6자리 메서드 끝
	
	
	//회원 수정 하는  메서드
	public int modMember(MemberBean MemberBean){
		
		try {
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}//modMember()메서드 끝
	
	
	//아이디 찾기 메서드
	public MemberBean findId(String id){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}//findId()메서드 끝
	
	
	//비밀번호 찾기 메서드
	public MemberBean findPw(String id){
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}//findPw()메서드 끝
	
	
	//회원 조회 하는 메서드
	public List<MemberBean> listMember(String id){
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}//listMember()메서드 끝
	
	
	//받는사람 id를 기준으로 이메일 검색하는 메소드
	public ArrayList<LetterBean> getMails(String id){
		ArrayList<LetterBean> list=new ArrayList<LetterBean>();
		String sql="select * from dyletter where recipient=?";
		/*try {
			LetterBean b=new LetterBea
			conn=getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, id);				
			res=pst.executeQuery();
			
			while(res.next()){
				b.setTitle(res.getString("title"));
				b.setContent(res.getString("content"));					
				b.setSender(res.getString("sender"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("이메일 얻기중 에러");
		}
		
		
		
		return list;*/
		return null;
	}//getMails() 메서드 끝
	
	
	
	//메일 보내기(쓰기)메서드    (변수를 적어주세요 ㅠㅠ)
	public MemberBean sendLetter(){
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}//sendLetter() 메서드끝
	
	
	//메일 삭제 메서드    (변수를 적어주세요 ㅠㅠ)
	public void delLetter(){
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//delLetter()메서드 끝
	
	
	//메일 읽기 메서드    (변수를 적어주세요 ㅠㅠ)
	public void readLetter(){
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//readLetter()메서드 끝
	
	
	//메일수 확인 메서드    (변수를 적어주세요 ㅠㅠ)
	public void countLetter(){
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//countLetter()메서드 끝
	
	
	//메일 목록(여러개를 볼수있는)메서드    (변수를 적어주세요 ㅠㅠ)
	public List<MemberBean> listLetter(){
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}//listLetter()메서드 끝

	
	
	//DB 연결 시험 메서드 
	/*public void test(){
		try {
			conn=getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("성공");
	}*/
	
}
