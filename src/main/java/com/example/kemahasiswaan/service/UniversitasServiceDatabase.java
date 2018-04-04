package com.example.kemahasiswaan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kemahasiswaan.dao.MahasiswaMapper;
import com.example.kemahasiswaan.dao.UniversitasMapper;
import com.example.kemahasiswaan.model.FakultasModel;
import com.example.kemahasiswaan.model.KelulusanModel;
import com.example.kemahasiswaan.model.ProgramStudiModel;
import com.example.kemahasiswaan.model.MahasiswaModel;
import com.example.kemahasiswaan.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UniversitasServiceDatabase implements UniversitasService {

	@Autowired
    private UniversitasMapper universitasMapper;
    
    @Override
    public List<UniversitasModel> getUniversitas() {
    	return universitasMapper.getUniversitas();
    }
    
    @Override
    public String getNamaUniversitas(int id_universitas) {
    	return universitasMapper.getNamaUniversitas(id_universitas);
    }
    
}
