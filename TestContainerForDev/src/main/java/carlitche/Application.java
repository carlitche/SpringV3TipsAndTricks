package carlitche;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

@Controller
@ResponseBody
class CustomerHttpController {
   private final CustomerRepository customerRepository;

  CustomerHttpController(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @GetMapping("/customers")
  Iterable<Customer> customers(){
    return this.customerRepository.findAll();
  }
  
  @GetMapping("/customers/{name}")
  Iterable<Customer> byName (@PathVariable String name ){
    return this.customerRepository.findByName(name);
  }

}

interface CustomerRepository extends CrudRepository<Customer, Integer>{

  Iterable<Customer> findByName(String name);

}

@Entity
class Customer {
  @Id Integer id;
  String name;

  public Customer() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
