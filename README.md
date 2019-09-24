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

