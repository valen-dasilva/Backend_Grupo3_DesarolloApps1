package turistear.turistear_backend.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "itinerario_items")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"itinerario", "actividad"})
public class ItemItinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "itinerario_id")
    private Itinerario itinerario;

    @ManyToOne
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;

    private LocalDate fecha;
    private LocalTime hora;

    public Actividad getActividad() {
        return actividad;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }
}

