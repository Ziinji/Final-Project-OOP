# Challenge Guidelines

- Implementasi sebuah game beat 'em up, seperti **Double Dragon (1987)** atau contoh yang lebih modern, yaitu **Elsword** dan **Closers**, dua-duanya merupakan MMORPG buatan Korea.
- Grafik akan merupakan Pixel Art yang dibuat di *Aseprite*, dengan background yang akan di outsource jika waktu tidak mencukupkan kami untuk membuatnya sendiri.
- Ketika game dimulai, *spawn* karakter player di tengah screen. Kemudian, game akan *spawn* Musuh dan ditempatkan secara acak di vector X sesuai dengan karakter player.
- Player mengalahkan musuh dengan melawanya menggunak combo yang sudah disediakan. Musuh akan mati ketika health mereka turun ke 0.
- Jika tidak ada musuh, spawn musuh lagi
- Musuh akan mengikuti karakter player dimanapun posisinya, kemudian menyerang jika sudah di sebelah mereka.
- Player kalah jika *health* mereka mencapai 0.
- Ketika game selesai, tunjukan berapa musuh yang mati.
- Nama main class adalah CresserMain
- Kontrol game:
  - Tombol panah untuk berjalan ke kiri dan kanan
  - Z dan X untuk menyerang
  - Spasi untuk memulai game
