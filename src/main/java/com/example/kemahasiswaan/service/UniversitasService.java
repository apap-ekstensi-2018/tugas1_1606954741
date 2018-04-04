package com.example.kemahasiswaan.service;

import java.util.List;

import com.example.kemahasiswaan.model.UniversitasModel;

public interface UniversitasService {
	
	List<UniversitasModel> getUniversitas();
	
	String getNamaUniversitas(int id_universitas);
}
