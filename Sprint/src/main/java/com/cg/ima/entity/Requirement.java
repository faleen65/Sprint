package com.cg.ima.entity;

import java.time.LocalDate;
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
 * @author Faleen
 *
 */
@Entity
@Table(name = "requirement")
public class Requirement {
	@Id
	@SequenceGenerator(name = "req", initialValue = 90001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "req")
	private int reqId;
	@NotNull
	private boolean isFulfilled;
	private LocalDate fulfilledOn;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reqId")
	@NotNull
	private List<Proposal> proposals;

	public Requirement() {
		super();
	}

	public Requirement(boolean isFulfilled, LocalDate fulfilledOn, List<Proposal> proposals) {

		this.isFulfilled = isFulfilled;
		this.fulfilledOn = fulfilledOn;
		this.proposals = proposals;

	}

	@Override
	public String toString() {
		return "Requirement [reqId=" + reqId + ", isFulfilled=" + isFulfilled + ", fulfilledOn=" + fulfilledOn
				+ ", proposals=" + proposals + "]";
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public boolean getisFulfilled() {
		return isFulfilled;
	}

	public void setisFulfilled(boolean isFulfilled) {
		this.isFulfilled = isFulfilled;
	}

	public LocalDate getFulfilledOn() {
		return fulfilledOn;
	}

	public void setFulfilledOn(LocalDate fulfilledOn) {
		this.fulfilledOn = fulfilledOn;
	}

	public List<Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}

}
