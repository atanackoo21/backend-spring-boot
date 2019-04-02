package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Nacionalnost;
import rva.reps.NacionalnostRepository;


@RestController
public class NacionalnostRestController {
	
	@Autowired
	private NacionalnostRepository nacionalnostRepository;
	
	@GetMapping("/nacionalnost")
	public Collection<Nacionalnost> getNacionalnosti(){
		return nacionalnostRepository.findAll();
	}
	
	@GetMapping("/nacionalnost/{id}")
	public Nacionalnost getNacionalnost (@PathVariable Integer id) {
		return nacionalnostRepository.getOne(id);
	}
	
	@GetMapping("/nacionalnostNaziv/{naziv}")
	public Collection<Nacionalnost> getByNaziv(@PathVariable String naziv) {
		return nacionalnostRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("/nacionalnost/{id}")
	public ResponseEntity<HttpStatus> deleteNacionalnost(@PathVariable Integer id){
		if (nacionalnostRepository.existsById(id)) {
			nacionalnostRepository.deleteById(id);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping ("/nacionalnost")
	public ResponseEntity<HttpStatus> addNacionalnost(@RequestBody Nacionalnost nacionalnost){

		nacionalnostRepository.save(nacionalnost);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PutMapping ("/nacionalnost")
	public ResponseEntity<HttpStatus> updateNacionalnost(@RequestBody Nacionalnost nacionalnost){
		if (nacionalnostRepository.existsById(nacionalnost.getId())) 
			nacionalnostRepository.save(nacionalnost);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);

	}

}
