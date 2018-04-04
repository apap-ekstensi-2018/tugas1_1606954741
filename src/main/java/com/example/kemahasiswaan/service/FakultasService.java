package com.example.kemahasiswaan.service;

import java.util.List;

import com.example.kemahasiswaan.model.FakultasModel;

public interface FakultasService {
    
	List<FakultasModel> getFakultas(int id_univ);
	
	String getNamaFakultas(int id_fakultas);
	
}
