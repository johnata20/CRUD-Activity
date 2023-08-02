package com.example.poc.pocproject;

import com.example.poc.PocApplication;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class POCService {
    private final POCRepository pocRepository;

    @Autowired
    public POCService(POCRepository pocRepository) {
        this.pocRepository = pocRepository;
    }

    public List<POCProject> getPOC() {
        return pocRepository.findAll();
    }

    public void addNewPOCProject(POCProject pocProject) {
       Optional<POCProject> pocProjectOptional = pocRepository.findPOCProjectByEmail(pocProject.getEmail());
       if (pocProjectOptional.isPresent()){
           throw new IllegalStateException("email take");
       }
        pocRepository.save(pocProject);
    }

    public void deletePOCProject(Long POCProjectid) {
       boolean exists = pocRepository.existsById(POCProjectid);
       if (!exists) {
           throw new IllegalStateException("POCProject with ID" + POCProjectid + "does not exists");
       }
       pocRepository.deleteById(POCProjectid);
    }
    @Transactional
    public void updatePOCProject(Long POCProjectID, String name, String email){
        POCProject pocProject = pocRepository.findById(POCProjectID)
                .orElseThrow(() -> new IllegalStateException("POCProject with Id" + POCProjectID + "does not exists"));

         if(name != null && name.length() > 0 &&
                !Objects.equals(pocProject.getName(), name)) {
            pocProject.setName(name);
        }
        if(email != null && email.length() > 0 &&
                !Objects.equals(pocProject.getEmail(), email)) {
            Optional<POCProject> pocProjectOptional =pocRepository
                    .findPOCProjectByEmail(email);
            if (pocProjectOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            pocProject.setEmail(email);
        }
    }

}

