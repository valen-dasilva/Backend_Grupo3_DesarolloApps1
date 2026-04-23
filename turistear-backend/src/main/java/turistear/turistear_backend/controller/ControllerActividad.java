package turistear.turistear_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import turistear.turistear_backend.dto.ActividadDTO;
import turistear.turistear_backend.enumerable.CategoriaActividad;
import turistear.turistear_backend.model.Actividad;
import turistear.turistear_backend.service.ServiceActividad;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ControllerActividad {

    private final ServiceActividad serviceActividad;

    public ControllerActividad(ServiceActividad serviceActividad) {
        this.serviceActividad = serviceActividad;
    }

    @GetMapping("/actividades/{idActividad}")
    public ActividadDTO getActividad(@PathVariable Long idActividad) {
        return serviceActividad.getActividad(idActividad);
    }

    @PostMapping("/actividades/publicar")
    public ActividadDTO subirActividad(@RequestBody Actividad actividad) {
        return serviceActividad.subirActividad(actividad);
    }

    @PatchMapping("/actividades/{idActividad}/tags")
    public Set<CategoriaActividad> agregarTag(
            @PathVariable Long idActividad,
            @RequestParam CategoriaActividad etiqueta) {
        return serviceActividad.agregarTag(etiqueta, idActividad);
    }
}
