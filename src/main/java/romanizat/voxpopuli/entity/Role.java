package romanizat.voxpopuli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@NoArgsConstructor
@Table(name = "role")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Role extends Auditable implements GrantedAuthority {
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_role")
	private Integer id;
	@Column(name = "role")
	private String role;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_user"))
	private List<User> users;
	@Override
    public String getAuthority() {
        return String.format("role_%s", getRole())
                .toUpperCase(Locale.ROOT);
    }


}