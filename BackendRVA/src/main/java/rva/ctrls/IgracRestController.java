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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Igrac;
import rva.reps.IgracRepository;

@Api (tags = {"Igrac Rest Controller"})
@RestController
public class IgracRestController {
	
	@Autowired
	private IgracRepository igracRepository;
	
	@GetMapping("/")
	public String opet(){
		return "Hello";
	}
	
	@ApiOperation(value = "Get metoda vraca sve igrace")
	@GetMapping("/igrac")
	public Collection<Igrac> getIgraci(){
		return igracRepository.findAll();
	}
	
	@ApiOperation(value = "Get metoda vraca igraca sa prosledjenim id-em")
	@GetMapping("/igrac/{id}")
	public Igrac getIgrac (@PathVariable Integer id) {
		return igracRepository.getOne(id);
	}
	
	@ApiOperation(value = "Get metoda vraca igraca sa prosledjenim prezimenom")
	@GetMapping("/igracPrezime/{prezime}")
	public Collection<Igrac> getByPrezime(@PathVariable String prezime) {
		return igracRepository.findByPrezimeContainingIgnoreCase(prezime);
	}
	
	@ApiOperation(value = "Delete metoda brise igraca sa prosledjenim id-em")
	@DeleteMapping("/igrac/{id}")
	public ResponseEntity<HttpStatus> deleteIgraca(@PathVariable Integer id){
		if (igracRepository.existsById(id)) {
			igracRepository.deleteById(id);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Post metoda dodaje igraca")
	@PostMapping ("/igrac")
	public ResponseEntity<HttpStatus> addIgraca(@RequestBody Igrac igrac){

		igracRepository.save(igrac);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Put metoda modifikuje igraca")
	@PutMapping ("/igrac")
	public ResponseEntity<HttpStatus> updateIgraca(@RequestBody Igrac igrac){
		if (igracRepository.existsById(igrac.getId()))
			igracRepository.save(igrac);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);


	}

}
