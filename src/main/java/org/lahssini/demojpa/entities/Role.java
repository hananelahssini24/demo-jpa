package org.lahssini.demojpa.entities;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Data
@Table(name = "ROLES")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Description")
    private String desc;
    @Column(length = 20,unique = true)
    private String roleName;
    @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "role_id"),
    //     	inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
    private List<User> users=new ArrayList<>();

}
