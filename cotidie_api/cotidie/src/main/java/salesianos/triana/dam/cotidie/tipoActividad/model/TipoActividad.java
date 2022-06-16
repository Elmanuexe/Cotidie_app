package salesianos.triana.dam.cotidie.tipoActividad.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import salesianos.triana.dam.cotidie.planificacion.model.PlanificacionMensual;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TipoActividad")
public class TipoActividad {

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

    private Tipo tipo;
    private LocalDate fecha;
    private String descripcion;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFIn;
    private Boolean todoElDia;

    @ManyToOne
    private PlanificacionMensual planificacion;

    @Override
    public String toString() {
        return "TipoActividad{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", horaInicio=" + horaInicio +
                ", horaFIn=" + horaFIn +
                ", todoElDia=" + todoElDia +
                '}';
    }

    //HELPERS PLANIFICACION//
    public void addDiaToPlanificacion(PlanificacionMensual u){
        this.planificacion=u;
        u.getEventos().add(this);
    }

    public void removeDiaFromPlanificacion(PlanificacionMensual u){
        this.planificacion=u;
        u.getEventos().remove(this);
    }
}
