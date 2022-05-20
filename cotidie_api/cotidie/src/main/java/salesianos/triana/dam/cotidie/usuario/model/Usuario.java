package salesianos.triana.dam.cotidie.usuario.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import salesianos.triana.dam.cotidie.ausencia.model.Ausencia;
import salesianos.triana.dam.cotidie.notificacion.model.Notificacion;
import salesianos.triana.dam.cotidie.planificacion.model.PlanificacionMensual;
import salesianos.triana.dam.cotidie.vacacion.model.Vacacion;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Usuario")
public class Usuario implements Serializable, UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;
    private String nombre,apellidos,email,telefono;
    private String fotoPerfil;

    @NaturalId
    private String nick;
    private String password;

    @Builder.Default
    private LocalDateTime fechaUltimaPasswordCambiada=LocalDateTime.now();

    @CreatedDate
    @Builder.Default
    private LocalDateTime fechaCreacion=LocalDateTime.now();
    private LocalDate fechaNaciemiento;

    @Enumerated(EnumType.STRING)
    private Role role;


    //ASOCIACIONES//

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Ausencia> ausencias = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Vacacion> vacaciones = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Notificacion> notificaciones = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<PlanificacionMensual> planificacionMensual = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+Role.USER.name()));
    }

    @Override
    public String getUsername() {
        return nick;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
