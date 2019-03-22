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
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Tim;
import rva.reps.TimRepository;

@RestController
public class TimRestController {
	
	@Autowired
	private TimRepository timRepository; 
	
	@GetMapping("/tim")
	public Collection<Tim> getIgraci(){
		return timRepository.findAll();
	}
	
	@GetMapping("/timId/{id}")
	public Tim getTim (@PathVariable Integer id) {
		return timRepository.getOne(id);
	}
	
	@GetMapping("/tim/{naziv}")
	public Collection<Tim> getByNaziv(@PathVariable String naziv) {
		return timRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("/tim/{id}")
	public ResponseEntity<HttpStatus> deleteTim(@PathVariable Integer id){
		if (timRepository.existsById(id)) {
			timRepository.deleteById(id);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping ("/tim/{tim}")
	public ResponseEntity<HttpStatus> addTim(@PathVariable Tim tim){
		if (timRepository.existsById(tim.getId())) {
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
		}
		timRepository.save(tim);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PutMapping ("/tim/{tim}")
	public ResponseEntity<HttpStatus> updateTim(@PathVariable Tim tim){
		if (timRepository.existsById(tim.getId())) {
			timRepository.save(tim);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);

	}

}
