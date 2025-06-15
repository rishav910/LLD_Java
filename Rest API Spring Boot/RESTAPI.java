package com.rishav.store;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Youtube: Engineering Digest -> https://www.youtube.com/watch?v=rxT5RFYxjSg&ab_channel=EngineeringDigest
/*     If @RequestMapping("/about") is at top of class annotation:
            @GetMapping           -> localhost:8080/about
            @GetMapping("/hello") -> localhost:8080/about/hello

       Else if - No @RequestMapping at class top
            @GetMapping            -> Build error
            @GetMapping("/hello")  -> localhost:8080/hello

       Q. How to get data entered by client? PUT & POST
       Ans: @PostMapping
            public void func (@RequestBody Product p)
         -> 'Product' type you can decide, assigned by parsing JSON and absorbing it in POJO

       Q. How to get values from URL to fetch some data? GET, PUT, POST, DELETE
            localhost:8080/about/2     -> Need product details with id = 2 (in url)
            How to get '2'?
       Ans: PutMapping("/{id}")
            public void func (@PathVariable int id, @RequestBody Product p)
       -> {id} from URL is assigned to variable 'id'. (If type conversion is possible)
       -> @RequestBody assigns JSON to Product 'p'.


       @RequestParam
       Used to search from endpoint queries
       looks like: /about/search?par1=hello&par2=90
       @GetMapping
       void method (@RequestParam String par1, @RequestParam(value="par2") int val) {}
       Without @RequestParam(value) -> name should match: par1 = par1 (in URL)
       With @RequestParam(value="par2") -> next variable can be named according to us
 */
@RestController
@RequestMapping("/about")
public class RESTAPI {

    private List<Product> products = new ArrayList<>();

    // GET - Get resource
    @GetMapping
    public List<Product> getAllProducts () {
        return products;
    }

    // Get - to search product using @RequestParam
    // http://localhost:8080/about/search?name=iPhone&price=1700
    @GetMapping("/search")
    public Product searchProduct (@RequestParam String name, @RequestParam(value="price") int pr) {
        for (Product product: products) {
            if (product.getName().equals(name) && pr == product.getPrice()) {
                return product;
            }
        }
        return null;
    }

    // POST - Add a new resource
    @PostMapping
    public void addProduct (@RequestBody Product p) {
        System.out.println("hit here");
        products.add(p);
    }

    // PUT - Update a resource
    @PutMapping("/{id}")
    public void updateProduct (@RequestBody Product p, @PathVariable int id) {
        for (Product product: products) {
            if (product.getId() == id) {
                product.setId(p.getId());
                product.setName(p.getName());
                product.setPrice(p.getPrice());
                return;
            }
        }
    }

    // DELETE - delete a resource
    @DeleteMapping("/{name}")
    public void deleteProduct (@PathVariable String name) {
        int index = 0;
        for (Product product: products) {
            if (product.getName().equals(name)) {
                break;
            }
            index++;
        }
        if (index == products.size()) return;

        products.remove(index);
    }
}
