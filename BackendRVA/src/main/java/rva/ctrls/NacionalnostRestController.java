package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Nacionalnost;
import rva.reps.NacionalnostRepository;

@Api (tags = {"Nacionalnost Rest Controller"})
@RestController
public class NacionalnostRestController {
	
	@Autowired
	private NacionalnostRepository nacionalnostRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Get all metoda vraca sve nacionalnosti")
	@GetMapping("/nacionalnost")
	public Collection<Nacionalnost> getNacionalnosti(){
		return nacionalnostRepository.findAll();
	}
	
	@ApiOperation(value = "Get metoda vraca nacionalnost na osnovu proslednjenog id-a")
	@GetMapping("/nacionalnost/{id}")
	public Nacionalnost getNacionalnost (@PathVariable Integer id) {
		return nacionalnostRepository.getOne(id);
	}
	
	@ApiOperation(value = "Get metoda vraca nacionalnost na osnovu proslednjenog naziva")
	@GetMapping("/nacionalnostNaziv/{naziv}")
	public Collection<Nacionalnost> getByNaziv(@PathVariable String naziv) {
		return nacionalnostRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Delete metoda brise nacionalnost sa prosledjenim id-em")
	@DeleteMapping("/nacionalnost/{id}")
	public ResponseEntity<HttpStatus> deleteNacionalnost(@PathVariable Integer id){
		if (nacionalnostRepository.existsById(id)) {
			nacionalnostRepository.deleteById(id);
			
			if(id == -100) 
				jdbcTemplate.execute("INSERT INTO \"nacionalnost\"(\"id\", \"naziv\", \"skracenica\")\r\n" + 
						"VALUES(-100, 'Mongolac', 'mng');");
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Post metoda dodaje nacionalnost")
	@PostMapping ("/nacionalnost")
	public ResponseEntity<HttpStatus> addNacionalnost(@RequestBody Nacionalnost nacionalnost){

		nacionalnostRepository.save(nacionalnost);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Put metoda modifikuje postojecu nacionalnost")
	@PutMapping ("/nacionalnost")
	public ResponseEntity<HttpStatus> updateNacionalnost(@RequestBody Nacionalnost nacionalnost){
		if (nacionalnostRepository.existsById(nacionalnost.getId())) 
			nacionalnostRepository.save(nacionalnost);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

}
