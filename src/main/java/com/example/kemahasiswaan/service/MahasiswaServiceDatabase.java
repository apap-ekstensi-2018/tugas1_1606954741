package com.example.kemahasiswaan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kemahasiswaan.dao.MahasiswaMapper;
import com.example.kemahasiswaan.model.KelulusanModel;
import com.example.kemahasiswaan.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService {

	@Autowired
    private MahasiswaMapper studentMapper;


    @Override
    public MahasiswaModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<MahasiswaModel> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public boolean addStudent (MahasiswaModel student)
    {
        return studentMapper.addStudent (student);
    }


    @Override
    public boolean deleteStudent (String npm)
    {
    	log.info("student "+ npm + " deleted");
    	return studentMapper.deleteStudent(npm);
    }
    
    @Override
    public boolean updateStudent (MahasiswaModel student)
    {
    	log.info("ini   ", student.getOldNpm());
    	boolean return_val = studentMapper.updateStudent(student);
    	log.info("update student "+ student.getNpm() + " and status is"+ return_val);
    	return return_val;
    }
    
    @Override
    public String getKodeUniv(String id_prodi) {
    	return studentMapper.getKodeUniv(id_prodi).get(0);
    	
    }
    
    @Override
    public String getLastUser(String kode) {
    	log.info("get last user"+studentMapper.getLastUser(kode).size());
    	if(studentMapper.getLastUser(kode).size() > 0){
    		return studentMapper.getLastUser(kode).get(0);
    	} else {
    		return "";
    	}
    }
    
    @Override
    public KelulusanModel getTotalMahasiswaAndDetail(String id_prodi, int tahun) {
    	
    	log.info("getgetTotalMahasiswaAndDetail "+ id_prodi + " tahun "+ tahun);
    	return studentMapper.getTotalMahasiswaAndDetail(id_prodi, tahun);
    }
    
    @Override
    public int getTotalLulus(String id_prodi, int tahun) {
    	log.info("getTotalMahasiswaLulus "+ id_prodi + " tahun "+ tahun);
    	return studentMapper.getTotalMahasiswaLulus(id_prodi, tahun).get(0);
    }
    
    @Override
    public List<MahasiswaModel> getMahasiswaByProdi(int id_prodi){
    	log.info("getMahasiswaByProdi "+ id_prodi);
    	return studentMapper.getMahasiswaByProdi(id_prodi);
    }
    
    @Override
    public MahasiswaModel getMahasiswaTermuda(int id_prodi, int tahun_masuk) {
    	return studentMapper.getMahasiswaTermuda(id_prodi, tahun_masuk);
    }
    
    @Override
    public MahasiswaModel getMahasiswaTertua(int id_prodi, int tahun_masuk) {
    	return studentMapper.getMahasiswaTertua(id_prodi, tahun_masuk);
    }
}
