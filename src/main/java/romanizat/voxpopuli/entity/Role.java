package romanizat.voxpopuli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.*;
import java.util.*;
import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "role")
@RequiredArgsConstructor
public class Role extends Auditable {
    @Id
    @Column(name = "id_role")
    private Integer id;
    @Column(name = "role")
    private String role;
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}