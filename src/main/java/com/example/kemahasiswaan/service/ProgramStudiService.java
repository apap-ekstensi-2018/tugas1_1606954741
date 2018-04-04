package com.example.kemahasiswaan.service;

import java.util.List;

import com.example.kemahasiswaan.model.ProgramStudiModel;

public interface ProgramStudiService {
    
	List<ProgramStudiModel> getProdi(int id_fakultas);
	
	String getNamaProdi(int id_prodi);
	
	int getKodeProdiByID(int id_prodi);
}
