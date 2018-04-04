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
public interface MahasiswaMapper {
	
	@Select("SELECT m.*, p.nama_prodi, p.id_fakultas, f.nama_fakultas, f.id_univ , u.nama_univ FROM mahasiswa m "
    		+ "LEFT JOIN program_studi p ON m.id_prodi = p.id LEFT JOIN fakultas f ON f.id = p.id_fakultas "
    		+ "LEFT JOIN universitas u ON f.id_univ = u.id WHERE m.npm = #{npm}")
    MahasiswaModel selectStudent (@Param("npm") String npm);

    @Select("select npm, name, gpa from student")
    List<MahasiswaModel> selectAllStudents ();

    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi)"
    		+ " VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{agama}, #{golongan_darah}, 'Aktif', #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
    boolean addStudent (MahasiswaModel student);
    
    @Delete("DELETE FROM student where npm = #{npm}")
    boolean deleteStudent(@Param("npm") String npm);
    
    @Update("UPDATE mahasiswa SET npm = #{npm}, nama= #{nama}, tempat_lahir = #{tempat_lahir}, "
    		+ "jenis_kelamin = #{jenis_kelamin}, agama = #{agama}, golongan_darah = #{golongan_darah},"
    		+ "tahun_masuk = #{tahun_masuk}, jalur_masuk = #{jalur_masuk}, id_prodi = #{id_prodi} WHERE npm = #{oldNpm}")
    boolean updateStudent(MahasiswaModel student);
    
    @Select("SELECT kode_univ FROM `program_studi` p left join "
    		+ "fakultas f on p.id_fakultas = f.id left JOIN universitas u ON f.id_univ = u.id WHERE p.id = #{id_prodi}")
    List<String> getKodeUniv(String id_prodi);
    
    @Select("SELECT COUNT(*) FROM mahasiswa WHERE id_prodi = #{id_prodi} AND status = 'Lulus' AND tahun_masuk = #{tahun}")
    List<Integer> getTotalMahasiswaLulus(@Param("id_prodi") String id_prodi, @Param("tahun") int tahun);
    
    @Select("SELECT count(m.npm) as total, p.nama_prodi, f.nama_fakultas, u.nama_univ FROM mahasiswa m LEFT JOIN program_studi p ON m.id_prodi = p.id "
    		+ "LEFT JOIN fakultas f ON f.id = p.id_fakultas LEFT JOIN universitas u ON f.id_univ = u.id WHERE p.id = #{id_prodi} AND m.tahun_masuk = #{tahun}")
    KelulusanModel getTotalMahasiswaAndDetail(@Param("id_prodi") String id_prodi, @Param("tahun") int tahun);
    
    @Select("SELECT npm FROM mahasiswa WHERE LEFT(npm, 9) = #{kode} ORDER BY npm DESC LIMIT 1")
    List<String> getLastUser(String kode);
    
    @Select("SELECT * FROM mahasiswa WHERE id_prodi = #{id_prodi}")
    List<MahasiswaModel> getMahasiswaByProdi(@Param("id_prodi") int id_prodi);
    
    @Select("SELECT * FROM mahasiswa WHERE tahun_masuk = #{tahun_masuk} AND id_prodi = #{id_prodi} ORDER BY tanggal_lahir ASC LIMIT 1")
    MahasiswaModel getMahasiswaTertua(@Param("id_prodi") int id_prodi, @Param("tahun_masuk") int tahun_masuk);
    
    @Select("SELECT * FROM mahasiswa WHERE tahun_masuk = #{tahun_masuk} AND id_prodi = #{id_prodi} ORDER BY tanggal_lahir DESC LIMIT 1")
    MahasiswaModel getMahasiswaTermuda(@Param("id_prodi") int id_prodi, @Param("tahun_masuk") int tahun_masuk);
    
}
