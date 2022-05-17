package salesianos.triana.dam.cotidie.organizacion.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import salesianos.triana.dam.cotidie.notificacion.model.Notificacion;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Notificacion")
public class Organizacion {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;

    private String nombre;

    @OneToMany(mappedBy = "organizacion", fetch = FetchType.LAZY)
    private List<Usuario> miembros = new ArrayList<>();
}
