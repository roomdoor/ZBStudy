package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberService {

    public List<Member> list() {
        List<Member> memberList = new ArrayList<>();

        //5 개
        // 1. ip (doamin)
        // 2. port
        // 3. 계정
        // 4. 패스워드
        // 5. 인스턴스

        String url = "jdbc:mariadb://localhost:3306/testdb";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";

        // 1. 드라이버 로드
        // 2. 커넥션 객체 생성
        // 3. 스테이트먼트 객체 생성
        // 4. 쿼리 실행
        // 5. 결과 수행
        // 6. 객체 연결 해제(close)


        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet rs = null;
        String memberTypeVale = "email";
        try {
            // 2. 커넥션 객체 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            // 3. 스테이트먼트 객체 생성
//            statement = connection.createStatement();

            // 4. 쿼리 실행
            String sql = "select member_type, user_id, password, name " +
                    "from member " +
                    "where member_type = ? ;";

            // 3. prepareStatement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeVale);
//            preparedStatement.setString(2, userIdValue);

//            rs = statement.executeQuery(sql);
            rs = preparedStatement.executeQuery();


            // 5. 결과 실행
            while (rs.next()) {
                String memberType = rs.getString("member_type");
                String userId = rs.getString("user_id");
                String password = rs.getString("password");
                String name = rs.getString("name");

                Member member = new Member(memberType, userId, password, name);

                memberList.add(member);

                System.out.println(memberType + ", " + userId + ", " + password + ", " + name);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return memberList;
    }

    //회원가입
    public void register(Member member) {

        //5 개
        // 1. ip (doamin)
        // 2. port
        // 3. 계정
        // 4. 패스워드
        // 5. 인스턴스

        String url = "jdbc:mariadb://localhost:3306/testdb";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";

        // 1. 드라이버 로드
        // 2. 커넥션 객체 생성
        // 3. 스테이트먼트 객체 생성
        // 4. 쿼리 실행
        // 5. 결과 수행
        // 6. 객체 연결 해제(close)


        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet rs = null;
        String memberTypeVale = member.getMemberType();
        String userIdValue = member.getUserId();
        String passwordValue = member.getPassword();
        String nameValue = member.getName();

        try {
            // 2. 커넥션 객체 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            // 3. 스테이트먼트 객체 생성
//            statement = connection.createStatement();

            // 4. 쿼리 실행
            String sql = "insert into member (member_type, user_id, password, name) " +
                    "VALUES (?, ?, ?, ?);";

            // 3. prepareStatement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeVale);
            preparedStatement.setString(2, userIdValue);
            preparedStatement.setString(3, passwordValue);
            preparedStatement.setString(4, nameValue);

            int affeced = preparedStatement.executeUpdate();

            if (affeced > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }

            // 5. 결과 실행


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    public void dbUpdate() {
        //5 개
        // 1. ip (doamin)
        // 2. port
        // 3. 계정
        // 4. 패스워드
        // 5. 인스턴스

        String url = "jdbc:mariadb://localhost:3306/testdb";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";

        // 1. 드라이버 로드
        // 2. 커넥션 객체 생성
        // 3. 스테이트먼트 객체 생성
        // 4. 쿼리 실행
        // 5. 결과 수행
        // 6. 객체 연결 해제(close)


        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet rs = null;
        String memberTypeVale = "email";
        String userIdValue = "zerobase@zerobase.com";
        String passwordValue = "9999";
        String nameValue = "제로베이스";

        try {
            // 2. 커넥션 객체 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            // 3. 스테이트먼트 객체 생성
//            statement = connection.createStatement();

            // 4. 쿼리 실행
            String sql = "update member " +
                    "set password = ? " +
                    "where member_type = ? and user_id = ?;";

            // 3. prepareStatement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passwordValue);
            preparedStatement.setString(2, memberTypeVale);
            preparedStatement.setString(3, userIdValue);

            int affeced = preparedStatement.executeUpdate();

            if (affeced > 0) {
                System.out.println("수정 성공");
            } else {
                System.out.println("수정 실패");
            }

            // 5. 결과 실행

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    //회원 탈퇴
    public void withdraw(Member member) {
        //5 개
        // 1. ip (doamin)
        // 2. port
        // 3. 계정
        // 4. 패스워드
        // 5. 인스턴스

        String url = "jdbc:mariadb://localhost:3306/testdb";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";

        // 1. 드라이버 로드
        // 2. 커넥션 객체 생성
        // 3. 스테이트먼트 객체 생성
        // 4. 쿼리 실행
        // 5. 결과 수행
        // 6. 객체 연결 해제(close)


        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet rs = null;

        String memberTypeValue = member.getMemberType();
        String userIdValue = member.getUserId();

        try {
            // 2. 커넥션 객체 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            // 3. 스테이트먼트 객체 생성
//            statement = connection.createStatement();

            // 4. 쿼리 실행
            String sql = "delete " +
                    "from member " +
                    "where member_type = ? and user_id = ?;";

            // 3. prepareStatement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);
            preparedStatement.setString(2, userIdValue);

            int affeced = preparedStatement.executeUpdate();

            if (affeced > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }

            // 5. 결과 실행

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    public Member detail(String memberTypeVale, String userId) {

        Member member = null;
        String url = "jdbc:mariadb://localhost:3306/testdb";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";

        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // 2. 커넥션 객체 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            // 3. 스테이트먼트 객체 생성
//            statement = connection.createStatement();

            // 4. 쿼리 실행
            String sql = "select m.member_type, m.user_id, m.password, m.name, md.mobile_no, md.marketing_yn, md.register_date " +
                    "from member m left join member_detail md on m.member_type = md.member_type and m.user_id = md.user_id " +
                    "where m.member_type = ? and m.user_id = ?;";

            // 3. prepareStatement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeVale);
            preparedStatement.setString(2, userId);

            rs = preparedStatement.executeQuery();


            // 5. 결과 실행
            if (rs.next()) {
                member = new Member(rs.getString("member_type"),
                        rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("mobile_no"),
                        rs.getBoolean("marketing_yn"),
                        rs.getDate("register_date"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return member;
    }
}
