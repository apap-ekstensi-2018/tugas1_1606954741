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
public interface UniversitasMapper {
    
    @Select("SELECT * FROM universitas")
    List<UniversitasModel> getUniversitas();
    
    @Select("SELECT nama_univ FROM universitas WHERE id = #{id_universitas}")
    String getNamaUniversitas(@Param("id_universitas") int id_universitas);
}
