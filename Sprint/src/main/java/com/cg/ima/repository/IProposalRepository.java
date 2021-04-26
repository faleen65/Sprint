package com.cg.ima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ima.entity.Proposal;

@Repository
public interface IProposalRepository extends JpaRepository<Proposal, Integer> {

}
