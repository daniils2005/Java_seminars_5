package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//git checkout master
//git pull origin master
//gir branch 
//git merge ritvars
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "MyAuthorityTable")
@Entity
public class MyAuthority {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Ida")
	@Id //ka primara atslega
	@GeneratedValue(strategy = GenerationType.AUTO)//tiks glabat DB automatiska pec ato increament algoritma
	private long ida;
	
	@Column(name = "Title")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z_]{4,10}")
	private String title;
	
	@OneToMany(mappedBy =  "authority")
	@ToString.Exclude
	private Collection<MyUser> users = new ArrayList<>();
	
	public MyAuthority(String title) {
		setTitle(title);
	}
	
	
	
	
}