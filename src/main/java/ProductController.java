import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setQuantity(newProduct.getQuantity());
                    product.setCategory(newProduct.getCategory()); // Updating category
                    return repository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
