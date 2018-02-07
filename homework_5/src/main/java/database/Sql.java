package database;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import vo.Program;


public class Sql {

    private Mapper iMapper;
    private SqlSession sqlSession;

    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "conf.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(
                    resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public Sql() {
        sqlSession= getSessionFactory().openSession(true);
        iMapper = sqlSession.getMapper(Mapper.class);
    }

    public int InsertProgram2Mysql(List<Program> programs){
        int n=0;
//        Program p1=iMapper.findById("11");
        for(Program p:programs){
            if(p.getDeadline_Without_Aid().length()>256) continue;
            try {
                iMapper.insertProgram(p);
                n++;
            }catch (Exception e){
                System.out.print(p.getProgram_name()+"\n");
            }
        }
        return n;
    }

    public Mapper getiMapper() {
        return iMapper;
    }
}



