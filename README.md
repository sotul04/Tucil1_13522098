# Tugas Kecil 1 Strategi Algoritma - Suthasoma Mahardhika Munthe 13522098

## Daftar Isi

- [Tugas Kecil 1 Strategi Algoritma](#tugas-kecil-1-strategi-algoritma---suthasoma-mahardhika-munthe-13522098)
  - [Daftar Isi](#daftar-isi)
  - [Cyberpunk 2077 Breach Protocol](#cyberpunk-2077-breach-protocol)
  - [Struktur Program](#struktur-program)
  - [Menjalankan Program dan Requirement](#menjalankan-program-dan-requirement)

## Cyberpunk 2077 Breach Protocol

Cyberpunk 2077 Breach Protocol adalah sebuah *minigame* peretasan pada permainan video Cyberpunk 2077. *Minigame* ini merupakan simulasi peretasan jaringan lokal dari *ICE (Intrusion Countermaeasures Electronics)* pada permainan *Cyberpunk 2077*.

Pada awal permainan akan ada sebuah matriks berisi token-token, buffer berisi token-token dengan jumlah maksimal token, dan sekuens berisi rangkaian token (dua atau lebih) yang memiliki bobot *reward*. Pemain bergerak dengan pola vertikal, horizontal, vertikal, dan seterusnya secara bergantian. Pemain memilih token pertama pada barisan paling atas pada matriks. Kemudian, mengikuti langkah pola permainan memilih token lain dan mencocokkannya dengan sekuens yang tersedia. Buffer yang memiliki jumlah token paling sedikit dengan poin *reward* terbanyak menjadi solusi optimal permainan ini. Sementara itu, jika tidak ada buffer yang memuat sekuen sama sekali maka tidak ada solusi yang ditemukan. 

Proyek ini adalah membuat suatu algoritma dengan pendekatan *brute force* untuk menyelesaikan permainan *Cyberpunk 2077 Breach Protocol*.

## Struktur Program

```
│ .gitignore
│ README.md
├── bin
│   ├── models
│   │   ├── Buffer.class
│   │   ├── Matrix.class
│   │   ├── Sequence.class
│   │   └── Visited.class
│   ├── operations
│   │   ├── Bordered.class
│   │   ├── Game.class
│   │   ├── MatrixSolution.class
│   │   ├── Sekuens.class
│   │   ├── Solution.class
│   │   └── Token.class 
│   ├── GameGUI.class
│   ├── GameGUI$1.class
│   ├── GameGUI$2.class
│   ├── GameGUI$3.class
│   ├── GameGUI$4.class
│   ├── GameGUI$5.class
│   ├── IntegerInput.class
│   ├── Main.class
│   ├── SolutionDisplay.class
│   └── SolutionDisplay$1.class
├── doc
├── src
│   ├── models
│   │   ├── Buffer.java
│   │   ├── Matrix.java
│   │   ├── Sequence.java
│   │   └── Visited.java
│   ├── operations
│   │   ├── Bordered.java
│   │   ├── Game.java
│   │   ├── MatrixSolution.java
│   │   ├── Sekuens.java
│   │   ├── Solution.java
│   │   └── Token.java 
│   ├── GameGUI.java
│   ├── icon.png
│   ├── IntegerInput.java
│   ├── logo.png
│   ├── Main.java
│   └── SolutionDisplay.java
└── test
    ├── input                  # Default input folder
    └── output                 # Default save folder
```

## Menjalankan Program dan Requirement

Untuk menjalankan program dibutuhkan java versi 20+.

Untuk menjalankan program, pada *root directory* pindah ke folder bin dengan command cd bin
```
cd bin
```
Selanjutnya, jalankan program dengan command java Main
```
java Main
```
Program akan berjalan dengan tampilan awal seperti ini
<br>
<p align="center">
<img src="./ss-program.png" alt="Preview" width="300"/>
</p>
<br>
Tampilan hasil pemrosesan program dapat dilihat pada gambar di bawah ini.
<p align="center">
<br>
<br>
<img src="./ss-solution.png" alt="Pop Up" width="600">
</p>
<br>
<br>

Kemudian, pada pilihan masukan berupa file, hanya file berekstensi ```.txt``` yang dapat diproses oleh program.

> Penting untuk diperhatikan, file dengan ekstensi ```.txt``` harus memuat data sesuai format. Data khususnya yang akan direpresentasikan sebagai ```integer``` tidak boleh memuat karakter apapun selain karakter numerik baik di awal, di tengah, maupun di akhir baris (kecuali pada bagian dimensi matriks).
