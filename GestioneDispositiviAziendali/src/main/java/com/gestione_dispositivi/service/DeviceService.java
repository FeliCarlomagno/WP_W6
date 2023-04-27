package com.gestione_dispositivi.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestione_dispositivi.model.Device;
import com.gestione_dispositivi.repository.DeviceDaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DeviceService {
	
	@Autowired private DeviceDaoRepository repoDevice;
	
	@Autowired @Qualifier ("NuovoDispositivo") private ObjectProvider<Device> fakeDeviceProvider;
	
	public Device saveDevice(Device d) {
		 repoDevice.save(d);
		return d;
	}
	
	public void createFakeDevice() {
		Device dev = fakeDeviceProvider.getObject();
		saveDevice(dev);
	}
	
	public String removeDevice(Long id) {
		if(repoDevice.existsById(id)) {
			repoDevice.deleteById(id);
		}else {
			throw new EntityNotFoundException("DEVICE NOT EXIST ON DATABASE!!");
		}
		return "Device correctly removed!";
	}
	
	
	public Device updateDevice(Device d) {
		 if(repoDevice.existsById(d.getId())) {
			 repoDevice.save(d);
		 }else {
			 throw new EntityNotFoundException("DEVICE NOT EXIST ON DATABASE!!");
		 }
		 return d;
	}
	
	public Device findById(Long id) {
		if(repoDevice.existsById(id)) {
			return repoDevice.findById(id).get();
		}else {
			 throw new EntityNotFoundException("DEVICE NOT EXIST ON DATABASE!!");
		}
	}
	
	public Page<Device> findAllDevice(Pageable pageable){
		return repoDevice.findAll(pageable);
	}
	
	//METODI PER PAGINAZIONE
}
