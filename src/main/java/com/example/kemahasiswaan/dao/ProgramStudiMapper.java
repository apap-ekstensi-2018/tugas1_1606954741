package com.example.kemahasiswaan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.kemahasiswaan.model.FakultasModel;
import com.example.kemahasiswaan.model.KelulusanModel;
import com.example.kemahasiswaan.model.ProgramStudiModel;
import com.example.kemahasiswaan.model.MahasiswaModel;
import com.example.kemahasiswaan.model.UniversitasModel;

@Mapper
public interface ProgramStudiMapper {
    
    @Select("SELECT * FROM program_studi WHERE id_fakultas = #{id_fakultas}")
    List<ProgramStudiModel> getProdi(@Param("id_fakultas") int id_fakultas);
    
    @Select("SELECT nama_prodi FROM program_studi WHERE id = #{id_prodi}")
    String getNamaProdi(@Param("id_prodi") int id_prodi);
    
    @Select("SELECT kode_prodi FROM program_studi WHERE id = #{id_prodi}")
    int getKodeProdiByID(@Param("id_prodi") int id_prodi);
}
