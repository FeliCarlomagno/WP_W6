package com.gestione_dispositivi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestione_dispositivi.model.Device;

public interface DeviceDaoRepository extends CrudRepository<Device, Long>, PagingAndSortingRepository<Device, Long> {

}
