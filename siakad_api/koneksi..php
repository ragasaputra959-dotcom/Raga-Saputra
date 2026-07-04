<?php
$conn = mysqli_connect("localhost", "root", "", "siakad_db"); // Pastikan nama DB-nya sesuai

if (!$conn) {
    die("Koneksi gagal: " . mysqli_connect_error());
}
?>