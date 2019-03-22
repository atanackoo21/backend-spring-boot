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

import rva.jpa.Liga;
import rva.reps.LigaRepository;

@RestController
public class LigaRestController {

	@Autowired
	private LigaRepository ligaRepository;
	
	@GetMapping("/liga")
	public Collection<Liga> getLige(){
		return ligaRepository.findAll();
	}
	
	@GetMapping("/ligaId/{id}")
	public Liga getLiga (@PathVariable Integer id) {
		return ligaRepository.getOne(id);
	}
	
	@GetMapping("/liga/{naziv}")
	public Collection<Liga> getByNaziv(@PathVariable String naziv) {
		return ligaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@DeleteMapping("/liga/{id}")
	public ResponseEntity<HttpStatus> deleteLiga(@PathVariable Integer id){
		if (ligaRepository.existsById(id)) {
			ligaRepository.deleteById(id);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping ("/liga/{liga}")
	public ResponseEntity<HttpStatus> addLiga(@PathVariable Liga liga){
		if (ligaRepository.existsById(liga.getId())) {
			return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
		}
		ligaRepository.save(liga);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PutMapping ("/liga/{liga}")
	public ResponseEntity<HttpStatus> updateLiga(@PathVariable Liga liga){
		if (ligaRepository.existsById(liga.getId())) {
			ligaRepository.save(liga);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);

	}
}
