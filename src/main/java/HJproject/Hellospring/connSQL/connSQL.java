package HJproject.Hellospring.connSQL;


import HJproject.Hellospring.domain.Member;
import org.hibernate.annotations.common.reflection.XMember;

import java.sql.*;
import java.util.ArrayList;

public class connSQL {

    public static void main(String[] args){


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SQL = "select * from member";

        try {
            System.out.println("연결중1");

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("연결중2");

            conn = DriverManager.getConnection("jdbc:mysql://210.220.67.85:3361/hjproject","jsj","639258");
            System.out.println("접속 성공!! : "+ conn);


            System.out.println("Step 2");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);

            ArrayList<Member> list = new ArrayList<Member>();

            while(rs.next()){
                Member member = new Member();

                member.setCode(rs.getLong("CODE"));
                member.setName(rs.getString("NAME"));
                list.add(member);

            }

            for(int i=0; i<list.size(); i++){
                System.out.println("CODE : "+ list.get(i).getCode());
                System.out.println("name : "+ list.get(i).getName());

            }



        }

        catch(Exception e) {
            System.out.println("드라이버 로딩 실패");

            e.printStackTrace();

        }

        finally {
            try {
                if( conn != null && !conn.isClosed()){
                    conn.close();

                }

            }catch (Exception e) {
                e.printStackTrace();

            }

        }

    }
}




