package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.ProfesionInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.profesion.DeleteProfesionRequest;
import co.edu.javeriana.as.personapp.model.request.profesion.GetProfesionRequest;
import co.edu.javeriana.as.personapp.model.request.profesion.ProfesionRequest;
import co.edu.javeriana.as.personapp.model.response.estudio.EstudioResponse;
import co.edu.javeriana.as.personapp.model.response.profesion.ProfesionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/profesion")
@Validated
public class ProfessionControllerV1 {
    @Autowired
    private ProfesionInputAdapterRest profesionInputAdapterRest;

    @GetMapping("/{database}")
    public Iterable<ProfesionResponse> getAll(@PathVariable String database){
        return profesionInputAdapterRest.getAll(database);
    }

    @GetMapping
    public ProfesionResponse getOne(@Valid @RequestBody GetProfesionRequest request){
        return profesionInputAdapterRest.getOne(request);
    }

    @PostMapping
    public ProfesionResponse create(@Valid @RequestBody ProfesionRequest request){
        return profesionInputAdapterRest.create(request);
    }

    @PutMapping
    public ProfesionResponse edit(@Valid @RequestBody ProfesionRequest request){
        return profesionInputAdapterRest.edit(request);
    }

    @DeleteMapping
    public ResponseEntity<Object> drop(@Valid @RequestBody DeleteProfesionRequest request){
        return profesionInputAdapterRest.drop(request) ? ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }
}
