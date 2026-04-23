package turistear.turistear_backend.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import turistear.turistear_backend.dto.ActividadDTO;
import turistear.turistear_backend.enumerable.CategoriaActividad;
import turistear.turistear_backend.model.Actividad;
import turistear.turistear_backend.repository.ActividadRepository;

import java.util.Set;

@Service
public class ServiceActividad {

    private final ActividadRepository repositorioActividad;

    public ServiceActividad(ActividadRepository repositorioActividad){
        this.repositorioActividad = repositorioActividad;
    }

    @Transactional
    public ActividadDTO getActividad(Long id_actividad){
        Actividad actividad = repositorioActividad.findById(id_actividad)
                .orElseThrow(() -> new RuntimeException("no se encontro la actividad"));
        return ActividadDTO.from(actividad);
    }

    @Transactional
    public ActividadDTO subirActividad(Actividad actividad){
        repositorioActividad.save(actividad);
        return ActividadDTO.from(actividad);
    }

    @Transactional
    public Set<CategoriaActividad> agregarTag(CategoriaActividad etiqueta, Long id_actividad){
        Actividad actividad = repositorioActividad.findById(id_actividad)
                .orElseThrow(() -> new RuntimeException("no se encontro la actividad"));

        actividad.getTags().add(etiqueta);
        repositorioActividad.save(actividad);
        return actividad.getTags();
    }
}
