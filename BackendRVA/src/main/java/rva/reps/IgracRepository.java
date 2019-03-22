package rva.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Igrac;

public interface IgracRepository extends JpaRepository <Igrac, Integer>{
	
	Collection <Igrac> findByPrezimeContainingIgnoreCase(String prezime);

}
