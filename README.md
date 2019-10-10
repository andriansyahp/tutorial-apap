# Tutorial APAP

## Authors

* **Muhammad Andriansyah Putra** - *1706044093* - *C*

## Tutorial 1
### What I have learned today

#### Github
1. Apa itu Issue Tracker? Masalah apa yang dapat diselesaikan dengan Issue Tracker?
    - *Issue Tracker* adalah tempat untuk memberikan semacam catatan mengenai perkembangan saat itu. *Issue Tracker* bisa berisi masalah yang sedang dihadapi dan ingin diselesaikan, pemberian masukan dan ide, permintaan *feature*, *bug fix* dan lainnya.
2. Apa perbedaan dari git merge dan merge --squash?
    - `git merge` biasa akan melakukan *merge* semua *commit* dari *branch* asal ke *branch* tujuan, sedangkan `git merge --squash` akan menggabungkan semua *commit* menjadi satu terlebih dahulu baru di-*merge* sebagai satu kesatuan ke *branch* tujuan.
#### Spring
3. Apa itu library & dependency?
    - *Library* dan *dependency* berisi kelas-kelas yang mungkin kita ingin gunakan untuk mempermudah dalam membuat proyek kita sehingga kita hanya perlu menggunakan *library* dan *dependency* tanpa harus *build from scratch*.
4. Apa itu Maven? Mengapa kita perlu menggunakan Maven?
    - Maven adalah sebuah *automation build tools*. Maven digunakan karena dapat memudahkan proses pembuatan proyek dengan *dependency* dan *library* sehingga tidak perlu repot *setup* nya.
5. Apa alternatif dari Maven?
    - Ant, Gradle.
### What I did not understand
- [x] Fungsi anotasi?
    + Anotasi digunakan dengan fungsi mirip seperti pada *interface*. Dengan anotasi, kita bisa mengimplementasikan *dependency injection* yang menjadi keunggulan *Three-Tier Layer architecture* (MVC).


## Tutorial 2
### What I have learned today
1. Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut: http://localhost:8080/restoran/add?idRestoran=1&nama=PanyuFC&alamat=Kantin%20Fasilkom&nomorTelepon=14022 . Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
    - ![Error.](https://i.ibb.co/VN7qJWj/error-controller-step-2.png)
        + Terjadi *Internal Server error* dikarenakan *view* yang telah kamu dibuat pada *controller* yaitu di `add-restoran` belum dibuat, sehingga harus dibuat terlebih dahulu *view* dari *template*-nya.
2. Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut: http://localhost:8080/restoran/add?idRestoran=2&nama=KentukuFC&alamat=Kantin%20FIK . Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
    - ![Error.](https://i.ibb.co/VN7qJWj/error-controller-step-2.png)
        + Terjadi *Bad Request error*  yang disebabkan karena jumlah parameter yang diminta *method* **add** di anotasi *@RequestParam* yaitu *nomorTelepon* dimana *required*-nya *true* tidak disertakan.
3. Jika Papa APAP ingin melihat restoran PanyuFC , link apa yang harus diakses?
    - ![Melihat restoran PanyuFC.](https://i.ibb.co/c1gxVQY/error-view-step-2.png)
        + Papa APAP bisa mengakses link http://localhost:8080/restoran/view?idRestoran=1 karena PanyuFC memiliki *idRestoran* bernilai '1'.
4. Tambahkan 1 contoh restoran lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/restoran/viewall , apa yang akan ditampilkan? Sertakan juga bukti screenshot mu.
    - ![Add PanyuFC.](https://i.ibb.co/pjr9dnt/pertanyaan-4-1.png)
        + Menambahkan restoran 'PanyuFC'.
    - ![Add Sate Fasilkom.](https://i.ibb.co/thZq0mw/pertanyaan-4-2.png)
        + Menambahkan restoran 'Sate Fasilkom'.
    - ![View All.](https://i.ibb.co/mFtGYjR/pertanyaan-4-3.png)
        + Melihat semua restoran dengan *method* **viewall**.

### What I did not understand
- [ ] Apakah ada cara untuk menyeragamkan semua halaman HTML agar bisa dibuat dengan *template* agar lebih sederhana dan terstruktur serta terorganisir?

## Tutorial 3
### What I have learned today
1. Pada class MenuDb, terdapat method findByRestoranIdRestoran, apakah kegunaan dari method tersebut?
    - Method findByRestoranIdRestoran pada MenuDb akan diimplementasikan di MenuService untuk melakukan pencarian Restoran berdasarkan ID yang diberikan.
2. Pada class RestoranController, jelaskan perbedaan method addRestoranFormPage dan addRestoranSubmit?
    - Method addRestoranFormPage berisi form untuk mengisi data pembuatan restoran, sedangkan method addRestoranSubmit menyimpan form yang telah diisi ke dalam database.
3. Jelaskan apa kegunaan dari JPA Repository?
    - JPA Repository digunakan sebagai jembatan pemetaan dari objek pada Java dengan database yang disimpan.
4. Sebutkan dan jelaskan di bagian kode mana sebuah relasi antara RestoranModel dan MenuModel dibuat?
    - ![Relasi Restoran dengan Menu berdasarkan soal.](https://i.ibb.co/kSH9k0d/relation-menu-restoran.png)
        + Relasi Restoran dengan Menu berdasarkan soal.
    - ![Implementasi relasi Restoran yang dapat memiliki banyak menu.](https://i.ibb.co/BjLg06v/restoran-relation.png)
        + Implementasi relasi Restoran yang dapat memiliki banyak menu.
    - ![Implementasi relasi Many-to-One dari Menu ke Restoran.](https://i.ibb.co/QXLjH3z/menu-relation.png)
        + Implementasi relasi Many-to-One dari Menu ke Restoran.
5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER.
    - FetchType.LAZY hanya mengambil ketika dibutuhkan, sedangkan FetchType.EAGER mengambil seluruhnya secara langsung. CascadeType.ALL artinya menerapkan semua perubahan terhadap entity terkait.

### What I did not understand
- [ ] 

## Tutorial 4
### What I have learned today
1. Jelaskan yang anda pelajari dari melakukan latihan nomor 2, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 2
    - Latihan nomor 2 berkaitan dengan sebuah fragment header yang dinamis. Yang saya lakukan adalah:
        + ![Fragment](https://i.ibb.co/3pZ6nTY/dynamic-navbar.png)
            - Menambahkan potongan kode seperti yang di-highlight pada gambar di atas ke dalam fragment.
        + ![View](https://i.ibb.co/2j5Pkj7/dynamic-navbar-2.png)
            Setelah itu, menambahkan id ke dalam tag title di semua view, salah satu contohnya seperti pada gambar di atas yaitu di view Add Menu.
2. Jelaskan yang anda pelajari dari latihan nomor 3, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 2
    - 
3. Jelaskan perbedaan th:include dan th:replace
    - th:include akan meng-include konten dari fragment ke dalam tag terkait, sedangkan th:replace akan mengganti (replace) keseluruhan tag dengan yang ada di fragment. 
4. Jelaskan bagaimana penggunaan th:object beserta tujuannya
    - th:object digunakan untuk menentukan objek berisi data yang akan diisikan ke dalam form untuk kemudian disimpan ke dalam database.

### What I did not understand
- [ ] 

## Tutorial 5
### Latihan
1. *Coverage* sesudah *testing*:
    - ![Latihan 1](https://i.ibb.co/CmfSGRv/lat-1-after.jpg)
2. *Coverage* sesudah *testing*
    - ![Latihan 2](https://i.ibb.co/QMBbMjT/lat-2-after.jpg)

### What I have learned today
1. Jelaskan bagian mana saja dari test yang dibuat pada latihan no 2 adalah given, when, dan and then.
    - **_Given_**: Mengatur pembuatan restoran *dummy* dan menu-menu *dummy* untuk restoran tersebut, kemudian mengatur *return* dari setiap aktivitas *service* di *database*.
    - **_When_**: Membuka *URL* untuk *View Restoran* dengan ID 1 (`mockMvc.perform(get("/restoran/view?idRestoran=1"))`).
    - **_Then_**: Ekspektasi hasil operasi yang dilakukan pada **_When_** (dicek melalui setiap baris `.andExpect()`).
        
2. Jelaskan perbedaan line coverage dan logic coverage.
    - *Line Coverage* mengecek apakah setiap baris pernah dijalankan setidaknya sekali, sedangkan *Logic Coverage* mengecek baris *logical branching* seperti *if* dan *else*.

3. Pada keadaan ideal, apa yang seharusnya dibuat terlebih dahulu, code atau unit test? Mengapa seperti itu? Apa akibatnya jika urutannya dibalik, adakah risiko tak terlihat yang mungkin terjadi?
    - *Unit testing* berguna untuk mengecek fungsionalitas per komponen, sedangkan *code testing* akan menguji program secara keseluruhan. Hal ini menyebabkan *unit testing* umumnya harus diprioritaskan, agar *error* bisa ditangkap lebih awal (pengecekan komponen) dibanding ketika dicek setelah semua selesai.

4. [Bonus] Jelaskan mengapa pada latihan no 3, main class spring tidak diikutsertakan ke dalam perhitungan coverage? Apa saja yang dapat menyebabkan suatu class dapat di-exclude dari perhitungan code coverage.
    - *Main class Spring* di-*exclude* karena *Main class Spring* tidak dijalankan dan diperhitungkan *coverage*-nya, sehingga akan merusak hasil penghitungan *coverage* secara keseluruhan. Faktor lain suatu *class* di-*exclude* antara lain karena *class* tersebut tidak memiliki *coverage* yang signifikan, *config file*, atau pun *built-in file*.

### What I did not understand
- [ ] 

