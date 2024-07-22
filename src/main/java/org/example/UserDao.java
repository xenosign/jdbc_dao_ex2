package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    // 회원 추가 메서드
    public void addUser(UserVo newUser) {
        try {
            // 1. Driver 커넥터 설정
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            System.out.println("1. 드라이버 설정 OK");

            // 2. DB 연결
            String url = "jdbc:mysql://localhost:3306/user_ex";
            String id = "root";
            String password = "1234";
            Connection conn = DriverManager.getConnection(url, id, password);
            System.out.println("2. DB연결 OK");

            // 3. SQL 문 생성
            String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getEmail());
            pstmt.setString(2, newUser.getPassword());
            System.out.println("3. SQL문 생성 OK");

            // 4. SQL 문 전송
            int affetedRows = pstmt.executeUpdate();
            if (affetedRows > 0) {
                System.out.println("회원 추가 성공!");
            } else {
                System.out.println("회원 추가 실패");
            }

            // 5. 자원 해제
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 모든 회원 정보를 조회하는 메서드
    public List<UserVo> getAllUsers() {
        // 리턴할 데이터를 미리 선언! try/catch 문이 종료 되고 리턴이 되어야 하므로
        // Scope 의 개념으로 try/catch 문 밖에 존재해야 함
        List<UserVo> userList = new ArrayList<>();

        try {
            // 1. Driver 커넥터 설정
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            System.out.println("1. 드라이버 설정 OK");

            // 2. DB 연결
            String url = "jdbc:mysql://localhost:3306/user_ex";
            String id = "root";
            String password = "1234";
            Connection conn = DriverManager.getConnection(url, id, password);
            System.out.println("2. DB연결 OK");

            // 3. SQL 문 생성
            String sql = "SELECT id, email, password FROM users";
            Statement stmt = conn.createStatement();
            System.out.println("3. SQL문 생성 OK");

            // 4. SQL 문 전송
            ResultSet rs = stmt.executeQuery(sql);
            // 결과 데이터를 전부 순회하는 while 문
            while (rs.next()) {
                int userid = rs.getInt("id");
                String email = rs.getString("email");
                String userpassword = rs.getString("password");
                
                // 결과 데이터를 바탕으로 회원 정보 객체(=UserVo)를 만들고 해당 객체를 List 에 추가
                UserVo user = new UserVo(userid, email, userpassword);                 
                userList.add(user);
            }
            
            // 5. 자원 해제
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 6. 결과 리턴
        // 데이터가 전부 추가된 리스트를 리턴!
        // 통신이 잘못되면 try/catch 구문이 정상적으로 실행이 안되므로 빈 리스트가 리턴
        return userList;
    }

    // 회원 정보를 수정하는 메서드
    public void updateUser(int userid, String newEmail, String newPassword) {
        try {
            // 1. Driver 커넥터 설정
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            System.out.println("1. 드라이버 설정 OK");

            // 2. DB 연결
            String url = "jdbc:mysql://localhost:3306/user_ex";
            String id = "root";
            String password = "1234";
            Connection conn = DriverManager.getConnection(url, id, password);
            System.out.println("2. DB연결 OK");

            // 3. SQL 문 생성
            String sql = "UPDATE users SET email = ?, password = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newEmail);
            pstmt.setString(2, newPassword);
            pstmt.setInt(3, userid);
            System.out.println("3. SQL문 생성 OK");

            // 4. SQL 문 전송
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("회원 정보 수정 성공!");
            } else {
                System.out.println("회원 정보 수정 실패");
            }

            // 5. 자원해제
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 회원 정보를 삭제하는 메서드
    public void deleteUser(int userid) {
        try {
            // 1. Driver 커넥터 설정
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            System.out.println("1. 드라이버 설정 OK");

            // 2. DB 연결
            String url = "jdbc:mysql://localhost:3306/user_ex";
            String id = "root";
            String password = "1234";
            Connection conn = DriverManager.getConnection(url, id, password);
            System.out.println("2. DB연결 OK");

            // 3. SQL 문 생성
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userid);
            System.out.println("3. SQL문 생성 OK");

            // 4. SQL 문 전송
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("회원 삭제 성공!");
            } else {
                System.out.println("회원 삭제 실패");
            }

            // 5. 자원 해제
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 테이블을 합친 뒤, 회원의 이름 정보까지 전부 출력하는 메서드
    public void getAllUsersWithName() {
        try {
            // 1. Driver 커넥터 설정
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            System.out.println("1. 드라이버 설정 OK");

            // 2. DB 연결
            String url = "jdbc:mysql://localhost:3306/user_ex";
            String id = "root";
            String password = "1234";
            Connection conn = DriverManager.getConnection(url, id, password);
            System.out.println("2. DB연결 OK");

            // 3. SQL 문 생성
            String sql = "SELECT users.id, users.email, users.password, user_info.name " +
                    "FROM users " +
                    "JOIN user_info ON users.id = user_info.id";
            Statement stmt = conn.createStatement();
            System.out.println("3. SQL문 생성 OK");

            // 4. SQL 문 전송
            ResultSet rs = stmt.executeQuery(sql);
            // 결과 데이터를 전부 순회하는 while 문
            while (rs.next()) {
                int userid = rs.getInt("id");
                String email = rs.getString("email");
                String userpassword = rs.getString("password");
                String name = rs.getString("name");

                System.out.printf("ID: %d, Email: %s, Password: %s, Name: %s%n", userid, email, userpassword, name);
            }

            // 5. 자원 해제
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
