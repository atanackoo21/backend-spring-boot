package rva.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the nacionalnost database table.
 * 
 */
@Entity
@NamedQuery(name="Nacionalnost.findAll", query="SELECT n FROM Nacionalnost n")
public class Nacionalnost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NACIONALNOST_ID_GENERATOR", sequenceName="NACIONALNOST_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NACIONALNOST_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String skracenica;

	//bi-directional many-to-one association to Igrac
	@JsonIgnore
	@OneToMany(mappedBy="nacionalnostBean")
	private List<Igrac> igracs;

	public Nacionalnost() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSkracenica() {
		return this.skracenica;
	}

	public void setSkracenica(String skracenica) {
		this.skracenica = skracenica;
	}

	public List<Igrac> getIgracs() {
		return this.igracs;
	}

	public void setIgracs(List<Igrac> igracs) {
		this.igracs = igracs;
	}

	public Igrac addIgrac(Igrac igrac) {
		getIgracs().add(igrac);
		igrac.setNacionalnostBean(this);

		return igrac;
	}

	public Igrac removeIgrac(Igrac igrac) {
		getIgracs().remove(igrac);
		igrac.setNacionalnostBean(null);

		return igrac;
	}

}