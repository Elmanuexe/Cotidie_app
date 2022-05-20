package salesianos.triana.dam.cotidie.planificacion.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import salesianos.triana.dam.cotidie.tipoActividad.model.TipoActividad;
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
@Table(name = "Planificacion")
public class PlanificacionMensual {

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

    @OneToMany(mappedBy = "planificacion", fetch = FetchType.LAZY)
    private List<TipoActividad> dias = new ArrayList<>();

    @ManyToOne
    private Usuario usuario;

    //HELPERS USUARIO//
    public void addInmobiliariaToUser(Usuario u){
        this.usuario=u;
        u.getPlanificacionMensual().add(this);
    }

    public void removeInmobiliariaFromUser(Usuario u){
        this.usuario=u;
        u.getPlanificacionMensual().remove(this);
    }
}
