package com.example.amigospringsecurity.security;
import static com.example.amigospringsecurity.security.ApplicationUserPermission.*;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;



public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}





//public enum ApplicationUserRole {
//    STUDENT(Sets.newHashSet()),
//    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)), //set of permissions
//    ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));
//
//   private Set<ApplicationUserPermission> permissions;
//
//    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<ApplicationUserPermission> getPermissions() {
//        return permissions;
//    }
//
//    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
//        Set<SimpleGrantedAuthority> permissions =  getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//
//        permissions.add(new SimpleGrantedAuthority("ROLE_"+ this
//        .name()));
//        return  permissions;
//
//    }
//}
