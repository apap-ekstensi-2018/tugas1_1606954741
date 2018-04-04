package com.example.kemahasiswaan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kemahasiswaan.dao.FakultasMapper;
import com.example.kemahasiswaan.dao.MahasiswaMapper;
import com.example.kemahasiswaan.model.FakultasModel;
import com.example.kemahasiswaan.model.KelulusanModel;
import com.example.kemahasiswaan.model.ProgramStudiModel;
import com.example.kemahasiswaan.model.MahasiswaModel;
import com.example.kemahasiswaan.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakultasServiceDatabase implements FakultasService {

	@Autowired
    private FakultasMapper fakultasMapper;
    
    @Override
    public List<FakultasModel> getFakultas(int id_univ){
    	return fakultasMapper.getFakultas(id_univ);
    }
    
    @Override
    public String getNamaFakultas(int id_fakultas) {
    	return fakultasMapper.getNamaFakultas(id_fakultas);
    }
    
}
