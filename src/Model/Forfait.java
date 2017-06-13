package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FORFAIT")
public class Forfait implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="IDFORFAIT")
    @GeneratedValue
	private int idForfait;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="COUTMENSUEL")
	private float coutMensuel;
	
	@Column(name="LOCATIONMAXFILMS")
	private int locationMaxFilms;
	
	@Column(name="DUREEMAXJOURS")
	private int dureeMaxJours;
	
	public Forfait() {
	}

	public int getIdForfait() {
		return idForfait;
	}

	public void setIdForfait(int idForfait) {
		this.idForfait = idForfait;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getCoutMensuel() {
		return coutMensuel;
	}

	public void setCoutMensuel(float coutMensuel) {
		this.coutMensuel = coutMensuel;
	}

	public int getLocationMaxFilms() {
		return locationMaxFilms;
	}

	public void setLocationMaxFilms(int locationMaxFilms) {
		this.locationMaxFilms = locationMaxFilms;
	}

	public int getDureeMaxJours() {
		return dureeMaxJours;
	}

	public void setDureeMaxJours(int dureeMaxJours) {
		this.dureeMaxJours = dureeMaxJours;
	}

}
