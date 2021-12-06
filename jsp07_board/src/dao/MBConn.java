package dao;



import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MBConn {
	public static SqlSession getSession() {
		SqlSession session = null;
		String resource = "mybatis/mybatisConfig.xml";
		try {
			//마이바티스 환경 xml파일
			InputStream is = Resources.getResourceAsStream(resource);
			//팩토리 만들기
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
			session = sf.openSession();
			System.out.println("session생성 성공");
		} catch (IOException e) {
			System.out.println("mybatis환경파일 읽기 실패");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return session;
	}
}
