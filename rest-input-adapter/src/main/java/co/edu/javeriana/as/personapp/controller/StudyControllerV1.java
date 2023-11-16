package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.StudyInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.estudio.DeleteEstudioRequest;
import co.edu.javeriana.as.personapp.model.request.estudio.EstudioRequest;
import co.edu.javeriana.as.personapp.model.request.estudio.GetEstudioRequest;
import co.edu.javeriana.as.personapp.model.response.estudio.EstudioResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estudio")
@Validated
public class StudyControllerV1 {
    @Autowired
    private StudyInputAdapterRest studyInputAdapterRest;

    @GetMapping("/{database}")
    public List<EstudioResponse> getAll(@PathParam("database") String db){
        return studyInputAdapterRest.getAll(db);
    }

    @GetMapping
    public EstudioResponse getOne(@RequestBody GetEstudioRequest request){
        return studyInputAdapterRest.getOne(request);
    }

    @PostMapping
    public EstudioResponse create(@RequestBody EstudioRequest request){
        return studyInputAdapterRest.create(request);
    }

    @PutMapping
    public EstudioResponse edit(@RequestBody EstudioRequest request){
        return studyInputAdapterRest.edit(request);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody DeleteEstudioRequest request){
        return studyInputAdapterRest.drop(request) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

}
