package com.cydeo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final User user;


    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList=new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(this.user.getRole().getDescription());
        authorityList.add(authority);
        return authorityList;
    }


    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    public String getFullNameForProfile(){
        return this.user.getFirstName()+" "+this.user.getLastName();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }


    public Long getId(){
        return this.user.getId();
    }
}
