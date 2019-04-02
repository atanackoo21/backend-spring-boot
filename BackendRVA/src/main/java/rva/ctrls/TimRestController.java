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
import rva.jpa.Tim;
import rva.reps.TimRepository;

@Api (tags = {"Tim Rest Controller"})
@RestController
public class TimRestController {
	
	@Autowired
	private TimRepository timRepository; 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Get all metoda vraca sve timove")
	@GetMapping("/tim")
	public Collection<Tim> getIgraci(){
		return timRepository.findAll();
	}
	
	@ApiOperation(value = "Get metoda vraca tim sa prosledjenim id-em")
	@GetMapping("/tim/{id}")
	public Tim getTim (@PathVariable Integer id) {
		return timRepository.getOne(id);
	}
	
	@ApiOperation(value = "Get metoda vraca tim sa prosledjenim nazivom")
	@GetMapping("/timNaziv/{naziv}")
	public Collection<Tim> getByNaziv(@PathVariable String naziv) {
		return timRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Delete metoda brise tim sa prosledjenim id-em")
	@DeleteMapping("/tim/{id}")
	public ResponseEntity<HttpStatus> deleteTim(@PathVariable Integer id){
		if (timRepository.existsById(id)) {
			timRepository.deleteById(id);
			
			if(id == -100) 
				jdbcTemplate.execute("INSERT INTO \"tim\"(\"id\", \"naziv\", \"osnovan\", \"sediste\", \"liga\") \r\n" + 
						"VALUES (-100, 'Charlton', '02/9/1886', 'Charlton', '-99');");
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Post metoda dodaje tim")
	@PostMapping ("/tim")
	public ResponseEntity<HttpStatus> addTim(@RequestBody Tim tim){
		timRepository.save(tim);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Put metoda modifikuje postojeci tim")
	@PutMapping ("/tim")
	public ResponseEntity<HttpStatus> updateTim(@RequestBody Tim tim){
		if (timRepository.existsById(tim.getId())) 
			timRepository.save(tim);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

}
