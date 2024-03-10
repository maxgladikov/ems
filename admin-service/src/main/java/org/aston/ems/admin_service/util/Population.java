package org.aston.ems.admin_service.util;

import org.aston.ems.admin_service.dto.UserReqDto;

import java.util.List;

public class Population {

    private static String [] students = {"STUDENT"};
    private static String [] teachers = {"TEACHER"};
    private static String [] admins = {"ADMIN"};
    public final static List<UserReqDto> USERS = List.of(
        new UserReqDto("Abdullah Vargas","password",students),
        new UserReqDto("Darren Hughes","password",students),
        new UserReqDto("Kiara Walter","password",students),
        new UserReqDto("Amir Salinas","password",students),
        new UserReqDto("Dawn Curry","password",students),
        new UserReqDto("Huda Vega","password",students),
        new UserReqDto("Hamza Rose","password",students),
        new UserReqDto("Alma Calhoun","password",students),
        new UserReqDto("Lewys Wall","password",students),
        new UserReqDto("Charis Patterson","password",students),
        new UserReqDto("Calum Ayers","password",students),
        new UserReqDto("Herman Todd","password",students),
        new UserReqDto("Tomasz Dejesus","password",students),
        new UserReqDto("Clayton Duncan","password",students),
        new UserReqDto("Kurt Howard","password",students),
        new UserReqDto("Nate Robles","password",students),
        new UserReqDto("Sadia Yang","password",students),
        new UserReqDto("Sandra Padilla","password",students),
        new UserReqDto("Annalise England","password",students),
        new UserReqDto("Raees Cannon","password",students),
        new UserReqDto("Summer Perry","password",students),
        new UserReqDto("Tamzin Sears","password",students),
        new UserReqDto("Lacie Cooper","password",students),
        new UserReqDto("Sumaiya Benton","password",students),
        new UserReqDto("Lottie Walker","password",students),
        new UserReqDto("Jaden Castro","password",students),
        new UserReqDto("Rayhan Pham","password",students),
        new UserReqDto("Lennox Page","password",students),
        new UserReqDto("Bertie Molina","password",students),
        new UserReqDto("Joe Gregory","password",students),
        new UserReqDto("Corey Hardy","password",teachers),
        new UserReqDto("Laurence Lewis","password",teachers),
        new UserReqDto("Maxwell Downs","password",teachers),
        new UserReqDto("admin","admin",admins)
    );
}
