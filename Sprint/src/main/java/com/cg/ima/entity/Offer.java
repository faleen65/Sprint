package com.cg.ima.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * 
 * @author Himanshu
 *
 */
@Entity
@Table(name = "offer")
public class Offer {

	@Id
	@SequenceGenerator(name = "off22", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "off22")
	private int offerId;
	@NotNull
	private boolean isAvailable;
	private LocalDate availableUpto;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "offerId")
	@NotNull
	private List<Proposal> proposals = new ArrayList<>();

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean bool) {
		this.isAvailable = bool;
	}

	public LocalDate getAvailableUpto() {
		return availableUpto;
	}

	public void setAvailableUpto(LocalDate availableUpto) {
		this.availableUpto = availableUpto;
	}

	public List<Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}

	public Offer() {
		super();
	}

	public Offer(boolean isAvailable, LocalDate availableUpto, List<Proposal> proposals) {
		super();
		this.isAvailable = isAvailable;
		this.availableUpto = availableUpto;
		this.proposals = proposals;

	}

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", isAvailable=" + isAvailable + ", availableUpto=" + availableUpto
				+ ", proposals=" + proposals + "]";
	}

}
