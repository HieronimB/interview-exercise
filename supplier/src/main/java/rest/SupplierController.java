package rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import source.DataSource;

import java.util.Collection;

@RestController()
public class SupplierController {
    private DataSource<String> dataSource;

    public SupplierController(DataSource<String> dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/source/json/{size}")
    public Collection<String> generateJson(@PathVariable int size) {
        return dataSource.fetch(size);
    }
}
