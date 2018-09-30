package fritz.test.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "specialty")
public class Speciality extends BasedEntity {

	@Column(name = "description")
	private String description;

}
