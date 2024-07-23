package org.example;

import java.util.List;

public class UserMain {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        // 이름이 출력되는 회원 조회 메서드
        userDao.getAllUsersWithName();

        // 회원 삭제
        userDao.deleteUser(5);

        // 회원 수정
        userDao.updateUser(5, "tetz3", "1234");


        // 전체 회원 조회 메서드
        List<UserVo> users =  userDao.getAllUsers();

        for (UserVo user : users) {
            System.out.print("ID : " + user.getId() + " / " + "email : " + user.getEmail() + " / " + "password : " + user.getPassword() + "\n");
        }



    }
}
