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
public interface FakultasMapper {
	
    @Select("SELECT * FROM fakultas WHERE id_univ = #{id_univ}")
    List<FakultasModel> getFakultas(@Param("id_univ") int id_univ);
    
    @Select("SELECT nama_fakultas FROM fakultas WHERE id = #{id_fakultas}")
    String getNamaFakultas(@Param("id_fakultas") int id_fakultas);
}
