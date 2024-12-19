package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
