package com.example.spcurrency;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Service
//@Data
public class UsersService {

//    @Autowired
    private final UsersRepository usersRepository;

//    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
//    private DataSource dataSource;
//
//    public UsersService(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    public UsersService(){
//
//    }

    @Transactional(readOnly = true)
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Users findByLogin(String username) {
        return usersRepository.findByLogin(username);
    }

    @Transactional
    public void deleteUsers(List<Long> ids) {
        ids.forEach(id -> {
            Optional<Users> user = usersRepository.findById(id);
            user.ifPresent(u -> {
                if ( ! AppConfig.ADMIN.equals(u.getUsername())) {
                    usersRepository.deleteById(u.getIdUser());
                }
            });
        });
    }

    @Transactional
    public void addUser(String name, String surname,
                        String username,
                        String password,
                        UserRole role) {

        Users user = new Users(name, surname, username, password, role);
        try{
            usersRepository.save(user);
        }
        catch (NullPointerException e){
            System.out.println("OOOOOOPS");
        }
    }

//    @Transactional
//    public void updateUser(String login, String email, String phone) {
//        Users user = usersRepository.findByUsername(login);
//        if (user == null)
//            return;
//
//        user.setEmail(email);
//        user.setPhone(phone);
//
//        userRepository.save(user);
//    }
//    public Users addUser(String name, String password, String surname, String username, UserRole role) {
//        Users user = new Users(name, password, surname, username, role);
//        return usersRepository.save(user);
//    }
//
//    public static Users findByUsername(String username) {
//        return usersRepository.findByUsername(username);
//    }
}
