package rva.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the liga database table.
 * 
 */
@Entity
@NamedQuery(name="Liga.findAll", query="SELECT l FROM Liga l")
public class Liga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LIGA_ID_GENERATOR", sequenceName="LIGA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIGA_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String oznaka;

	//bi-directional many-to-one association to Tim
	@JsonIgnore
	@OneToMany(mappedBy="ligaBean")
	private List<Tim> tims;

	public Liga() {
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

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public List<Tim> getTims() {
		return this.tims;
	}

	public void setTims(List<Tim> tims) {
		this.tims = tims;
	}

	public Tim addTim(Tim tim) {
		getTims().add(tim);
		tim.setLigaBean(this);

		return tim;
	}

	public Tim removeTim(Tim tim) {
		getTims().remove(tim);
		tim.setLigaBean(null);

		return tim;
	}

}