package rva.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tim database table.
 * 
 */
@Entity
@NamedQuery(name="Tim.findAll", query="SELECT t FROM Tim t")
public class Tim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIM_ID_GENERATOR", sequenceName="TIM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIM_ID_GENERATOR")
	private Integer id;

	private String naziv;

	@Temporal(TemporalType.DATE)
	private Date osnovan;

	private String sediste;

	//bi-directional many-to-one association to Igrac
	@JsonIgnore
	@OneToMany(mappedBy="timBean")
	private List<Igrac> igracs;

	//bi-directional many-to-one association to Liga
	@ManyToOne
	@JoinColumn(name="liga")
	private Liga ligaBean;

	public Tim() {
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

	public Date getOsnovan() {
		return this.osnovan;
	}

	public void setOsnovan(Date osnovan) {
		this.osnovan = osnovan;
	}

	public String getSediste() {
		return this.sediste;
	}

	public void setSediste(String sediste) {
		this.sediste = sediste;
	}

	public List<Igrac> getIgracs() {
		return this.igracs;
	}

	public void setIgracs(List<Igrac> igracs) {
		this.igracs = igracs;
	}

	public Igrac addIgrac(Igrac igrac) {
		getIgracs().add(igrac);
		igrac.setTimBean(this);

		return igrac;
	}

	public Igrac removeIgrac(Igrac igrac) {
		getIgracs().remove(igrac);
		igrac.setTimBean(null);

		return igrac;
	}

	public Liga getLigaBean() {
		return this.ligaBean;
	}

	public void setLigaBean(Liga ligaBean) {
		this.ligaBean = ligaBean;
	}

}