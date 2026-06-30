# Flona App :seedling: :panda_face:

Flona adalah aplikasi ensiklopedia digital yang didedikasikan untuk mendokumentasikan flora dan fauna endemik Indonesia. Aplikasi ini bertujuan untuk meningkatkan kesadaran dan pengetahuan masyarakat tentang keanekaragaman hayati unik yang ada di Indonesia, serta status konservasinya.

## Fitur Utama

-   **Jelajahi Spesies Endemik:** Telusuri daftar lengkap flora dan fauna endemik Indonesia.
-   **Pencarian dan Filter:** Cari spesies berdasarkan nama dan filter berdasarkan kategori (hewan atau tumbuhan).
-   **Eksplorasi Berdasarkan Wilayah:** Temukan spesies endemik berdasarkan wilayah geografis di Indonesia (Sumatra, Jawa, Bali, Kalimantan, Sulawesi, Maluku, Papua, Nusa Tenggara).
-   **Detail Spesies:** Lihat informasi rinci untuk setiap spesies, termasuk nama ilmiah, deskripsi, famili, genus, asal, distribusi, dan status konservasi.
-   **Daftar Favorit:** Simpan spesies favorit Anda untuk akses cepat dan mudah.
-   **Mode Gelap/Terang:** Sesuaikan tampilan aplikasi dengan preferensi mode gelap atau terang.
-   **Bagikan Informasi:** Bagikan detail spesies dengan teman dan keluarga.

## Screenshot In App

<div align="center">
  <img src="https://github.com/elwanenonly/flona_app/blob/9c82a2e6a0496007d1a2a0f10987d216e8b6a26c/splash_screen.jpeg?raw=true" alt="Splash Screen" width="150">
  <img src="https://github.com/elwanenonly/flona_app/blob/9c82a2e6a0496007d1a2a0f10987d216e8b6a26c/home_page.jpeg?raw=true" alt="Home Page" width="150">
  <img src="https://github.com/elwanenonly/flona_app/blob/9c82a2e6a0496007d1a2a0f10987d216e8b6a26c/plant_category_page.jpeg?raw=true" alt="Category Page" width="150">
  <img src="https://github.com/elwanenonly/flona_app/blob/9c82a2e6a0496007d1a2a0f10987d216e8b6a26c/detail_page.jpeg?raw=true" alt="Detail Page" width="150">
  <img src="https://github.com/elwanenonly/flona_app/blob/9c82a2e6a0496007d1a2a0f10987d216e8b6a26c/explore_page.jpeg?raw=true" alt="Explore Page" width="150">
  <img src="https://github.com/elwanenonly/flona_app/blob/9c82a2e6a0496007d1a2a0f10987d216e8b6a26c/favorit_page.jpeg?raw=true" alt="Favorit Page" width="150">
  <img src="https://github.com/elwanenonly/flona_app/blob/9c82a2e6a0496007d1a2a0f10987d216e8b6a26c/dark_mode.jpeg?raw=true" alt="Dark Mode" width="150">
  <img src="https://github.com/elwanenonly/flona_app/blob/9c82a2e6a0496007d1a2a0f10987d216e8b6a26c/light_mode.jpeg?raw=true" alt="Light Mode" width="150">
</div>

## Teknologi yang Digunakan

Aplikasi Flona dibangun menggunakan teknologi Android modern:

-   **Kotlin/Java:** Bahasa pemrograman utama.
-   **Android Jetpack Components:**
    -   **Navigation Component:** Untuk mengelola navigasi antar layar.
    -   **ViewModel & LiveData:** Untuk arsitektur MVVM dan manajemen data yang reaktif.
    -   **Room Persistence Library:** Untuk penyimpanan data lokal (favorit dan caching).
-   **Retrofit:** Klien HTTP aman tipe-safe untuk mengonsumsi REST API.
-   **Gson:** Library untuk serialisasi/deserialisasi objek Java ke/dari JSON.
-   **OkHttp:** Klien HTTP untuk Android dan Java.
-   **Glide:** Library pemuatan gambar yang cepat dan efisien untuk Android.
-   **Material Design:** Untuk antarmuka pengguna yang modern dan intuitif.

## Sumber Data

Data flora dan fauna endemik yang digunakan dalam aplikasi ini bersumber dari:

-   [data_endemik Repository](https://github.com/hendroprwk08/data_endemik)

## Instalasi dan Penggunaan (Untuk Pengembang)

Untuk menjalankan proyek ini secara lokal, ikuti langkah-langkah berikut:

1.  **Kloning Repositori:**
    ```bash
    git clone https://github.com/elwanenonly/flona_app.git
    cd flona_app
    ```
2.  **Buka di Android Studio:** Buka proyek ini menggunakan Android Studio.
3.  **Sinkronkan Proyek:** Biarkan Gradle menyinkronkan semua dependensi.
4.  **Jalankan Aplikasi:** Jalankan aplikasi di emulator atau perangkat fisik Anda.

## Kontribusi

Kontribusi sangat dihargai! Jika Anda memiliki saran atau ingin berkontribusi, silakan buat *pull request* atau buka *issue*.

## Credit

-   **Developer:** Fauzan Faturrahman
-   **Sumber Data:** hendroprwk08/data_endemik

© 2026 Flona Project
