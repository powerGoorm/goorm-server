package com.powerGoorm.repositroy;

import com.powerGoorm.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.processing.SQL;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final DataSource datasource;

    /*public MemberRepository(DataSource data){
        this.datasource=data;
    }*/

    public void save(Member member) throws SQLException {
        String sql="insert into member(id,name,password,git,intro,created_dt,deleted_dt) values(?,?,?,?,?,?,?)";

        try{

            Connection con=getConnection();
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,member.getId());
            pstm.setString(2,member.getName());
            pstm.setString(3,member.getPassword());
            pstm.setString(4,member.getGiturl());
            pstm.setString(5,member.getIntro());
            pstm.setString(6, new Date().toString());
            pstm.setString(7,"0");


            pstm.executeUpdate();

        }
        catch(SQLException e){

        }
        finally{
            log.info("member set ending");
        }

    }

    public boolean findbyusername(String id) {
        String sql="select * from member where id=?";

        try{

            Connection con=getConnection();
            log.info("information on connection={}",con);
            PreparedStatement pstm=con.prepareStatement(sql);

            pstm.setString(1,id);

            ResultSet rs=pstm.executeQuery();

            if(rs.next()){

            List<String> columnname=new ArrayList<>(Arrays.asList("id","name","password","git","intro","created_dt","deleted_dt"));

            for(String  x : columnname){
                log.info("logdata:{}",rs.getString(x));

            }
                return true;
            }
            else{
                log.info("not found in db error");
                return false;

            }



        }
        catch(SQLException e){
           log.info("user not found in db error");
           return false;
        }
        finally{
            log.info("search end");

        }




    }

    public void update(Member member) throws SQLException {
        try {
            String sql = "update member set id=? name=? password=? git=? intro=?  where member_id=?";
            Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);

            List<String> columnname=new ArrayList<>(Arrays.asList("id","name","password","git","intro"));
            for(int i=0;i<5;i++){
                pstm.setString(i+1,columnname.get(i));
            }


            pstm.executeQuery();
        } catch (SQLException e) {
            log.info("update error");
            throw e;
        }
        finally{
            log.info("update end");
        }
    }
    public void delete(String id) throws SQLException{

        try{

            String sql="delete from member where id=?";
            Connection con=getConnection();
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.setString(1,id);
            pstm.executeUpdate();

        }catch(SQLException e){
            log.info("err");
            throw e;
        }
        finally{

        }


    }


    public void close(ResultSet rs,PreparedStatement psmt,Connection con){

        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(psmt);
        DataSourceUtils.releaseConnection(con,datasource);
    }

    public Connection getConnection(){
        Connection con= DataSourceUtils.getConnection(datasource);

        return con;
    }
}
