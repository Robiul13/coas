package com.asl.asl_rms.controller;

import com.asl.asl_rms.configuration.SecurityUtility;
import com.asl.asl_rms.model.Role;
import com.asl.asl_rms.model.User;
import com.asl.asl_rms.service.RoleService;
import com.asl.asl_rms.service.UserService;
import com.asl.asl_rms.util.GlobalMethod;
import org.casbin.jcasbin.main.Enforcer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    List<Role> roleList = new ArrayList<>();
    private Connection connection;
    @Autowired
    private Environment environment;
    @Autowired
    Enforcer enforcer;
    @Autowired
    GlobalMethod globalMethod;

    @RequestMapping(value = {"/adminpanel/user/userlists"}, method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userlist", userService.findActiveUser(true));
        List<String> stringList = getAllFromCasbin(userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        return "/dashboard/user/user_list";
    }

    @RequestMapping(value = {"/adminpanel/user/adduser"}, method = RequestMethod.GET)
    public String addUser(Model model) {
        roleList = roleService.getAll();
        model.addAttribute("rolelist", roleList);
        model.addAttribute("user", new User());
        List<String> stringList = getAllFromCasbin(userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        return "/dashboard/user/add_user";
    }

    @RequestMapping(value = {"/adminpanel/user/usersave"}, method = RequestMethod.POST)
    public String saveUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result){

        if(result.hasErrors()){
        	
            logger.info("Validation Error");
            model.addAttribute("rolelist", roleList);
            model.addAttribute("user", user);
            List<String> stringList = getAllFromCasbin(userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            return "/dashboard/user/add_user";
        }

        User user1 = userService.usernameExist(user.getEmail());
        if(user1 != null){
            logger.info("Username already exist");
            user.setEmail("");
            model.addAttribute("user", user);
            model.addAttribute("rolelist", roleList);
            List<String> stringList = getAllFromCasbin(userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            return "/dashboard/user/add_user";
        }

        for(String id: user.getSelectedRole()){
            Role role = roleList.stream().filter(role1 -> role1.getId().equals(Long.parseLong(id))).findFirst().orElse(null);
            user.getRoles().add(role);
        }
        user.setUserName(user.getEmail());
        user.setActive(true);
        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
        userService.saveUser(user);
        insertToCasbin(user);
        enforcer.loadPolicy();
        return "redirect:/adminpanel/user/userlists";
    }

    @RequestMapping(value = {"/adminpanel/user/useredit/{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable Long id, Model model){
        User user = userService.findOne(id);
        for(Role role: user.getRoles()){ user.getSelectedRole().add(role.getId()+""); }
        roleList = roleService.getAll();
        model.addAttribute("rolelist", roleList);
        model.addAttribute("user", user);
        List<String> stringList = getAllFromCasbin(userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
        model.addAttribute("menulist", stringList);
        model.addAttribute("fullname", globalMethod.getPrincipalFullName());
        return "/dashboard/user/edit_user";
    }

    @RequestMapping(value = {"/adminpanel/user/userupdate/{id}"}, method = RequestMethod.POST)
    public String updateUser(@PathVariable Long id, Model model, @Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            logger.info("Validation Error");
            model.addAttribute("rolelist", roleList);
            model.addAttribute("user", user);
            List<String> stringList = getAllFromCasbin(userService.findByUserName(getPrincipal()).getRoles().iterator().next().getName());
            model.addAttribute("menulist", stringList);
            model.addAttribute("fullname", globalMethod.getPrincipalFullName());
            return "/dashboard/user/edit_user";
        }

        for(String roleId: user.getSelectedRole()){
            Role role = roleList.stream().filter(role1 -> role1.getId().equals(Long.parseLong(roleId))).findFirst().orElse(null);
            user.getRoles().add(role);
        }

        user.setId(id);
        user.setUserName(user.getEmail());
        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));

        userService.saveUser(user);
        deleteFromCasbin(user);
        insertToCasbin(user);
        enforcer.loadPolicy();
        return "redirect:/adminpanel/user/userlists";
    }

    @RequestMapping(value = {"/adminpanel/user/userdelete/{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id, Model model){
        User user = userService.findOne(id);
        user.setActive(false);
        userService.saveUser(user);
        deleteFromCasbin(user);
        enforcer.loadPolicy();
        return "redirect:/adminpanel/user/userlists";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        System.out.println(userName);
        return userName;
    }

    public List<String> getAllFromCasbin(String role){
        List<String> stringList = new ArrayList<>();
        try {
            Class.forName(environment.getRequiredProperty("spring.datasource.driver-class-name"));

            String url = environment.getRequiredProperty("spring.datasource.url");
            String username = environment.getRequiredProperty("spring.datasource.username");
            String password = environment.getRequiredProperty("spring.datasource.password");

            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT v1 from casbin_rule WHERE v0=? AND v2=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role);
            statement.setString(2, "GET");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                stringList.add(resultSet.getString("v1"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    public void insertToCasbin(User user){
        for(Role role: user.getRoles()){
            enforcer.addGroupingPolicy(user.getUsername(),role.getName());
        }
    }

    public void deleteFromCasbin(User user){
        try {
            Class.forName(environment.getRequiredProperty("spring.datasource.driver-class-name"));

            String url = environment.getRequiredProperty("spring.datasource.url");
            String username = environment.getRequiredProperty("spring.datasource.username");
            String password = environment.getRequiredProperty("spring.datasource.password");

            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = null;
            String deleteSql = "DELETE FROM casbin_rule WHERE v0 = ?";
            statement = connection.prepareStatement(deleteSql);
            statement.setString(1,user.getUsername());
            statement.executeUpdate();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
