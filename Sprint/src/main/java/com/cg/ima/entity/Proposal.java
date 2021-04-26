package com.cg.ima.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * 
 * @author Aastha
 *
 */
@Entity
@Table(name = "proposal")
public class Proposal {
	@Id
	@SequenceGenerator(name = "prop", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prop")
	private int propId;
	@NotNull
	private String proposal;
	@NotNull
	private double amount;
	@NotNull
	private LocalDate proposalDate;
	@NotNull
	private boolean isAccepted;

	private LocalDate acceptedOn;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	@JoinColumn(name = "resid")
	private Resource resource;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empId")
	@NotNull

	private Employee emp;

	public int getPropId() {
		return propId;
	}

	public void setPropId(int propId) {
		this.propId = propId;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getProposalDate() {
		return proposalDate;
	}

	public void setProposalDate(LocalDate proposalDate) {
		this.proposalDate = proposalDate;
	}

	public boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public LocalDate getAcceptedOn() {
		return acceptedOn;
	}

	public void setAcceptedOn(LocalDate acceptedOn) {
		this.acceptedOn = acceptedOn;
	}

	public Employee getEmp() {
		return this.resource.getEmp();
	}

	public void setEmp(Resource res) {
		this.emp = this.resource.getEmp();
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Proposal(String proposal, double amount, LocalDate proposalDate, boolean isAccepted, LocalDate acceptedOn,
			@NotNull Resource resource) {
		super();
		this.proposal = proposal;
		this.amount = amount;
		this.proposalDate = proposalDate;
		this.isAccepted = isAccepted;
		this.acceptedOn = acceptedOn;

		this.resource = resource;
		this.emp = this.resource.getEmp();
	}

	@Override
	public String toString() {
		return "Proposal [propId=" + propId + ", proposal=" + proposal + ", amount=" + amount + ", proposalDate="
				+ proposalDate + ", isAccepted=" + isAccepted + ", acceptedOn=" + acceptedOn + ", emp=" + emp
				+ ", resource=" + resource + "]";
	}

	public Proposal() {

	}

}
