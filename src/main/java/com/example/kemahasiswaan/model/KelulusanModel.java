package com.example.kemahasiswaan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelulusanModel {
	private int total;
    private String nama_prodi;
    private String nama_fakultas;
    private String nama_univ;
}
