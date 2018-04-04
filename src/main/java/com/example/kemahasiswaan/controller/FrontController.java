package com.example.kemahasiswaan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kemahasiswaan.service.FakultasService;
import com.example.kemahasiswaan.service.MahasiswaService;
import com.example.kemahasiswaan.service.ProgramStudiService;
import com.example.kemahasiswaan.service.UniversitasService;

import lombok.extern.slf4j.Slf4j;

import com.example.kemahasiswaan.model.FakultasModel;
import com.example.kemahasiswaan.model.KelulusanModel;
import com.example.kemahasiswaan.model.ProgramStudiModel;
import com.example.kemahasiswaan.model.MahasiswaModel;
import com.example.kemahasiswaan.model.UniversitasModel;

@Slf4j
@Controller
public class FrontController {
	
	@Autowired
    MahasiswaService studentDAO;
	@Autowired
	FakultasService fakultasDAO;
	@Autowired
	ProgramStudiService prodiDAO;
	@Autowired
	UniversitasService universitasDAO;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("url","home");
		return "index";
	}
	
	@RequestMapping("/mahasiswa")
	public String view(Model model, @RequestParam(value = "npm", required = false) String npm) {
		MahasiswaModel student = studentDAO.selectStudent (npm);
		model.addAttribute("url","home");
		if(student == null) {
			model.addAttribute("npm", npm);
			return "view-not-found";
		}
		model.addAttribute ("student", student);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   
        try {

            Date tgl_lahirDate = formatter.parse(student.getTanggal_lahir());
            SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy"); 
            String tgl_lahir = df2.format(tgl_lahirDate);
            model.addAttribute("tanggal_lahir", tgl_lahir);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		return "view";
	}
	
	@RequestMapping("/mahasiswa/tambah")
	public String addStudent(Model model) {
		model.addAttribute("student", new MahasiswaModel());
		model.addAttribute("method", "add");
		model.addAttribute("url","tambah");
		return "add";
	}
	
    @RequestMapping(value = "/mahasiswa/tambah", method = RequestMethod.POST)
    public String addSubmit (MahasiswaModel student, Model model)
    {
    	String tahun_masuk = student.getTahun_masuk();
    	String kode_univ = this.getKodeUniv(String.valueOf(student.getId_prodi()));
    	int kode_prodi = prodiDAO.getKodeProdiByID(student.getId_prodi());
    	String jalur_masuk = student.getJalur_masuk();
       
        String kodeNPM = this.generateNPM(tahun_masuk, kode_univ, kode_prodi, jalur_masuk);
    	
        String lastUser = this.getLastUser(kodeNPM);
        String urutan = "001";
        if(lastUser != "" && !lastUser.isEmpty()) {
        	String lastUrutan = lastUser.substring(lastUser.length() - 3);
        	int lastUrutan2 = Integer.valueOf(lastUrutan);
        	
        	lastUrutan2 = lastUrutan2 + 1;
        	urutan = String.format("%03d", lastUrutan2);
        }
        
        String finalNpm  = kodeNPM+urutan;
    	student.setNpm(finalNpm);
    	studentDAO.addStudent(student);
    	model.addAttribute("url","tambah");
    	model.addAttribute("npm", finalNpm);
        return "add-berhasil";
    }
    
    
    @RequestMapping("/mahasiswa/ubah/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm)
    {
    	MahasiswaModel student = studentDAO.selectStudent (npm);

        if (student != null) {
        	model.addAttribute("student",student);
        	model.addAttribute("method", "update");
            return "add";
        } else {
            model.addAttribute ("npm", npm);
            return "view-not-found";
        }

    }
    
    @RequestMapping(value = "/mahasiswa/ubah/{npm}", method = RequestMethod.POST)
    public String updateSubmit (@RequestParam(value = "npm", required = false) String npm, MahasiswaModel student, Model model)
    {
    	String tahun_masuk = student.getTahun_masuk();
    	String kode_univ = this.getKodeUniv(String.valueOf(student.getId_prodi()));
    	int kode_prodi = prodiDAO.getKodeProdiByID(student.getId_prodi());
    	String jalur_masuk = student.getJalur_masuk();
       
        String kodeNPM = this.generateNPM(tahun_masuk, kode_univ, kode_prodi, jalur_masuk);
    	
        String lastUser = this.getLastUser(kodeNPM);
        String urutan = "001";
        if(lastUser != "" && !lastUser.isEmpty()) {
        	String lastUrutan = lastUser.substring(lastUser.length() - 3);
        	int lastUrutan2 = Integer.valueOf(lastUrutan);
        	
        	lastUrutan2 = lastUrutan2 + 1;
        	urutan = String.format("%03d", lastUrutan2);
        }
        
        String finalNpm  = kodeNPM+urutan;
    	student.setNpm(finalNpm);
    	student.setOldNpm(npm);
    	boolean updated = studentDAO.updateStudent(student);
    	if(updated) {
    		model.addAttribute("npm", npm);
    		return "update-berhasil";
    	}
        return "index";
    }
    
    @RequestMapping("/kelulusan")
    public String viewKelulusan(Model model) {
    	model.addAttribute("url","kelulusan");
    	return "view-kelulusan";
    }
    
    @RequestMapping("/mahasiswa/kelulusan")
    public String getKelulusan(@RequestParam(value = "thn", required = false) int tahun, @RequestParam(value = "prodi", required = false) String prodi, Model model) {
    	int totalLulus = studentDAO.getTotalLulus(prodi, tahun);
    	KelulusanModel detailMahasiswaandTotal= studentDAO.getTotalMahasiswaAndDetail(prodi, tahun);
    	int totalMahasiswa = detailMahasiswaandTotal.getTotal();
    	
    	int percentageLulus = (int) Math.round(((double) totalLulus/totalMahasiswa) * 100);
    
    	model.addAttribute("detailMhs", detailMahasiswaandTotal);
    	model.addAttribute("totalLulus", totalLulus);
    	model.addAttribute("percentage", percentageLulus);
    	model.addAttribute("tahun", tahun);
    	model.addAttribute("url","kelulusan");
    	return "detail-kelulusan";
    }
    
    @RequestMapping("/mahasiswa/viewCari")
    public String cariBasedUniv(Model model) {
    	List<UniversitasModel> univ = universitasDAO.getUniversitas();
    	model.addAttribute("url","cari");
    	model.addAttribute("univs", univ);
    	
    	return "cari-univ";
    }
    
    @RequestMapping("/mahasiswa/cari")
    public String postCariBasedUniv(@RequestParam(value = "univ", required = false) int univ , Model model) {
    	List<UniversitasModel> univs = universitasDAO.getUniversitas();
    	for(UniversitasModel item: univs) {
    		if(item.getId() == univ) {
    			model.addAttribute("univSelected", item);
    		}
    	}
    	model.addAttribute("id_univ", univ);
    	List<FakultasModel> fakultas= fakultasDAO.getFakultas(univ);
    	model.addAttribute("fakultas", fakultas);
    	model.addAttribute("url","cari");
    	return "cari-fakultas";
    }
    
    @RequestMapping("/mahasiswa/cari2")
    public String postCariBasedFakultas(@RequestParam(value = "univ", required = false) int univ ,
    		@RequestParam(value = "fakultas", required = false) int fak, Model model) {
    	
    	List<UniversitasModel> univs = universitasDAO.getUniversitas();
		model.addAttribute("univSelected", univs);
    	model.addAttribute("id_univ", univ);
    	
    	List<FakultasModel> fakultas= fakultasDAO.getFakultas(univ);
    	model.addAttribute("fakultas", fakultas);
    	model.addAttribute("id_fakultas", fak);
    	
    	List<ProgramStudiModel> prodi = prodiDAO.getProdi(fak);
    	model.addAttribute("prodi", prodi);
    	model.addAttribute("url","cari");
    	return "cari-prodi";
    }
    
    @RequestMapping("/mahasiswa/cari3")
    public String postCariBasedProdi(@RequestParam(value = "univ", required = false) int univ ,
    		@RequestParam(value = "fakultas", required = false) int fak,
    		@RequestParam(value = "prodi", required = false) int prodi,
    		Model model) {
    	
    	List<MahasiswaModel> mahasiswa = studentDAO.getMahasiswaByProdi(prodi);
    	model.addAttribute("mahasiswa", mahasiswa);
    	model.addAttribute("prodi", prodiDAO.getNamaProdi(prodi));
    	model.addAttribute("fakultas", fakultasDAO.getNamaFakultas(fak));
    	model.addAttribute("universitas", universitasDAO.getNamaUniversitas(univ));
    	model.addAttribute("url","cari");
    	return "viewall";
    }
    
    @RequestMapping("/mahasiswa/cariUsia")
    public String viewCariMahasiswaTertutaTermuda() {
    	
    	return "view-usia";
    }
    
    @RequestMapping(value = "/mahasiswa/postCariUsia")
    public String cariMahasiswaTertutaTermuda(
    		@RequestParam(value = "id_prodi", required = false) int id_prodi, 
    		@RequestParam(value = "tahun_masuk", required = false) int tahun_masuk,
    		Model model) {
    	
    	MahasiswaModel mahasiswaTertua = studentDAO.getMahasiswaTertua(id_prodi, tahun_masuk);
    	MahasiswaModel mahasiswaTermuda = studentDAO.getMahasiswaTermuda(id_prodi, tahun_masuk);
    	
    	model.addAttribute("tertua", mahasiswaTertua);
    	model.addAttribute("termuda", mahasiswaTermuda);
    	
    	return "view-detail-usia";
    }
    
    public String generateNPM(String tahun_masuk, String kode_univ, int kode_prodi, String jalur_masuk) {
    	String subtahun_masuk = tahun_masuk.substring(tahun_masuk.length() - 2);
    	
    	int kode_jalur_masuk;
        switch (jalur_masuk) {
             case "Undangan Reguler/SNMPTN":  
    	 		kode_jalur_masuk = 54;
    	 		break;
             case "Ujian Tulis Mandiri":  
        	 	kode_jalur_masuk = 62;
        	 	break;
             case "Undangan Paralel/PPKB":  
         	 	kode_jalur_masuk = 55;
         	 	break;
             case "Ujian Tulis Bersama/SBMPTN":  
         	 	kode_jalur_masuk = 57;
         	 	break;
             case "Undangan Olimpiade":  
         	 	kode_jalur_masuk = 53;
         	 	break;
             default: 
            	 kode_jalur_masuk = 0;
                 break;
         }
        
        String kodeNPM = subtahun_masuk + kode_univ + kode_prodi + kode_jalur_masuk;
    	return kodeNPM;
    }
    
    public String getKodeUniv(String id_prodi) {
    	return studentDAO.getKodeUniv(id_prodi);
    }
    
    public String getLastUser(String kode) {
    	return studentDAO.getLastUser(kode);
    }
    
  
}
