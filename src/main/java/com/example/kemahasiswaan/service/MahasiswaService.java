package com.example.kemahasiswaan.service;

import java.util.List;

import com.example.kemahasiswaan.model.FakultasModel;
import com.example.kemahasiswaan.model.KelulusanModel;
import com.example.kemahasiswaan.model.ProgramStudiModel;
import com.example.kemahasiswaan.model.MahasiswaModel;
import com.example.kemahasiswaan.model.UniversitasModel;

public interface MahasiswaService {
    MahasiswaModel selectStudent (String npm);


    List<MahasiswaModel> selectAllStudents ();


    boolean addStudent (MahasiswaModel student);


    boolean deleteStudent (String npm);


	boolean updateStudent(MahasiswaModel student);
	
	String getKodeUniv(String id_prodi);
	
	String getLastUser(String kode);
	
	KelulusanModel getTotalMahasiswaAndDetail(String id_prodi, int tahun);
	
	int getTotalLulus(String id_prodi, int tahun);
	
	List<MahasiswaModel> getMahasiswaByProdi(int id_prodi);
	
	MahasiswaModel getMahasiswaTermuda(int id_prodi, int tahun_masuk);
	
	MahasiswaModel getMahasiswaTertua(int id_prodi, int tahun_masuk);
}
