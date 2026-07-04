<?php
// 1. Pastikan memanggil file yang benar (sesuai folder kamu, yaitu config.php)
include 'config.php'; 

// 2. Mengambil data dari Postman (Raw JSON)
$json = file_get_contents('php://input');
$data = json_decode($json, true);

// Cek apakah data JSON berhasil diterima
if ($data && isset($data['nim']) && isset($data['password'])) {
    $nim = mysqli_real_escape_string($conn, $data['nim']);
    $password = $data['password']; // Jika belum di-hash di DB, gunakan plain text

    // 3. Query ke database
    $query = mysqli_query($conn, "SELECT * FROM mahasiswa WHERE nim='$nim'");
    $user = mysqli_fetch_array($query);

    if ($user) {
        // Cek password (jika di database pakai hash, gunakan password_verify)
        if ($password == $user['password']) {
            echo json_encode(["status" => "success", "message" => "Login Berhasil"]);
        } else {
            echo json_encode(["status" => "error", "message" => "Password salah"]);
        }
    } else {
        echo json_encode(["status" => "error", "message" => "NIM tidak ditemukan"]);
    }
} else {
    echo json_encode(["status" => "error", "message" => "Data tidak lengkap atau format JSON salah"]);
}
?>