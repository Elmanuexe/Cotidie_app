package salesianos.triana.dam.cotidie.notificacion.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Notificacion")
public class Notificacion {

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

    private String mensaje;


    @ManyToOne
    private Usuario usuario;


    //HELPERS USUARIO//
    public void addInmobiliariaToUser(Usuario u){
        this.usuario=u;
        u.getNotificaciones().add(this);
    }

    public void removeInmobiliariaFromUser(Usuario u){
        this.usuario=u;
        u.getNotificaciones().remove(this);
    }
}
