package br.com.saves.hrworker.resources;

import br.com.saves.hrworker.entities.Worker;
import br.com.saves.hrworker.repositories.WorkerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
@Slf4j
public class WorkerResource {

    @Value("${test.config}")
    private String testConfig;

    @Autowired
    private Environment env;

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> list = workerRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs() {
        log.info( "CONFIG: " + env.getProperty("test.config"));
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {

        /*try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        log.info("PORT = "+ env.getProperty("local.server.port"));
        Worker worker = workerRepository.findById(id).get();
        return ResponseEntity.ok(worker);
    }
}
