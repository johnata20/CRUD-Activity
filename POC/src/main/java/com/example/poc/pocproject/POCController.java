package com.example.poc.pocproject;

import com.example.poc.PocApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/POC")
public class POCController {

    private final POCService pocService;

    @Autowired
    public POCController(POCService pocService) {
        this.pocService = pocService;
    }

    @GetMapping
    public List<POCProject> getPOC(){
        return pocService.getPOC();
    }
    @PostMapping
    public void registerNewPOCProject(@RequestBody POCProject pocProject){
        pocService.addNewPOCProject(pocProject);
    }
    @DeleteMapping(path = "{POCProjectId}")
    public void deletePOCProject(@PathVariable("POCProjectId") Long POCProjectid) {
        pocService.deletePOCProject(POCProjectid);
    }
        @PutMapping
                (path = "{POCProjectId}")
                public void updatePOCProject(
                        @PathVariable("POCProjectId") Long POCProjectid,
                        @RequestParam(required = false) String name,
                        @RequestParam(required = false) String email){
                pocService.updatePOCProject(POCProjectid,name, email);
        }
    }

