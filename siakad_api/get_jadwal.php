<?php
// 1. Baris ini wajib untuk menampilkan pesan error jika ada yang salah
error_reporting(E_ALL);
ini_set('display_errors', 1);

// 2. Memberitahu browser bahwa output berupa data JSON
header('Content-Type: application/json');

// 3. Memanggil koneksi database
include 'config.php';

// Cek apakah koneksi berhasil (jika $conn tidak ada, script berhenti)
if (!isset($conn)) {
    die(json_encode(["error" => "Koneksi database (config.php) tidak ditemukan atau gagal!"]));
}

$nim = $_GET['nim'] ?? '';

// 4. Mengambil data jadwal berdasarkan NIM
$query = "SELECT * FROM jadwal WHERE nim='$nim'";
$result = mysqli_query($conn, $query);

// Cek apakah query error
if (!$result) {
    die(json_encode(["error" => "Error pada Query SQL: " . mysqli_error($conn)]));
}

$data = [];
while($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}

// 5. Menampilkan data dalam format JSON
echo json_encode($data);
?>