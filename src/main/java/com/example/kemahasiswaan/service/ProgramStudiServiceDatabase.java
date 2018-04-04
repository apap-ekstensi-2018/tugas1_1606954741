package com.example.kemahasiswaan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.kemahasiswaan.dao.ProgramStudiMapper;
import com.example.kemahasiswaan.model.ProgramStudiModel;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramStudiServiceDatabase implements ProgramStudiService {

	@Autowired
    private ProgramStudiMapper prodiMapper;

    @Override
    public List<ProgramStudiModel> getProdi(int id_fakultas) {
    	return prodiMapper.getProdi(id_fakultas);
    }
    
    @Override
    public String getNamaProdi(int id_prodi) {
    	return prodiMapper.getNamaProdi(id_prodi);
    }
    
    @Override
    public int getKodeProdiByID(int id_prodi) {
    	return prodiMapper.getKodeProdiByID(id_prodi);
    }
}
